package com.szczepaniak.bibtex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the full BibTeX document
 */
public class Document {
    /**
     * The list stores all of the lines of the BibTeX document
     * Each list element is a line from of original file
     */
    private List<String> fileContents;

    /**
     * Array list that stores all of the parsed entries of the document
     */
    private ArrayList<Entry> fileEntries;

    /**
     * Constructor of document, gets entries and then checks if all of them have
     * required fields for their's category
     *
     * @param path path to the document
     */
    public Document(String path) {
        this.loadFile(path);
        fileEntries = Parser.getEntries(fileContents);

        for (Entry entry : fileEntries) {
            entry.checkCompulsoryFields(fileEntries);
        }
    }

    /**
     * Reads the file and gets all the file contents into the list
     *
     * @param path path to the document
     */
    private void loadFile(String path) {
        try {
            fileContents = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints all the entries in as ASCII tables
     *
     * @return
     */
    @Override
    public String toString() {
        for (Entry entry : fileEntries) {
            Format.printEntry(entry);
        }

        return "";
    }

    /**
     * Get file entries
     *
     * @return file entries
     */
    public ArrayList<Entry> getFileEntries() {
        return fileEntries;
    }

    /**
     * Set file entries (used for filtering)
     *
     * @param fileEntries filtered entries
     */
    public void setFileEntries(ArrayList<Entry> fileEntries) {
        this.fileEntries = fileEntries;
    }
}
