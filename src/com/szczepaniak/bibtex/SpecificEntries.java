package com.szczepaniak.bibtex;

import java.util.ArrayList;

public class SpecificEntries {
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
