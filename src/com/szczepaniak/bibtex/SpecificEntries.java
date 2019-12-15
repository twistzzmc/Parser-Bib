package com.szczepaniak.bibtex;

import java.text.Normalizer;
import java.util.ArrayList;

public class SpecificEntries {
    public static ArrayList<Entry> getAuthorEntries(String author, Document document) {
        ArrayList<Entry> authorEntries = new ArrayList<>();

        for (Entry entry : document.getFileEntries()) {
            if (entry.getAuthors().containsKey(author)) {
                authorEntries.add(entry);
            }
        }

        return authorEntries;
    }

    public static ArrayList<Entry> getSpecificEntryTypes(String entryType, Document document) {
        ArrayList<Entry> specificEntryTypes = new ArrayList<>();

        for (Entry entry : document.getFileEntries()) {
            if (entry.getEntryType().equals(entryType)) {
                specificEntryTypes.add(entry);
            }
        }

        return specificEntryTypes;
    }
}
