package com.szczepaniak.bibtex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String path = args[0];

        Document document = new Document(path);

        System.out.println(document);
//        ArrayList<Entry> entries = SpecificEntries.getSpecificEntryTypes("INBOOK", document);
//        for (Entry entry : entries)
//            Format.printEntry(entry);
    }
}

/* TODO
*  -command line
*  -better table for printing
*  -string handling if it's bigger than one line
*/
