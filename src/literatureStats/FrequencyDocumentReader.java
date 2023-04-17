package literatureStats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static a3algorithms.Normaliser.normalise;

public class FrequencyDocumentReader {
    private FrequencyDocumentReader() {} // 01/04/2023 updated to have private visibility

    public static final String DEFAULT_NON_WORD_CHARS = "[^a-zA-Z0-9'\\s]+";

    /**
     *  Reads the named document file using default settings. Use the
     *  defaults for information not provided.
     *
     * @param dictionaryFileName
     * @return
     */
    public static Map<String, FrequencyWord> readDocument(
            String dictionaryFileName) {
        return readDocument(new FrequencyReaderConfig(dictionaryFileName, FrequencyReaderConfig.EMPTY_MARKER, FrequencyReaderConfig.EMPTY_MARKER, FrequencyReaderConfig.DEFAULT_VERBOSITY), DEFAULT_NON_WORD_CHARS);
    }

    /**
     * Reads a document using default settings for those not provided.
     *
     * @param dictionaryFileName
     * @param nonWordChars
     * @return
     */
    public static Map<String, FrequencyWord> readDocument(
            String dictionaryFileName, String nonWordChars) {
    return readDocument(new FrequencyReaderConfig(dictionaryFileName, FrequencyReaderConfig.EMPTY_MARKER, FrequencyReaderConfig.EMPTY_MARKER, FrequencyReaderConfig.DEFAULT_VERBOSITY), nonWordChars);
    }

    /**
     * reads a document using the default set of non-word characters.
     *
     * @param config
     * @return
     */
    public static Map<String, FrequencyWord> readDocument(
            FrequencyReaderConfig config) {

        return readDocument(config, DEFAULT_NON_WORD_CHARS);
    }

    /**  read the file specified in the configuration and obey the
     *   start and stop markers.
     *   If the configuration has a non-zero verbosity then print the following messages:
     *   if the word is new:
     *   Added normalisedWord
     *   if the word already exists:
     *   Incremented normalisedWord to newCount
     *   In both cases substitute normalisedWord with the actual normalised form.
     *   If a word already exists print the count that includes the instance you
     *   are processing.
     *
     * @param config
     * @param nonWordChars
     * @return
     */
    public static Map<String, FrequencyWord> readDocument(
            FrequencyReaderConfig config, String nonWordChars) {

        boolean started = false;
        Map<String, FrequencyWord> words = new HashMap<>();

        try ( final Scanner sc = new Scanner(
                new File(config.DOCUMENT_FILENAME)) ) {
            while ( sc.hasNextLine() ) {
                String line = sc.nextLine();

                if (line.contains(config.START_MARKER)) {
                    started = true;
                    continue;
                }
                else if (line.contains(config.STOP_MARKER)) {
                    break;
                }

                if (!started) {
                    continue;
                }

                String[] tokens = line.replaceAll(nonWordChars, " ").split("\\s+");

                for (String word: tokens) {
                    if (word.isBlank()) {
                        continue;
                    }
                    String normalisedWord = normalise(word);

                    if (!words.containsKey(normalisedWord)) {
                        words.put(normalisedWord, new FrequencyWord(normalisedWord));

                        if (config.getVerbosity().isVerbose()) {
                            System.out.printf("Added %s", normalisedWord);
                        }
                    } else {
                        FrequencyWord frequencyWord = words.get(normalisedWord);
                        frequencyWord.incrementCount();
                        if (config.getVerbosity().isVerbose()) {
                            System.out.printf("Incremented %s to %d%n", normalisedWord, frequencyWord.getCount());
                        }
                    }
                }
            }
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
