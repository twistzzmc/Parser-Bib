package com.szczepaniak.bibtex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Types {
//    public static LinkedHashSet<String> entryTypes = new LinkedHashSet<>();
    public static LinkedHashMap<String, ArrayList<FieldNames>> entryTypes = new LinkedHashMap<>();

    public Types() {
        entryTypes.put("ARTICLE", FieldNames.createFieldNames("ARTICLE"));
        entryTypes.put("BOOK", FieldNames.createFieldNames("BOOK"));
        entryTypes.put("INPROCEEDINGS", FieldNames.createFieldNames("INPROCEEDINGS"));
        entryTypes.put("BOOKLET", FieldNames.createFieldNames("BOOKLET"));
        entryTypes.put("INBOOK", FieldNames.createFieldNames("INBOOK"));
        entryTypes.put("INCOLLECTION", FieldNames.createFieldNames("INCOLLECTION"));
        entryTypes.put("MANUAL", FieldNames.createFieldNames("MANUAL"));
        entryTypes.put("MASTERSTHESIS", FieldNames.createFieldNames("MASTERSTHESIS"));
        entryTypes.put("PHDTHESIS", FieldNames.createFieldNames("PHDTHESIS"));
        entryTypes.put("PROCEEDINGS", FieldNames.createFieldNames("PROCEEDINGS"));
        entryTypes.put("TECHREPORT", FieldNames.createFieldNames("TECHREPORT"));
        entryTypes.put("MISC", FieldNames.createFieldNames("MISC"));
        entryTypes.put("UNPUBLISHED", FieldNames.createFieldNames("UNPUBLISHED"));
    }
}
