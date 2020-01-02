package com.szczepaniak.bibtex;

import java.util.ArrayList;

/**
 * Used only for printing entries is ASCII format as pretty table
 */
public class Format {
    /**
     * Prints entry as pretty table using ASCII
     * @param entry entry to be printed
     */
    static void printEntry(Entry entry) {
        int keyLength = 0, valueLength = 0;

        int nameLength = entry.getCitationKey().length() + entry.getEntryType().length() + 5;

        for (Field field : entry.getFields()) {
            keyLength = Math.max(keyLength, field.getKey().length());
            valueLength = Math.max(valueLength, field.getRaw().length());
        }

        int authorsNumber = 0, editorsNumber = 0;
        ArrayList<String> authorsNames = new ArrayList<>();
        ArrayList<String> editorsNames = new ArrayList<>();

        for (Author author : entry.getAuthors().values()) {
            valueLength = Math.max(valueLength, author.getName().length());
            keyLength = Math.max(keyLength, author.getAuthorType().length());

            if (author.getAuthorType().equals("author")) {
                authorsNumber += 1;
                authorsNames.add(author.getName());
            }
            else {
                editorsNumber += 1;
                editorsNames.add(author.getName());
            }
        }

        if (nameLength > keyLength + valueLength)
            valueLength = nameLength - keyLength;

        keyLength += 1;
        valueLength += 1;
        int totalLength = keyLength + valueLength + 3;

        StringBuilder top = new StringBuilder(), middle = new StringBuilder(), bottom = new StringBuilder();
        top.append('╔');
        middle.append('║');
        bottom.append('╚');
        for (int i = 1; i <= totalLength; i++) {
            top.append('═');
            middle.append('═');
            bottom.append('═');
        }
        top.append('╗');
        middle.append('║');
        bottom.append('╝');


        System.out.println(top);

        String entryName = "║" + String.format("%-" + totalLength + "s", entry.getEntryType() + " (" + entry.getCitationKey() + ")") + "║";
        System.out.println(entryName);

        System.out.println(middle);

        printAuthors(authorsNumber, authorsNames, keyLength, valueLength, "author");
        printAuthors(editorsNumber, editorsNames, keyLength, valueLength, "editor");

        for (Field field : entry.getFields()) {
            String fieldKey = String.format("║ %-" + keyLength + "s", field.getKey()) + "│";
            String fieldRaw = String.format(" %-" + valueLength + "s", field.getRaw()) + "║";
            System.out.println(fieldKey + fieldRaw);
        }
        System.out.println(bottom + "\n");
    }

    /**
     * Helping method for printing entry's authors in new lines
     * @param count number of authors (or editors) for this entry
     * @param authors List of authors full names
     * @param keyLength length of the first part of field table (length of field's keys)
     * @param valueLength length of the second part of field table (length of field's value)
     * @param authorType author type (author or editor)
     */
    private static void printAuthors(int count, ArrayList<String> authors, int keyLength, int valueLength, String authorType) {
        if (count > 0) {
            String type = String.format("║ %-" + keyLength + "s", authorType) + "│";
            type += String.format(" • %-" + (valueLength - 2) + "s", authors.remove(0)) + "║";

            System.out.println(type);
            for (String authorName : authors) {
                String authorString = String.format("║  %" + keyLength + "s", "│");
                authorString += String.format(" • %-" + (valueLength - 2) + "s", authorName) + "║";
                System.out.println(authorString);
            }
        }
    }
}
