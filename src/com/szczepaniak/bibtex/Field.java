package com.szczepaniak.bibtex;

public class Field {
    private String key;
    private String raw;

    public Field(String key, String raw) {
        this.key = key;
        this.raw = raw;
    }

    @Override
    public String toString() {
        return " " + key + " | " + raw;
    }
}
