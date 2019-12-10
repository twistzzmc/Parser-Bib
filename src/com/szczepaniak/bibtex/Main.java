package com.szczepaniak.bibtex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String path = args[0];

        Document document = new Document(path);

        System.out.println(document);
    }
}
