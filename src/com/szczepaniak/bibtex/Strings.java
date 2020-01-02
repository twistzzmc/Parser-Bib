package com.szczepaniak.bibtex;

/**
 * Stores information about STRINGS declarations in BibTeX file
 */
public class Strings {
    /**
     * key of the STRINGS (what we look for in entries to change)
     */
    private String key;

    /**
     * value of the STRINGS (what we the key for in entries)
     */
    private String value;

    /**
     * Constructor must be given ready values
     * @param key key
     * @param value value
     */
    public Strings(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets STRINGS key
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets STRINGS value
     * @return value
     */
    public String getValue() {
        return value;
    }
}
