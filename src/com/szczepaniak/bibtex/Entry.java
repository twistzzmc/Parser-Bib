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

    public void checkCompulsoryFields(ArrayList<Entry> allEntries) {
        LinkedHashMap<String, FieldName> compFieldNames = Types.entryTypes.get(entryType).getCompulsoryFieldNames();
        LinkedHashSet<String> newCompFieldNames = new LinkedHashSet<>(compFieldNames.keySet());

        String crossref = "";

        for (Field field : fields) {
            removeField(compFieldNames, newCompFieldNames, field);

            if (field.getKey().equals("crossref")) {
                crossref = field.getRaw().toLowerCase();
            }
        }

        for (Author author : authors.values()) {
            removeAuthor(compFieldNames, newCompFieldNames, author);
        }

        if (!crossref.equals("")) {
            for (Entry entry : allEntries) {
                if (entry.getCitationKey().equals(crossref)) {
                    for (Field field : entry.fields) {
                        removeField(compFieldNames, newCompFieldNames, field);
                    }

                    for (Author author : entry.authors.values()) {
                        removeAuthor(compFieldNames, newCompFieldNames, author);
                    }
                }
            }
        }

        if (!newCompFieldNames.isEmpty()) {
            throw new IllegalArgumentException("Entry with key " + citationKey + " does not have all compulsory fields!");
        }
    }

    private void removeField(LinkedHashMap<String, FieldName> compFieldNames, LinkedHashSet<String> newCompFieldNames, Field field) {
        if (newCompFieldNames.contains(field.getKey())) {
            if (compFieldNames.get(field.getKey()).name2 != null) {
                newCompFieldNames.remove(compFieldNames.get(field.getKey()).name2);
                newCompFieldNames.remove(compFieldNames.get(field.getKey()).name1);
            }
            else {
                newCompFieldNames.remove(field.getKey());
            }
        }
    }

    private void removeAuthor(LinkedHashMap<String, FieldName> compFieldNames, LinkedHashSet<String> newCompFieldNames, Author author) {
        if (newCompFieldNames.contains(author.getAuthorType())) {
            if (compFieldNames.get(author.getAuthorType()).name2 != null) {
                newCompFieldNames.remove(compFieldNames.get(author.getAuthorType()).name2);
                newCompFieldNames.remove(compFieldNames.get(author.getAuthorType()).name1);
            }
            else {
                newCompFieldNames.remove(author.getAuthorType());
            }
        }
    }
}
