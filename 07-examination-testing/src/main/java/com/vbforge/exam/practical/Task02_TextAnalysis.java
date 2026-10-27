package com.vbforge.exam.practical;

import java.util.Map;
import java.util.Optional;

/**
 * PRACTICAL TASK 02 — Strings + Collections + Streams + Basic algorithms.
 *
 * <p>A "word" is a maximal run of Unicode letters and digits. Everything else
 * (whitespace, punctuation) is a separator. All word-level operations are
 * case-insensitive; use lower-case for keys and outputs.
 */
public final class Task02_TextAnalysis {

    private Task02_TextAnalysis() {}

    // ---------------------------------------------------------------------
    // Anagrams
    // ---------------------------------------------------------------------

    /**
     * Return {@code true} iff {@code a} and {@code b} are anagrams — same
     * characters, possibly in a different order, CASE-INSENSITIVE.
     *
     * <p>Non-letter characters are NOT ignored for this check — {@code "Listen!"}
     * and {@code "silent"} are NOT anagrams because of the extra {@code !}.
     *
     * <ul>
     *   <li>{@code ("listen", "silent")} → true</li>
     *   <li>{@code ("Hello", "hello")} → true</li>
     *   <li>{@code ("Dormitory", "dirtyroom")} → true</li>
     *   <li>{@code ("hello", "world")} → false</li>
     *   <li>{@code ("", "")} → true</li>
     *   <li>null (either side) → false</li>
     * </ul>
     */
    public static boolean areAnagrams(String a, String b) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Word frequency
    // ---------------------------------------------------------------------

    /**
     * Count occurrences of each word, case-insensitive. The returned map has
     * lower-case word keys. Null / empty / all-non-word input → empty map.
     *
     * <ul>
     *   <li>{@code "Hello, world! Hello."} → {@code {hello=2, world=1}}</li>
     *   <li>{@code "Java 21 and Java 17"} → {@code {java=2, 21=1, and=1, 17=1}}</li>
     * </ul>
     */
    public static Map<String, Integer> wordFrequency(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * The single most-frequent word. Ties broken alphabetically (first alphabetically wins).
     * Null / empty / all-non-word input → {@code Optional.empty()}.
     *
     * <ul>
     *   <li>{@code "a a b c c c d d d d"} → {@code Optional.of("d")}</li>
     *   <li>{@code "b a c"} → {@code Optional.of("a")} (all count 1 → alphabetical)</li>
     * </ul>
     */
    public static Optional<String> mostFrequentWord(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Palindromes
    // ---------------------------------------------------------------------

    /**
     * {@code true} iff {@code s} reads the same forwards and backwards,
     * IGNORING case and IGNORING non-letter characters.
     *
     * <ul>
     *   <li>{@code "racecar"} → true</li>
     *   <li>{@code "A man a plan a canal Panama"} → true</li>
     *   <li>{@code "No 'x' in Nixon"} → true</li>
     *   <li>{@code "hello"} → false</li>
     *   <li>{@code ""} → true</li>
     *   <li>null → false</li>
     * </ul>
     */
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Longest word
    // ---------------------------------------------------------------------

    /**
     * Return the FIRST longest word (by character count) in the original text,
     * preserving the original case of that word. If there are multiple words of
     * the maximum length, return the one that appears EARLIEST in the text.
     *
     * <ul>
     *   <li>{@code "The quick brown fox"} → {@code Optional.of("quick")}  (5 chars, first of the 5-char words)</li>
     *   <li>{@code "hi WORLD"} → {@code Optional.of("WORLD")}</li>
     *   <li>{@code ""} → {@code Optional.empty()}</li>
     *   <li>null → {@code Optional.empty()}</li>
     * </ul>
     *
     * <p>Hint: iterate words in order; track the current longest. Update only on strict
     * {@code >} (never {@code >=}) so ties keep the first one.
     */
    public static Optional<String> longestWord(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}