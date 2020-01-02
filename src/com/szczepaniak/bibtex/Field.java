package com.szczepaniak.bibtex;

/**
 * Stores information about fields (authors and editors NOT included)
 */
public class Field {
    /**
     * Field key (type)
     */
    private String key;

    /**
     * Field value (what it says)
     */
    private String raw;

    /**
     * Field constructor, requires key and value already known
     * @param key field key
     * @param raw field value
     */
    public Field(String key, String raw) {
        this.key = key;
        this.raw = raw;
    }

    /**
     * Gets field key
     * @return field key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets field value
     * @return field value
     */
    public String getRaw() {
        return raw;
    }
}
