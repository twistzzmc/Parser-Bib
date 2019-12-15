package com.szczepaniak.bibtex;

import java.util.*;

public class Entry {
    private LinkedHashMap<String, Author> authors = new LinkedHashMap<>();
    private ArrayList<String> entryRaw;
    private String citationKey;
    private String entryType;
    private ArrayList<Field> fields;
    private int startIndex;
    private int endIndex;

    public Entry(int start, int end, List<String> allLines) {
        this.startIndex = start;
        this.endIndex = end;

        this.entryRaw = new ArrayList<>();
        this.fields = new ArrayList<>();

        entryRaw = Parser.getLines(startIndex, endIndex, allLines);
        entryType = Parser.getEntryType(entryRaw);
        citationKey = Parser.getCitationKey(entryRaw);
        fields = Parser.getFields(entryRaw);
        this.createAuthors();

        checkEntry();
    }

    private void createAuthors() {
        for (int i = 0; !entryRaw.get(i).equals("}"); i++) {
            String line = entryRaw.get(i);
            if (line.contains("=")) {
                String field = line.substring(0, line.indexOf("=")).trim();

                if (field.equals("author") || field.equals("editor")) {
                    String newLine = line.substring(line.indexOf("=") + 3, line.indexOf(",") - 1).trim();
                    String [] newAuthors = newLine.split("and");
                    for (String author : newAuthors) {
                        author = author.trim();
                        authors.put(author, new Author(author, field));
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        System.out.println(startIndex + " " + endIndex);
        System.out.println(entryType + " (" + citationKey + ")");
        System.out.println("Authors: ");

        Collection<Author> authorNames = authors.values();
        for (Author author : authorNames) {
            if (author.getAuthorType().equals("author"))
                System.out.println("      " + author);
        }

        System.out.println("Editors: ");
        for (Author author : authorNames) {
            if (author.getAuthorType().equals("editor"))
                System.out.println("      " + author);
        }

        for (Field field : fields) {
            System.out.println("   " + field);
        }

        return "";
    }

    public String getCitationKey() {
        return citationKey;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public String getEntryType() {
        return entryType;
    }

    public LinkedHashMap<String, Author> getAuthors() {
        return authors;
    }

    private void checkEntry() {
        int countedCompulsoryFields = 0;
        Types types = new Types();

//        System.out.println(types);
        System.out.println(types.entryTypes.get(entryType).getCompulsoryFieldNames().contains(new FieldName("author", true)));
    }
}
