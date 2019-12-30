package com.szczepaniak.bibtex;

import java.util.LinkedHashMap;

public class FieldNames {
    private LinkedHashMap<String, FieldName> compulsoryFieldNames = new LinkedHashMap<>();
    private LinkedHashMap<String, FieldName> optionalFieldNames = new LinkedHashMap<>();
    private int compulsoryFieldNamesCount;
    private int optionalFieldNamesCount;

    public FieldNames(String type) {
        createFieldNames(type);
    }

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

    public int getCompulsoryFieldNamesCount() {
        return compulsoryFieldNamesCount;
    }

    public int getOptionalFieldNamesCount() {
        return optionalFieldNamesCount;
    }

    public LinkedHashMap<String, FieldName> getCompulsoryFieldNames() {
        return compulsoryFieldNames;
    }

    public LinkedHashMap<String, FieldName> getOptionalFieldNames() {
        return optionalFieldNames;
    }

    @Override
    public String toString() {
        System.out.println("Compulsory count: " + compulsoryFieldNamesCount);
        for (FieldName fieldName : compulsoryFieldNames.values()) {
            System.out.println(fieldName);
        }

        System.out.println("Optional count: " + optionalFieldNamesCount);
        for (FieldName fieldName : optionalFieldNames.values()) {
            System.out.println(fieldName);
        }

        return "";
    }
}
