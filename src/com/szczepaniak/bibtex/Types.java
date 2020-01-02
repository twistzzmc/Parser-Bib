package com.szczepaniak.bibtex;

import java.util.LinkedHashMap;

/**
 * Method containing every possible entry and field type in BibTeX
 */
public class Types {
    /**
     * Hash map whose keys are entry types and values all fields of this type
     */
    public static final LinkedHashMap<String, FieldNames> entryTypes = new LinkedHashMap<>();

    /**
     * Constructor to put entry types and fields into the hash map
     */
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
}
