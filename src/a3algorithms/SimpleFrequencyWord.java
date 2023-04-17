package a3algorithms;

import static a3algorithms.Normaliser.normalise;

/**
 * Stores a word with its frequency of usage.
 * This word has no knowledge of any other words.
 */
public class SimpleFrequencyWord implements Comparable<SimpleFrequencyWord> {
    // 01/04/2023 initialise elsewhere
    protected final String word;

    protected int count; // 01/04/2023 initialise elsewhere

    /**
     *  constructor.
     *  Ensure class variables are initialised.
     *
     * @param word
     */
    SimpleFrequencyWord(String word) {
        this.word = word;
        count = 1;
    }

    /**
     * getWord() is the getter for the core word.
     *
     * @return
     */
    public String getWord() {

        return word;
    }

    /**
     * getCount() is the getter for the current word frequency.
     */
    public int getCount() {

        return count;
    }

    /**
     * incrementCount by one.
     */
    public void incrementCount() {
        count++;
    }

    /**
     *  toString generates a one-line String according to the pattern
     *              digits right-justified in 4 spaces
     *              tab
     *              the word
     */
    @Override
    public String toString() {

        return String.format("%-4d\t%s%n", count, word);
    }

    /**
     *  toString(String) generates a one-line String of the frequency
     *  then the normalised word according to the supplied pattern.
     *
     * @param wordStatePattern
     * @return
     */
    public String toString(String wordStatePattern) {

        return String.format(wordStatePattern, count, word);
    }

    /**
     * compareTo() knows how to compare this object with another of the same type.
     *          You need to learn how to do this. Compare frequencies.
     *
     * @param other the counterpart object to be compared with.
     * @return
     */
    @Override
    public int compareTo(SimpleFrequencyWord other) {

        return Integer.compare(count, other.getCount());
    }
}
