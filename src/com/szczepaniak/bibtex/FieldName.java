package com.szczepaniak.bibtex;

/**
 * Stores information about a specific field type, it is used by FieldNames class only
 */
public class FieldName {
    /**
     * Field name (every field must have that)
     */
    public final String name1;

    /**
     * If field name has two possible names (e.g. author and editor are often interchangeable)
     */
    public final String name2;

    /**
     * If the field is optional or compulsory for a specific entry type (if true then optional)
     */
    public final boolean optional;

    /**
     * Constructor that requires field name (or names if it has "|" sign) and if it is optional or not
     * @param name field name (or names)
     * @param optional information whether its optional
     */
    public FieldName(String name, boolean optional) {
        this.optional = optional;
        if (!name.contains("|")) {
            name1 = name;
            name2 = null;
        }
        else {
            name1 = name.substring(0, name.indexOf("|"));
            name2 = name.substring(name.indexOf("|") + 1);
        }
    }
}
