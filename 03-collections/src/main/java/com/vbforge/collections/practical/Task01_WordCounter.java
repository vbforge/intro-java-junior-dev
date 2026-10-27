package com.vbforge.collections.practical;

import java.util.Map;

/**
 * PRACTICAL TASK 01 — {@code Map} + counting.
 *
 * <p>Count word occurrences in a text. A "word" is a maximal sequence of
 * Unicode letters or digits — everything else (spaces, punctuation) is a separator.
 * Counting must be CASE-INSENSITIVE; the returned map should use lower-case keys.
 */
public final class Task01_WordCounter {

    private Task01_WordCounter() {}

    /**
     * @param text may be null or empty → return an empty map.
     * @return a map from each lower-case word to its count. Do NOT guarantee key order.
     * Hint: {@code text.toLowerCase().split("[^\\p{L}\\p{Nd}]+")} splits on non-letters/non-digits.
     */
    public static Map<String, Integer> count(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the {@code n} most frequent words, ties broken by word ascending.
     * Result keys in the returned map should reflect the ranking order (use a LinkedHashMap).
     * If {@code n >= unique words}, return all of them. If {@code n <= 0} → empty map.
     */
    public static Map<String, Integer> topN(String text, int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}