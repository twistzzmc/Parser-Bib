package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

class Parser {
    static ArrayList<Strings> strings = new ArrayList<>();

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

    static void getString(int start, List<String> fileContents) {
        String string = fileContents.get(start);
        String stringKey = string.substring(string.indexOf("{") + 1, string.indexOf("=")-1).trim();
        String stringValue = string.substring(string.indexOf("=") + 3, string.length() - 2).trim();
        Parser.strings.add(new Strings(stringKey, stringValue));
    }

    static ArrayList getLines(int start, int end, List<String> allLines) {
        ArrayList<String> entry = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            entry.add(allLines.get(i));
        }

        return entry;
    }

    static String getEntryType(List<String> entry) {
        String firstLine = entry.get(0);

        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == '{') {
//                System.out.println(firstLine.substring(1, i));
                return firstLine.substring(1, i);
            }
        }

        return "";
    }

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
//                    System.out.println(fieldKey);

                    if (line.endsWith(",")) {
                        fieldRaw = line.substring(line.indexOf("=") + 2, line.length() - 1).trim();
//                        System.out.println(fieldRaw);
                    }
                    else {
                        fieldRaw = line.substring(line.indexOf("=") + 2);
                        fieldRaw += entry.get(start + 1).trim();
//                        System.out.println(fieldRaw);
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
