package com.szczepaniak.bibtex;

import java.util.LinkedHashMap;

/**
 * Stores information abour fields of a specific entry type (used by Types class)
 */
public class FieldNames {
    /**
     * Stores all compulsory fields as hash map where the key is the field name (if it has two names then
     * it is stored twice)
     */
    private LinkedHashMap<String, FieldName> compulsoryFieldNames = new LinkedHashMap<>();

    /**
     * Stores all optional fields as hash map where they key is the field name (if it has two names then
     * it is stored twice)
     */
    private LinkedHashMap<String, FieldName> optionalFieldNames = new LinkedHashMap<>();

    /**
     * Number of all the compulsory fields (counts field with two names as one)
     */
    private int compulsoryFieldNamesCount;

    /**
     * Number of all the optional fields (counts fields with two names as one)
     */
    private int optionalFieldNamesCount;

    /**
     * Constructor for this class
     * @param type type of the entry as String (it's always correct as it is created without users input)
     */
    public FieldNames(String type) {
        createFieldNames(type);
    }

    /**
     * Helping method for constructor, it has all the optional and compulsory fields given
     * @param type type of the entry
     */
    private void createFieldNames(String type) {
        String compulsory = "", optional = "";
        switch (type) {
            case "ARTICLE":
                compulsory = "author, title, journal, year";
                optional = "volume, number, pages, month, note, key, crossref";
                break;
            case "BOOK":
                compulsory = "author|editor, title, publisher, year";
                optional = "volume, series, address, edition, month, note, key, crossref";
                break;
            case "INPROCEEDINGS":
                compulsory = "author, title, booktitle, year";
                optional = "editor, volume|number, series, pages, address, month, organization, publisher, note, key, crossref";
                break;
            case "BOOKLET":
                compulsory = "title";
                optional = "author, howpublished, address, month, year, note, key, crossref";
                break;
            case "INBOOK":
                compulsory = "author|editor, title, chapter|pages, publisher, year";
                optional = "volume|number, series, type, address, edition, month, note, key, crossref";
                break;
            case "INCOLLECTION":
                compulsory = "author, title, booktitle, publisher, year";
                optional = "editor, volume|number, series, type, chapter, pages, address, edition, month, note, key, crossref";
                break;
            case "MANUAL":
                compulsory = "title";
                optional = "author, organization, address, edition, month, year, note, key, crossref";
                break;
            case "MASTERSTHESIS":
                compulsory = "author, title, school, year";
                optional = "type, address, month, note, key, crossref";
                break;
            case "PHDTHESIS":
                compulsory = "author, title, school, year";
                optional = "type, address, month, note, key, crossref";
                break;
            case "PROCEEDINGS":
                compulsory = "title, year";
                optional = "editor, volume|number, series, address, month, publisher, organization, note, key, crossref";
                break;
            case "TECHREPORT":
                compulsory = "author, title, institution, year";
                optional = "editor, volume|number, series, address, month, organization, publisher, note, key, crossref";
                break;
            case "MISC":
                compulsory = "";
                optional = "author, title, howpublished, month, year, note, key, crossref";
                break;
            case "UNPUBLISHED":
                compulsory = "author, title, note";
                optional = "month, year, key, crossref";
                break;


        }

        putInArray(optional, compulsory);
    }

    /**
     * Helping method for creating field names, parser fields and puts them in appropriate hash map
     *
     * @param optional all optional fields as one String that will be parsed
     * @param compulsory all compulsory fields as one String that will be parsed
     */
    private void putInArray(String optional, String compulsory) {

        if (compulsory.length() != 0) {
            compulsoryFieldNamesCount = compulsory.split(", ").length;
            for (String comp : compulsory.split(", ")) {
                handleField(comp, false);
            }
        }

        optionalFieldNamesCount = optional.split(", ").length;
        for (String opt : optional.split(", ")) {
            handleField(opt, true);
        }
    }

    /**
     * Helping method for putting in array, it takes out names if there are two
     * @param fieldName field name or names (then split by "|")
     * @param optional information whether field is optional
     */
    private void handleField(String fieldName, boolean optional) {
        if (fieldName.length() > 0 && !fieldName.contains("|"))
            if (!optional)
                compulsoryFieldNames.put(fieldName, new FieldName(fieldName, false));
            else
                optionalFieldNames.put(fieldName, new FieldName(fieldName, true));
        else if (fieldName.length() > 0 && fieldName.contains("|")) {
            if (!optional) {
                compulsoryFieldNames.put(fieldName.substring(0, fieldName.indexOf("|")), new FieldName(fieldName, false));
                compulsoryFieldNames.put(fieldName.substring(fieldName.indexOf("|") + 1), new FieldName(fieldName, false));
            }
            else {
                optionalFieldNames.put(fieldName.substring(0, fieldName.indexOf("|")), new FieldName(fieldName, true));
                optionalFieldNames.put(fieldName.substring(fieldName.indexOf("|") + 1), new FieldName(fieldName, true));
            }
        }
    }

    /**
     * Gets compulsory fields as hash map
     * @return hash map of compulsory fields
     */
    public LinkedHashMap<String, FieldName> getCompulsoryFieldNames() {
        return compulsoryFieldNames;
    }
}
