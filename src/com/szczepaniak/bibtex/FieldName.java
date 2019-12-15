package com.szczepaniak.bibtex;

public class FieldName {
    public final String name1;
    public final String name2;
    public final boolean optional;

    public FieldName(String name, boolean optional) {
        this.name1 = name;
        this.name2 = null;
        this.optional = optional;
    }

    public FieldName(String name1, String name2, boolean optional) {
        this.name1 = name1;
        this.name2 = name2;
        this.optional = optional;
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
