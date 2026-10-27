package com.vbforge.streams.practical;

import java.util.List;
import java.util.Map;

/**
 * PRACTICAL TASK 05 — Collectors.
 *
 * <p>All methods take non-null inputs. Do not modify them.
 */
public final class Task05_Collectors {

    public record Person(String name, int age) {}

    /**
     * Group words by their first character.
     * <ul>
     *   <li>Inputs are non-null and non-empty.</li>
     *   <li>Encounter order within each group must be preserved.</li>
     * </ul>
     * {@code ["apple", "avocado", "banana", "cherry", "blueberry"]} →
     * {@code {'a': ["apple", "avocado"], 'b': ["banana", "blueberry"], 'c': ["cherry"]}}.
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Count occurrences of each word (case-sensitive).
     * {@code ["a", "b", "a", "c", "a"]} → {@code {"a": 3, "b": 1, "c": 1}}.
     */
    public static Map<String, Long> countWords(List<String> words) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the names of adults (age >= 18), sorted alphabetically, joined with ", ".
     * Empty list → empty string.
     */
    public static String adultNamesCsv(List<Person> people) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Partition people into {@code true = adult (age >= 18)} and {@code false = minor}.
     * The returned map MUST contain both keys, even if one group is empty.
     */
    public static Map<Boolean, List<Person>> partitionByAdult(List<Person> people) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Map each word to its length using {@code Collectors.toMap}.
     * On duplicate words, keep the LONGEST length seen (which is the same as the first length, since
     * duplicates have identical lengths — but the merge function is required by the spec).
     * <ul>
     *   <li>{@code ["hi", "hello", "hi"]} → {@code {"hi": 2, "hello": 5}}</li>
     * </ul>
     */
    public static Map<String, Integer> wordToLength(List<String> words) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the sum of ages as a {@code Long} via {@code Collectors.summingLong} (or summingInt + cast).
     * Empty list → 0.
     */
    public static long totalAge(List<Person> people) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}