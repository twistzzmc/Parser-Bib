package com.szczepaniak.bibtex;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private List<String> fileContents;
    private ArrayList<Entry> fileEntries;

    public Document(String path) {
        this.loadFile(path);
        fileEntries = Parser.getEntries(fileContents);
    }

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
