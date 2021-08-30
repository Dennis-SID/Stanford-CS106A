package com.cscourse.week5.dsidelnik.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.Arrays;
import java.util.List;

/**
 * Assignment5 Part1
 * Counts number of syllables in the word (works only with english language words)
 * counts all syllables in the word (a, u, i, e, y, o)
 */
public class Assignment5Part1 extends TextProgram {

    /**
     * Main action method inherited from TextProgram
     */
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /*
     * Provides main logic and implementation of the program
     * takes word as a parameter and counts number of occurrences of syllables
     * in the word. Do not counts the last 'e' letter in the word, also counts sequence of the syllables
     * as one syllable
     *
     * If there are no syllables in the word, will return 1
     */
    public int syllablesIn(String word) {
        List<Character> syllableList = createSyllableList();
        boolean prevSyll = false;
        int counter = 0;

        for (int i = 0; i < word.length(); i++) {
            if (syllableList.contains(Character.toLowerCase(word.charAt(i))) && !prevSyll) {
                if (i == word.length() - 1 && Character.toLowerCase(word.charAt(i)) == 'e') continue;
                counter++;
                prevSyll = true;
            } else if (syllableList.contains(Character.toLowerCase(word.charAt(i))) && prevSyll) {
                /* catch the condition, doing nothing and continue loop */
            } else {
                prevSyll = false;
            }
        }
        return Math.max(counter, 1); // check if counter less then 1, returns 1 instead
    }

    /*
     * Helper method, creates list of syllables
     * 'y' syllable included by condition of the task
     */
    private List<Character> createSyllableList() {
        return Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');
    }
}
