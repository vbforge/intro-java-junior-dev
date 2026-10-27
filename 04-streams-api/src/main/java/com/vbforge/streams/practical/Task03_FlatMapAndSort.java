package com.vbforge.streams.practical;

import java.util.Comparator;
import java.util.List;

/**
 * PRACTICAL TASK 03 — {@code flatMap} and {@code sorted}.
 */
public final class Task03_FlatMapAndSort {

    private Task03_FlatMapAndSort() {}

    /**
     * Flatten a list of lists into a single list, preserving encounter order.
     * {@code [[1, 2], [3], [], [4, 5]]} → {@code [1, 2, 3, 4, 5]}.
     */
    public static List<Integer> flatten(List<List<Integer>> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Split each line into words on whitespace and return all words in order.
     * {@code ["hello world", "java streams"]} → {@code ["hello", "world", "java", "streams"]}.
     * Empty lines contribute no words.
     */
    public static List<String> wordsFromLines(List<String> lines) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Sort a list of strings by length ASCENDING, then alphabetically ASCENDING for ties.
     * {@code ["ccc", "a", "bb", "aa", "b"]} → {@code ["a", "b", "aa", "bb", "ccc"]}.
     */
    public static List<String> sortByLengthThenAlpha(List<String> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Sort a list of integers DESCENDING.
     * {@code [3, 1, 4, 1, 5]} → {@code [5, 4, 3, 1, 1]}.
     */
    public static List<Integer> sortDescending(List<Integer> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the sort of strings by length DESCENDING using the SUPPLIED comparator
     * trick: build a {@link Comparator} locally and pass it to {@code sorted}.
     * {@code ["a", "bbb", "cc"]} → {@code ["bbb", "cc", "a"]}.
     */
    public static List<String> sortByLengthDescending(List<String> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}