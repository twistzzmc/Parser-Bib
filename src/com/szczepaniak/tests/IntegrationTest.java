package com.szczepaniak.tests;

import com.szczepaniak.bibtex.Author;
import com.szczepaniak.bibtex.Entry;
import com.szczepaniak.bibtex.Field;
import org.junit.jupiter.api.Test;
import com.szczepaniak.bibtex.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    @Test
    public void testWholeProgram() {
        Document document = new Document("C:\\Users\\Michal\\IdeaProjects\\szczepaniak_bibtex\\test.bib");
        ArrayList<Entry> entries = document.getFileEntries();

        assertEquals(entries.size(), 2);

        Entry entryArticle = entries.get(0);
        Entry entryInbook = entries.get(1);

        assertEquals(entryArticle.getEntryType(), "ARTICLE");
        assertEquals(entryArticle.getCitationKey(), "whole-journal");
        assertEquals(entryArticle.getAuthors().values().size(), 1);
        assertEquals(entryArticle.getFields().size(), 8);

        assertEquals(entryInbook.getEntryType(), "INBOOK");
        assertEquals(entryInbook.getCitationKey(), "inbook-minimal");
        assertEquals(entryInbook.getAuthors().values().size(), 1);
        assertEquals(entryInbook.getFields().size(), 4);

        ArrayList<Field> fieldsArticle = entryArticle.getFields();
        assertEquals(fieldsArticle.get(0).getKey(), "key");
        assertEquals(fieldsArticle.get(1).getKey(), "title");
        assertEquals(fieldsArticle.get(2).getKey(), "journal");
        assertEquals(fieldsArticle.get(3).getKey(), "year");
        assertEquals(fieldsArticle.get(4).getKey(), "volume");
        assertEquals(fieldsArticle.get(5).getKey(), "number");
        assertEquals(fieldsArticle.get(6).getKey(), "month");
        assertEquals(fieldsArticle.get(7).getKey(), "note");

        assertEquals(fieldsArticle.get(0).getRaw(), "GAJ");
        assertEquals(fieldsArticle.get(1).getRaw(), "The Gnats and Gnus Document Preparation System");
        assertEquals(fieldsArticle.get(2).getRaw(), "G-Animal's Journal");
        assertEquals(fieldsArticle.get(3).getRaw(), "1986");
        assertEquals(fieldsArticle.get(4).getRaw(), "41");
        assertEquals(fieldsArticle.get(5).getRaw(), "7");
        assertEquals(fieldsArticle.get(6).getRaw(), "jul");
        assertEquals(fieldsArticle.get(7).getRaw(), "The entire issue is devoted to gnats and gnus(this entry is a cross-referenced ARTICLE (journal)),");

        ArrayList<Field> fieldsInbook = entryInbook.getFields();
        assertEquals(fieldsInbook.get(0).getKey(), "title");
        assertEquals(fieldsInbook.get(1).getKey(), "publisher");
        assertEquals(fieldsInbook.get(2).getKey(), "year");
        assertEquals(fieldsInbook.get(3).getKey(), "chapter");

        assertEquals(fieldsInbook.get(0).getRaw(), "Fundamental Algorithms");
        assertEquals(fieldsInbook.get(1).getRaw(), "Addison-Wesley");
        assertEquals(fieldsInbook.get(2).getRaw(), "1973");
        assertEquals(fieldsInbook.get(3).getRaw(), "1.2");

        LinkedHashMap<String, Author> authorsMapArticle = entryArticle.getAuthors();
        assertEquals(authorsMapArticle.get("Aamport").getAuthorType(), "author");
        assertEquals(authorsMapArticle.get("Aamport").getName(), "Leslie A. Aamport");

        LinkedHashMap<String, Author> authorsMapInbook = entryInbook.getAuthors();
        assertEquals(authorsMapInbook.get("Knuth").getAuthorType(), "author");
        assertEquals(authorsMapInbook.get("Knuth").getName(), "Donald E. Knuth");
    }
}