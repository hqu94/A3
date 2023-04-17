package a3algorithms;

import java.util.Arrays;
public class VowelChecker {
    private VowelChecker() {} // 01/04/2023 updated to have private visibility, do not change

    /**
     *  isVowel() checks if a String is one of the five English vowels
     *  "y" is not considered a vowel here. Assume lowercase input.
     *
     * @param s
     * @return
     */
    public static boolean isVowel(String s){

        String[] vowels = {"a", "e", "i", "o", "u"};
        for (String vowel: vowels) {
            if (s.equals(vowel)) {
                return true;
            }
        }

        return false;
    }
}
