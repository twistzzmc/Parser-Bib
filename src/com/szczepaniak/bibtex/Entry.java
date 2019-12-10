package com.szczepaniak.bibtex;

import java.util.*;

public class Entry {
    private LinkedHashMap<String, String> authors = new LinkedHashMap<>();
    private ArrayList<String> entry;
    private String citationKey;
    private String entryType;
    private ArrayList<Field> fields;
    private int startIndex;
    private int endIndex;

    public Entry(int start, int end, List<String> allLines) {
        this.startIndex = start;
        this.endIndex = end;

        this.entry = new ArrayList<>();
        this.fields = new ArrayList<>();

//        getLines(allLines);

        entry = Parser.getLines(startIndex, endIndex, allLines);
        entryType = Parser.getEntryType(startIndex, allLines);
        citationKey = Parser.getCitationKey(startIndex, allLines);
        fields = Parser.getFields(startIndex, allLines);
    }

//    private void getAuthors(List<String> allLines) {
//        for (int i = startIndex; allLines)
//    }

    @Override
    public String toString() {
        System.out.println(startIndex + " " + endIndex);
        System.out.println(entryType + " (" + citationKey + ")");

        for (Field field : fields) {
            System.out.println(field);
        }

//        for (String line : entry) {
//            System.out.println(line);
//        }
        return "";
    }
}
