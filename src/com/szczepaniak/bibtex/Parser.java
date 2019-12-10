package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.List;

class Parser {
    static ArrayList<Entry> getEntries(List<String> fileContents) {
        ArrayList<Entry> fileEntries = new ArrayList<>();
        Types entryTypes = new Types();

//        entryTypes.entryTypes.forEach((type, entryList) -> {
//            System.out.println(type + "\n" + entryList);
//        });

        int start = -1, end;
        for (int i = 0; i < fileContents.size(); i++) {
            if (!fileContents.get(i).isEmpty() && fileContents.get(i).charAt(0) == '@') {
                if (start != -1 && entryTypes.entryTypes.containsKey(getEntryType(start, fileContents))) {
                    end = i - 2;
                    fileEntries.add(new Entry(start, end, fileContents));
                }
                start = i;
            }
        }

        return fileEntries;
    }

    static ArrayList getLines(int start, int end, List<String> allLines) {
        ArrayList<String> entry = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            entry.add(allLines.get(i));
        }

        return entry;
    }

    static String getEntryType(int start, List<String> allLines) {
        String firstLine = allLines.get(start);

        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == '{') {
//                System.out.println(firstLine.substring(1, i));
                return firstLine.substring(1, i);
            }
        }

        return "";
    }

    static String getCitationKey(int start, List<String> allLines) {
        String firstLine = allLines.get(start);
        int first = 0;

        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == '{')
                first = i + 1;
            if (firstLine.charAt(i) == ',')
                return firstLine.substring(first, i);
        }

        throw new IllegalStateException("Entry must have citation key");
    }

    static ArrayList<Field> getFields(int start, List<String> allLines) {
        ArrayList<Field> fields = new ArrayList<>();

        while ( true ) {
            start++;
            if (allLines.get(start).equals("}"))
                break;
            else {
                String line = allLines.get(start), fieldRaw = "";
                if (line.contains("=")) {
                    String fieldKey = line.substring(3, line.indexOf("=") - 1);
//                    System.out.println(fieldKey);

                    if (line.endsWith(",")) {
                        fieldRaw = line.substring(line.indexOf("=") + 2, line.length() - 1);
//                        System.out.println(fieldRaw);
                    }
                    else {
                        fieldRaw = line.substring(line.indexOf("=") + 2);
                        fieldRaw += allLines.get(start + 1).trim();
//                        System.out.println(fieldRaw);
                    }

                    if (!fieldKey.equals("author") && !fieldKey.equals("editor"))
                        fields.add(new Field(fieldKey, fieldRaw));
                }
            }
        }

        return fields;
    }
}
