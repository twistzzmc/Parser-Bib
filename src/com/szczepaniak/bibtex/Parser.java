package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class used for parsing the document
 */
class Parser {
    /**
     * Holds all found @Strings declarations in BibTeX file
     */
    private static ArrayList<Strings> strings = new ArrayList<>();

    /**
     * Main method for parsing the document
     * @param fileContents all lines of the document as Strings
     * @return all parsed entries
     */
    static ArrayList<Entry> getEntries(List<String> fileContents) {
        ArrayList<Entry> fileEntries = new ArrayList<>();
        Types entryTypes = new Types();

        int start = -1, end;
        for (int i = 0; i < fileContents.size(); i++) {
            if (!fileContents.get(i).isEmpty() && fileContents.get(i).charAt(0) == '@') {
                if (start != -1 && entryTypes.entryTypes.containsKey(getEntryType(fileContents.subList(start, fileContents.size() - 1)))) {
                    end = i - 2;
                    fileEntries.add(new Entry(start, end, fileContents));
                }
                start = i;
            }

            if (!fileContents.get(i).isEmpty() && fileContents.get(i).contains("@STRING")) {
                getString(i, fileContents);
            }
        }

        return fileEntries;
    }

    /**
     * Helping method for getting entries, that gets all STRINGS declarations
     * @param start starting index of the declaration
     * @param fileContents all file lines
     */
    private static void getString(int start, List<String> fileContents) {
        if (fileContents.get(start).charAt(fileContents.get(start).length() - 1) == '}') {
            addString(fileContents.get(start).substring(8, fileContents.get(start).length() - 2));
        }
        else {
            for (int i = start + 1; !fileContents.get(i).equals("}"); i++) {
                addString(fileContents.get(i));
            }
        }
    }

    /**
     * Helping method for getting strings, it adds them to string array list
     * @param line line with one string declaration to be parsed
     */
    private static void addString(String line) {
        String key = line.substring(0, line.indexOf('=')).trim();
        String value = line.substring(line.indexOf('=') + 1, line.length() - 1);

        value = value.replaceAll(",", "");
        value = value.replaceAll("\"", "").trim();

        Parser.strings.add(new Strings(key, value));
    }

    /**
     * From all document lines gets only those that have one specific entry (starts with "@" ends with "}")
     * @param start starting entry index ("@")
     * @param end ending entry index ("}")
     * @param allLines all file lines
     * @return array list of entry lines
     */
    static ArrayList getLines(int start, int end, List<String> allLines) {
        ArrayList<String> entry = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            entry.add(allLines.get(i));
        }

        return entry;
    }

    /**
     * From entry line list gets the type of the entry
     * @param entry list of entry lines as Strings
     * @return entry type as String
     */
    static String getEntryType(List<String> entry) {
        String firstLine = entry.get(0);

        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == '{') {
                return firstLine.substring(1, i);
            }
        }

        throw new IllegalStateException("Entry must have entry type");
    }

    /**
     * From entry line list gets the citation key of the entry
     * @param entry list of entry lines as Strings
     * @return citation key as String
     */
    static String getCitationKey(List<String> entry) {
        String firstLine = entry.get(0);
        int first = 0;

        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == '{')
                first = i + 1;
            if (firstLine.charAt(i) == ',')
                return firstLine.substring(first, i);
        }

        throw new IllegalStateException("Entry must have citation key");
    }

    /**
     * From entry line list gets all of it's fields
     * @param entry list of entry lines as Strings
     * @return array list of entry's fields
     */
    static ArrayList<Field> getFields(List<String> entry) {
        ArrayList<Field> fields = new ArrayList<>();
        int start = 0;

        while ( true ) {
            start++;
            if (entry.get(start).equals("}"))
                break;
            else {
                String line = entry.get(start), fieldRaw = "";
                if (line.contains("=")) {
                    String fieldKey = line.substring(3, line.indexOf("=") - 1);

                    if (line.endsWith(",")) {
                        fieldRaw = line.substring(line.indexOf("=") + 2, line.length() - 1).trim();
                    }
                    else {
                        fieldRaw = line.substring(line.indexOf("=") + 2);
                        fieldRaw += entry.get(start + 1).trim();
                    }

                    if (!fieldKey.equals("author") && !fieldKey.equals("editor")) {
                        for (Strings string : Parser.strings) {
                            fieldRaw = fieldRaw.replaceAll(string.getKey(), string.getValue());
                        }
                        fieldRaw = fieldRaw.replaceAll(" # ", "");
                        fieldRaw = fieldRaw.replaceAll("\"", "");

                        if (fieldRaw.length() > 0 && fieldRaw.charAt(0) == '{')
                            fieldRaw = fieldRaw.substring(1, fieldRaw.length() - 1).trim();

                        fields.add(new Field(fieldKey, fieldRaw));
                    }
                }
            }
        }

        return fields;
    }
}
