package com.szczepaniak.bibtex;

public class Author {
    private String authorType;
    private String name;

    public Author(String name, String authorType) {
        this.name = name;
        this.authorType = authorType;
    }

    public String getAuthorType() {
        return authorType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
