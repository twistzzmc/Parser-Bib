package com.szczepaniak.tests;

import com.szczepaniak.bibtex.FieldName;
import com.szczepaniak.bibtex.FieldNames;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class FieldNamesTest {

    @Test
    void testCreateFields() {
        FieldNames fieldNamesTest = new FieldNames("ARTICLE");

        assertEquals(fieldNamesTest.getCompulsoryFieldNames().get("author").name1, "author");
        assertEquals(fieldNamesTest.getCompulsoryFieldNames().get("title").name1, "title");
        assertEquals(fieldNamesTest.getCompulsoryFieldNames().get("journal").name1, "journal");
        assertEquals(fieldNamesTest.getCompulsoryFieldNames().get("year").name1, "year");

        FieldNames fieldNamesTest2 = new FieldNames("INBOOK");

        LinkedHashMap<String, FieldName> temp = fieldNamesTest2.getCompulsoryFieldNames();
        assertEquals(temp.get("author").name1, "author");
        assertEquals(temp.get("author").name2, "editor");
        assertEquals(temp.get("editor").name1, "author");
        assertEquals(temp.get("editor").name2, "editor");
        assertEquals(temp.get("title").name1, "title");
        assertEquals(temp.get("chapter").name1, "chapter");
        assertEquals(temp.get("chapter").name2, "pages");
        assertEquals(temp.get("pages").name1, "chapter");
        assertEquals(temp.get("pages").name2, "pages");
        assertEquals(temp.get("publisher").name1, "publisher");
        assertEquals(temp.get("year").name1, "year");
    }
}