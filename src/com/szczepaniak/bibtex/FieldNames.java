package com.szczepaniak.bibtex;

import java.util.ArrayList;

public class FieldNames {
    private String name1;
    private String name2;
    private boolean optional;

    public FieldNames(String name, boolean optional) {
        this.name1 = name;
        this.name2 = null;
        this.optional = optional;
    }

    public FieldNames(String name1, String name2, boolean optional) {
        this.name1 = name1;
        this.name2 = name2;
        this.optional = optional;
    }

    public static ArrayList<FieldNames> createFieldNames(String type) {
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

        return putInArray(optional, compulsory);
    }

    private static ArrayList<FieldNames> putInArray(String optional, String compulsory) {
        ArrayList<FieldNames> fieldNames = new ArrayList<>();

        for (String comp : compulsory.split(", ")) {
            if (comp.length() > 0)
                fieldNames.add(newFieldName(comp, false));
        }

        for (String opt : optional.split(", ")) {
            if (opt.length() > 0)
                fieldNames.add(newFieldName(opt, true));
        }

        return fieldNames;
    }

    private static FieldNames newFieldName(String name, boolean optional) {
        if (!name.contains("|")) {
            return new FieldNames(name, optional);
        }
        else {
            return new FieldNames(name.substring(0, name.indexOf("|")), name.substring(name.indexOf("|") + 1), optional);
        }
    }

    @Override
    public String toString() {
        if (name2 == null) {
            return (name1 + " " + optional);
        }
        else {
            return (name1 + "|" + name2 + " " + optional);
        }
    }
}
