package com.szczepaniak.tests;

import com.szczepaniak.bibtex.FieldName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldNameTest {
    @Test
    void testConstructor() {
        FieldName fieldNameTest = new FieldName("author|editor", false);
        assertEquals(fieldNameTest.name1, "author");
        assertEquals(fieldNameTest.name2, "editor");
        assertEquals(fieldNameTest.optional, false);

        FieldName fieldNameTest2 = new FieldName("crossref", true);
        assertEquals(fieldNameTest2.name1, "crossref");
        assertEquals(fieldNameTest2.name2, null);
        assertEquals(fieldNameTest2.optional, true);
    }
}