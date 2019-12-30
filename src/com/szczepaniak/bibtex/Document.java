package com.szczepaniak.bibtex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Document {
    // List containing lines of the whole document
    private List<String> fileContents;

    // List containing every entry
    private ArrayList<Entry> fileEntries;

    public Document(String path) {
        this.loadFile(path);
        fileEntries = Parser.getEntries(fileContents);

        for (Entry entry : fileEntries) {
            entry.checkCompulsoryFields(fileEntries);
        }
    }

    // Reading the file and putting it in fileContents
    private void loadFile(String path) {
        try {
            fileContents = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        for (Entry entry : fileEntries) {
            Format.printEntry(entry);
        }

        return "";
    }

    public ArrayList<Entry> getFileEntries() {
        return fileEntries;
    }

    public void setFileEntries(ArrayList<Entry> fileEntries) {
        this.fileEntries = fileEntries;
    }
}
