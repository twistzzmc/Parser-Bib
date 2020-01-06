package com.szczepaniak.tests;

import com.szczepaniak.bibtex.Entry;
import com.szczepaniak.bibtex.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    @Test
    public void testValidateMissingYear() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Missing year
            List<String> file = new ArrayList<>();
            file.add("@BOOK{book-full,");
            file.add("   author = \"Knuth| Donald E. \",");
            file.add("   title = \"Seminumerical Algorithms\",");
            file.add("   volume = 2,");
            file.add("   series = \"The Art of Computer Programming\",");
            file.add("   publisher = \"Addison-Wesley\",");
            file.add("   address = \"Reading Massachusetts\",");
            file.add("   edition = \"Second\",");
            file.add("   month = \"10 \" # \"styczeń\",");
            file.add("   note = \"This is a full BOOK entry\"");
            file.add("}");


            ArrayList<Entry> entry = Parser.getEntries(file);
            entry.get(0).checkCompulsoryFields(entry);
        });
    }

    @Test
    public void testValidateMissingTitle() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Missing title
            List<String> file = new ArrayList<>();
            file.add("@MANUAL{manual-full,");
            file.add("   author = \"Larry Manmaker\",");
            file.add("   organization = \\\"Chips-R-Us\\\",");
            file.add("   address = \\\"Silicon Valley\\\",");
            file.add("   edition = \\\"Silver\\\",");
            file.add("   month = \\\"kwiecień\\\",");
            file.add("   year = 1986,");
            file.add("   edition = \"Second\",");
            file.add("   note = \\\"This is a full MANUAL entry\\");
            file.add("}");


            ArrayList<Entry> entry = Parser.getEntries(file);
            entry.get(0).checkCompulsoryFields(entry);
        });
    }

    @Test
    public void testValidateInvalidEntryType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<String> file = new ArrayList<>();
            file.add("@INVALID_ENTRY_TYPE{some_citation_key,");
            file.add("   author = \\\"Michał Szczepaniak\\\",");
            file.add("   note = \\\"This entry has invalid entry type.");
            file.add("}");


            ArrayList<Entry> entry = Parser.getEntries(file);
            entry.get(0).checkCompulsoryFields(entry);
        });
    }
}