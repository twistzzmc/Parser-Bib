package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class FieldNames {
    private LinkedHashSet<FieldName> compulsoryFieldNames = new LinkedHashSet<>();
    private LinkedHashSet<FieldName> optionalFieldNames = new LinkedHashSet<>();
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
        compulsoryFieldNamesCount = compulsory.split(", ").length;
        for (String comp : compulsory.split(", ")) {
            if (comp.length() > 0)
                compulsoryFieldNames.add(newFieldName(comp, false));
        }

        optionalFieldNamesCount = optional.split(", ").length;
        for (String opt : optional.split(", ")) {
            if (opt.length() > 0)
                optionalFieldNames.add(newFieldName(opt, true));
        }
    }

    private static FieldName newFieldName(String name, boolean optional) {
        if (!name.contains("|")) {
            return new FieldName(name, optional);
        }
        else {
            return new FieldName(name.substring(0, name.indexOf("|")), name.substring(name.indexOf("|") + 1), optional);
        }
    }

    public int getCompulsoryFieldNamesCount() {
        return compulsoryFieldNamesCount;
    }

    public int getOptionalFieldNamesCount() {
        return optionalFieldNamesCount;
    }

    public LinkedHashSet<FieldName> getCompulsoryFieldNames() {
        return compulsoryFieldNames;
    }

    public LinkedHashSet<FieldName> getOptionalFieldNames() {
        return optionalFieldNames;
    }

    @Override
    public String toString() {
        System.out.println("Compulsory count: " + compulsoryFieldNamesCount);
        for (FieldName fieldName : compulsoryFieldNames) {
            System.out.println(fieldName);
        }

        System.out.println("Optional count: " + optionalFieldNamesCount);
        for (FieldName fieldName : optionalFieldNames) {
            System.out.println(fieldName);
        }

        return "";
    }
}
