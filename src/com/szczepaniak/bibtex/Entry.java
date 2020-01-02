package com.szczepaniak.bibtex;

import java.util.*;

/**
 * Stores information about the entry
 */
public class Entry {
    /**
     * Hash map of all the authors of this entry
     * Full name is used as a key, Author class as a value (gives information about author's type)
     */
    private LinkedHashMap<String, Author> authors = new LinkedHashMap<>();

    /**
     * Stores raw unparsed entry as a list of lines of the entry (starts at "@" ends with "}"
     */
    private ArrayList<String> entryRaw;

    /**
     * Stores citation key
     */
    private String citationKey;

    /**
     * Stores entry type
     */
    private String entryType;

    /**
     * Stores all the fields of the entry except of the authors and editors
     */
    private ArrayList<Field> fields;

    /**
     * Starting line index of the entry in full unparsed document
     */
    private int startIndex;

    /**
     * Ending line index of the entry in full unparsed document
     */
    private int endIndex;

    /**
     * Constructor of the entry, gets the lines of the entry, entry type, citation key using Parser class and
     * authors using private method in this class
     *
     * @param start starting line index of this entry in full document (all lines)
     * @param end ending line index of this entry in full document (all lines)
     * @param allLines all lines of the BibTeX file as list of Strings
     */
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

    /**
     * Gets all the authors and editors of the entry and puts it in the authors hash map
     * (for each author and editor creates Author class)
     */
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

    /**
     * Gets citation key
     * @return citation key
     */
    public String getCitationKey() {
        return citationKey;
    }

    /**
     * Gets entry's fields
     * @return entry's fields
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * Gets entry's type
     * @return entry's type
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * Gets entry's authors in hash map
     * @return entry's authors
     */
    public LinkedHashMap<String, Author> getAuthors() {
        return authors;
    }

    /**
     * Checks if the entry has all of the compulsory fields (takes under consideration crossref field as well as
     * if fields has two possible names e.g. author|editor)
     * If they don't error occurs.
     *
     * @param allEntries all lines of the unparsed BibTeX file
     */
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

    /**
     * Helping method for checking compulsory fields, it removes a field from the temporary hash set so we know that
     * this compulsory field has already been used (needed for fields with two possible names)
     *
     * @param compFieldNames Hash map with all compulsory fields
     * @param newCompFieldNames Temporary hash set with not yet found compulsory field types
     * @param field Field type
     */
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

    /**
     * Helping method for checking compulsory fields but for authors and editors
     *
     * @param compFieldNames Hash map with all compulsory fields
     * @param newCompFieldNames Temporary hash set with not yet found compulsory field types
     * @param author Checked author (or editor)
     */
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
