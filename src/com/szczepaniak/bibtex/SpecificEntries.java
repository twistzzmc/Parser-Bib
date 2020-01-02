package com.szczepaniak.bibtex;

import java.util.ArrayList;

/**
 * Class used for filtering wanted entries from document
 */
public class SpecificEntries {
    /**
     * Filters document entries, gets only those that have this specifically given author
     * @param author author that entry must have
     * @param document whole parsed document
     * @return entries that have this author
     */
    public static ArrayList<Entry> getAuthorEntries(String author, Document document) {
        ArrayList<Entry> authorEntries = new ArrayList<>();

        for (Entry entry : document.getFileEntries()) {
            if (entry.getAuthors().containsKey(author)) {
                authorEntries.add(entry);
            }
        }

        if (authorEntries.isEmpty())
            System.out.println("Such authors were not found");

        return authorEntries;
    }

    /**
     * Filters document entries, gets all entries whose types are in array list entryType
     * @param entryType entry types to be get
     * @param document whole parsed document
     * @return entries whose types user wanted
     */
    public static ArrayList<Entry> getSpecificEntryTypes(ArrayList<String> entryType, Document document) {
        ArrayList<Entry> specificEntryTypes = new ArrayList<>();

        for (Entry entry : document.getFileEntries()) {
            for (String type : entryType) {
                if (entry.getEntryType().equals(type)) {
                    specificEntryTypes.add(entry);
                    break;
                }
            }
        }

        if (specificEntryTypes.isEmpty())
            System.out.println("Such entry types were not found");

        return specificEntryTypes;
    }
}
