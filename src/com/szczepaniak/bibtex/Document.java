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
//        this.parse();
    }

    private void loadFile(String path) {
        try {
            fileContents = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void parse() {
//        int start = -1, end;
//        for (int i = 0; i < fileContents.size(); i++) {
//            if (!fileContents.get(i).isEmpty() && fileContents.get(i).charAt(0) == '@') {
//                if (start != -1) {
//                    end = i - 2;
//                    fileEntries.add(new Entry(start, end, fileContents));
//                }
//                start = i;
//            }
//        }
//    }

    @Override
    public String toString() {
        for (Entry entry : fileEntries) {
//            System.out.println(entry);
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
