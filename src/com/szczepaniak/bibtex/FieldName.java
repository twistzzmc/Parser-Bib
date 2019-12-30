package com.szczepaniak.bibtex;

public class FieldName {
    public final String name1;
    public final String name2;
    public final boolean optional;

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
