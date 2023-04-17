package a3algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static a3algorithms.Normaliser.normalise;

public class AdvancedTextFileReader {

    public static final String START_MARKER = "**START";
    public static final String STOP_MARKER  = "**STOP";

    private AdvancedTextFileReader() {} // 01/04/2023 updated to have private visibility, do not change

    /**
     *  advancedReadFile: read all the words of a file between two specific lines.
     *  Works like BasicTextFileReader but with an extra restriction. 01/04/2023  updated
     *  Don't call the BasicTextFileReader code: put all the code in this method. 01/04/2023  updated
     *
     *  Process lines in order.
     *  Skip all lines up to and including the start marker.
     *  Process every line (in order) up to but excluding the stop marker.
     *  Process these lines the same as BasicTextFileReader.readFile().
     *  Sample file: input/advanced-01-portion.txt
     *
     * @param filename
     * @return
     */
    public static List<String> advancedReadFile(String filename) {
        /**
         * The characters to remove from a word.
         */
        final String charsToDelete = "[^A-Za-z0-9'\\s]+";

        List<String> wordList = new ArrayList<>();

        boolean started = false;

        try ( final Scanner sc = new Scanner(new File(filename)) ) {
            while ( sc.hasNextLine() ) {
                String line = sc.nextLine();

                if (line.contains(START_MARKER)) {
                    started = true;
                    continue;
                }
                else if (line.contains(STOP_MARKER)) {
                    break;
                }

                if (!started) {
                    continue;
                }

                String[] words = line.replaceAll(charsToDelete, " ").split("\\s+");

                for (String word: words) {
                    if (word.isBlank()) {
                        continue;
                    }
                    String normalisedWord = normalise(word);
                    if (!wordList.contains(normalisedWord)) {
                        wordList.add(normalisedWord);
                    }
                }
            }
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        }

        return wordList;
    }
}
