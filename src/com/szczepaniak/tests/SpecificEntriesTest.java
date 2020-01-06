package com.szczepaniak.tests;

import com.szczepaniak.bibtex.Parser;
import com.szczepaniak.bibtex.Entry;
import com.szczepaniak.bibtex.SpecificEntries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecificEntriesTest {
    @Test
    void getAuthorEntries() {
        List<String> allLines = new ArrayList<>();
        allLines.add("@INBOOK{inbook-minimal,");
        allLines.add("        title = \"Fundamental Algorithms\",");
        allLines.add("        author = \"Donald E. Knuth\",");
        allLines.add("        year = 1973,");
        allLines.add("        publisher = \"Addison-Wesley\",");
        allLines.add("        chapter = \"1.2\"");
        allLines.add("}");
        allLines.add("");
        allLines.add("@ARTICLE{whole-journal,");
        allLines.add("        author = \"Leslie A. Aamport\",");
        allLines.add("        journal = \"G-Animal's Journal\",");
        allLines.add("        title = \"The Gnats and Gnus Document Preparation System\",");
        allLines.add("        year = 1986");
        allLines.add("}");
        allLines.add("");

        ArrayList<Entry> entries = Parser.getEntries(allLines);
        String author = "Knuth";

        ArrayList<Entry> specificEntries = SpecificEntries.getAuthorEntries(author, entries);
        assertFalse(specificEntries.contains(entries.get(1)));
        assertTrue(specificEntries.contains(entries.get(0)));
    }

    @Test
    void getSpecificEntryTypes() {
        List<String> allLines = new ArrayList<>();
        allLines.add("@ARTICLE{whole-journal,");
        allLines.add("        author = \"Leslie A. Aamport\",");
        allLines.add("        title = \"The Gnats and Gnus Document Preparation System\",");
        allLines.add("        journal = \"G-Animal's Journal\",");
        allLines.add("        year = 1986");
        allLines.add("}");
        allLines.add("");
        allLines.add("@INBOOK{inbook-minimal,");
        allLines.add("        author = \"Donald E. Knuth\",");
        allLines.add("        title = \"Fundamental Algorithms\",");
        allLines.add("        publisher = \"Addison-Wesley\",");
        allLines.add("        year = 1973,");
        allLines.add("        chapter = \"1.2\",");
        allLines.add("}");
        allLines.add("");

        ArrayList<Entry> entries = Parser.getEntries(allLines);
        ArrayList<String> wantedEntries = new ArrayList<>();
        wantedEntries.add("ARTICLE");

        ArrayList<Entry> specificEntries = SpecificEntries.getSpecificEntryTypes(wantedEntries, entries);
        assertFalse(specificEntries.contains(entries.get(1)));
        assertTrue(specificEntries.contains(entries.get(0)));
    }
}