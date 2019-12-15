package com.szczepaniak.bibtex;

public class Author {
    private String authorType;
    private String name;
    private String firstName;
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return name;
    }
}
