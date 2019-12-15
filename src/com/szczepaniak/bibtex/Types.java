package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Types {
    public static final LinkedHashMap<String, FieldNames> entryTypes = new LinkedHashMap<>();

    public Types() {
        entryTypes.put("ARTICLE", new FieldNames("ARTICLE"));
        entryTypes.put("BOOK", new FieldNames("BOOK"));
        entryTypes.put("INPROCEEDINGS", new FieldNames("INPROCEEDINGS"));
        entryTypes.put("BOOKLET", new FieldNames("BOOKLET"));
        entryTypes.put("INBOOK", new FieldNames("INBOOK"));
        entryTypes.put("INCOLLECTION", new FieldNames("INCOLLECTION"));
        entryTypes.put("MANUAL", new FieldNames("MANUAL"));
        entryTypes.put("MASTERSTHESIS", new FieldNames("MASTERSTHESIS"));
        entryTypes.put("PHDTHESIS", new FieldNames("PHDTHESIS"));
        entryTypes.put("PROCEEDINGS", new FieldNames("PROCEEDINGS"));
        entryTypes.put("TECHREPORT", new FieldNames("TECHREPORT"));
        entryTypes.put("MISC", new FieldNames("MISC"));
        entryTypes.put("UNPUBLISHED", new FieldNames("UNPUBLISHED"));
    }

    @Override
    public String toString() {
        for (String type : entryTypes.keySet()) {
            System.out.println("Entry type: " + type);
            System.out.println(entryTypes.get(type) + "\n");
        }

        return "";
    }
}
