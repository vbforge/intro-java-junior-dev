package com.vbforge.streams.practical;

import java.util.List;

/**
 * PRACTICAL TASK 02 — {@code filter} and {@code map} pipelines.
 *
 * <p>Every method takes a non-null input list and returns a NEW list.
 * Do NOT modify the input.
 */
public final class Task02_FilterMap {

    private Task02_FilterMap() {}

    /**
     * Return the squares of all EVEN numbers, in the original order.
     * {@code [1, 2, 3, 4, 5, 6]} → {@code [4, 16, 36]}.
     */
    public static List<Integer> evenSquares(List<Integer> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the upper-cased versions of all NON-BLANK strings, in the original order.
     * Blanks are strings that are null or {@link String#isBlank()}.
     * {@code ["a", " ", "bb", null, "ccc"]} → {@code ["A", "BB", "CCC"]}.
     */
    public static List<String> nonBlankUpper(List<String> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return distinct elements sorted ascending.
     * {@code [3, 1, 2, 1, 3]} → {@code [1, 2, 3]}.
     */
    public static List<Integer> distinctSorted(List<Integer> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the first names from a list of "First Last" strings (with the first letter of the first name
     * preserved, everything else the same). A string with no space is treated as a first name with nothing else.
     * {@code ["Alice Smith", "Bob"]} → {@code ["Alice", "Bob"]}.
     */
    public static List<String> firstNames(List<String> fullNames) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}