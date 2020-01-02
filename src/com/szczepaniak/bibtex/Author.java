package com.szczepaniak.bibtex;

/**
 * Stores information about authors
 */
public class Author {
    /**
     * Author type as a String ("author" or "editor")
     */
    private String authorType;

    /**
     * Full author name as a String
     */
    private String name;

    /**
     * Constructor for author, has to be given full name and type
     * @param name       author name
     * @param authorType author type
     */
    public Author(String name, String authorType) {
        this.name = name;
        this.authorType = authorType;
    }

    /**
     * Get author's type
     * @return author's type
     */
    public String getAuthorType() {
        return authorType;
    }

    /**
     * get author's name
     * @return author's full name
     */
    public String getName() {
        return name;
    }
}
