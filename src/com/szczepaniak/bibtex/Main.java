package com.szczepaniak.bibtex;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main program class.
 * <p>
 * You run this program with following arguments:
 * <ul>
 * <li>-f, --file=FILE<br>path to BibTeX file</li>
 * <li>-a, --author=AUTHOR[,AUTHOR...]<br>author(s) to search for</li>
 * <li>-t, --type=TYPE[,TYPE...]<br>entry type(s) to search for</li>
 * <li>-h, --help<br>show help message and exit</li>
 * <li>-v, --version<br>print version information and exit</li>
 * </ul>
 */
@Command(name = "bibtex-parser",
        mixinStandardHelpOptions = true,
        sortOptions = false,
        version = "BibTeX parser by Michał Szczepaniak 2019",
        headerHeading = "@|bold,underline Usage:|@%n%n",
        synopsisHeading = "%n",
        descriptionHeading = "%n@|bold,underline Description:|@%n%n",
        parameterListHeading = "%n@|bold,underline Parameters:|@%n",
        optionListHeading = "%n@|bold,underline Options:|@%n",
        header = "Display filtered entries from BibTeX file.",
        description = "Parses the specified BibTeX file, then displays only those entries " +
                "that match specified filters.")

public class Main implements Runnable{

    @Option(names = {"-p", "--path"}, required = true, paramLabel = "PATH", description = "path to BibTeX file")
    private String path;

    @Option(names = {"-a", "--author"}, split = ",", paramLabel = "AUTHOR", description = "searching for author(s) entries")
    private ArrayList<String> authors = new ArrayList<>();

    @Option(names = {"-t", "--type"}, split = ",", paramLabel = "TYPE", description = "search for specific types of entry(s)")
    private ArrayList<String> types = new ArrayList<>();

    public static void main(String[] args) {
        CommandLine.run(new Main(), args);
    }

    @Override
    public void run() {
        Document document = new Document(path);

        for (String author : authors) {
            document.setFileEntries(SpecificEntries.getAuthorEntries(author, document));
        }

        for (String type : types) {
            document.setFileEntries(SpecificEntries.getSpecificEntryTypes(type, document));
        }

        System.out.println(document);
    }
}

/* TODO
*  -add comments
*  -cleaning the code
*  -add errors
*  -add tests
*  -proper filtering => done (i think xd)
*  -command line => mostly done
*  -better table for printing => done
*  -string handling if it's bigger than one line => done
*/
