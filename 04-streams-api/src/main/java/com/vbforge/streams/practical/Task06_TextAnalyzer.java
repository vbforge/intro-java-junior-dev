package com.vbforge.streams.practical;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * PRACTICAL TASK 06 — End-to-end text analysis with streams.
 *
 * <p>A "word" is a maximal run of Unicode letters and digits — everything else is a separator.
 * Case is ignored; use lower-case for all outputs.
 */
public final class Task06_TextAnalyzer {

    private Task06_TextAnalyzer() {}

    /**
     * Return the {@code n} most frequent words, ties broken alphabetically (ascending).
     * <ul>
     *   <li>Empty / null / blank text → empty list.</li>
     *   <li>{@code n <= 0} → empty list.</li>
     *   <li>{@code n >= uniqueWords} → all words, ranked.</li>
     * </ul>
     * <pre>
     *   topN("a a b c c c d d d d", 2) → ["d", "c"]
     *   topN("b a c", 3)              → ["a", "b", "c"]     (all count 1 → alphabetical)
     * </pre>
     * Hint: split on {@code "[^\\p{L}\\p{Nd}]+"}, lowercase, group+count,
     * sort entries by value DESC then key ASC, limit, map to key.
     */
    public static List<String> topN(String text, int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Count each letter (a-z, case-insensitive). Non-letters are ignored.
     * {@code "Hello, World!"} → {@code {'h': 1, 'e': 1, 'l': 3, 'o': 2, 'w': 1, 'r': 1, 'd': 1}}.
     * Empty / null text → empty map.
     * Hint: {@code text.toLowerCase().chars().filter(Character::isLetter)
     *            .mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))}
     * — but you'll need to cast the {@code Map<Character, Long>} properly.
     */
    public static Map<Character, Long> letterFrequency(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Average word length across all words.
     * <ul>
     *   <li>{@code "hi there"} → {@code OptionalDouble.of(2.5)} (2 + 5 / 2 = 3.5? NO — 2 and 5 are lengths; average is 3.5)</li>
     *   <li>Wait — "hi" length 2, "there" length 5; average is {@code (2 + 5) / 2.0 = 3.5}.</li>
     *   <li>Empty / null / blank text → {@code OptionalDouble.empty()}.</li>
     * </ul>
     * Hint: split, mapToInt(String::length), average(). Use {@code .stream().average()} —
     * do NOT box unnecessarily.
     */
    public static OptionalDouble averageWordLength(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the distinct words in the text, sorted by length DESC, then alphabetically ASC.
     * Empty / null / blank text → empty list.
     * <ul>
     *   <li>{@code "a bb ccc bb"} → {@code ["ccc", "bb", "a"]}</li>
     * </ul>
     */
    public static List<String> distinctWordsSortedByLengthDesc(String text) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}