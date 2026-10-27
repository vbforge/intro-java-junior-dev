package com.vbforge.streams.investigation;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * INVESTIGATION — {@link java.util.stream.Collectors}.
 */
public final class CollectorsDemo {

    private CollectorsDemo() {}

    public record Person(String name, int age) {}

    // ---------------------------------------------------------------------
    // Basic collectors
    // ---------------------------------------------------------------------

    /** {@code toList()} — mutable ArrayList (implementation detail; not guaranteed by spec). */
    public static List<Integer> toList() {
        return IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());
    }

    /** {@code toSet()} — order NOT preserved. */
    public static Set<Integer> toSet() {
        return Stream.of(3, 1, 2, 1, 3).collect(Collectors.toSet());
    }

    /**
     * {@code toMap(keyFn, valueFn)} — throws {@link IllegalStateException} on duplicate keys.
     * Use the 3-arg overload ({@code (k, v1, v2) -> resolved}) to pick a winner.
     */
    public static boolean toMapDuplicateThrows() {
        try {
            Stream.of("a", "A").collect(Collectors.toMap(String::toLowerCase, String::length));
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    public static Map<String, Integer> toMapWithMerge() {
        return Stream.of("a", "A", "b").collect(
                Collectors.toMap(
                        String::toLowerCase,
                        String::length,
                        Integer::sum                        // on collision, sum the values
                )
        );                                              // {a=2, b=1}
    }

    // ---------------------------------------------------------------------
    // Numeric collectors
    // ---------------------------------------------------------------------

    public static long count(List<String> names) {
        return names.stream().collect(Collectors.counting());
    }

    public static int sumOfAges(List<Person> people) {
        return people.stream().collect(Collectors.summingInt(Person::age));
    }

    public static double averageAge(List<Person> people) {
        return people.stream().collect(Collectors.averagingInt(Person::age)); // 0.0 if empty
    }

    public static int maxAge(List<Person> people) {
        return people.stream().mapToInt(Person::age).max().orElse(0);
    }

    // ---------------------------------------------------------------------
    // groupingBy / partitioningBy
    // ---------------------------------------------------------------------

    /** {@code groupingBy} with the default downstream collector {@code toList}. */
    public static Map<Integer, List<String>> byLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(String::length));
    }

    /** {@code groupingBy} with a counting downstream — {@code Map<K, Long>}. */
    public static Map<Integer, Long> countByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
    }

    /** {@code groupingBy} with {@code mapping} — extract a different view per group. */
    public static Map<Boolean, List<String>> namesByAdultStatus(List<Person> people) {
        return people.stream().collect(
                Collectors.partitioningBy(
                        p -> p.age() >= 18,
                        Collectors.mapping(Person::name, Collectors.toList())
                )
        );
    }

    /**
     * {@code partitioningBy} — ALWAYS returns a map with both keys {@code true} and {@code false}.
     * Contrast with {@code groupingBy}, which only has keys for groups that actually occurred.
     */
    public static Map<Boolean, List<Integer>> evenOddAlwaysHasBothKeys(List<Integer> nums) {
        return nums.stream().collect(
                Collectors.partitioningBy(n -> n % 2 == 0)
        );
    }

    // ---------------------------------------------------------------------
    // joining
    // ---------------------------------------------------------------------

    public static String joinedSimple(List<String> words) {
        return words.stream().collect(Collectors.joining());       // "abcdef"
    }

    public static String joinedWithDelimiter(List<String> words) {
        return words.stream().collect(Collectors.joining(", "));   // "a, b, c"
    }

    public static String joinedWithPrefixSuffix(List<String> words) {
        return words.stream().collect(Collectors.joining(", ", "[", "]")); // "[a, b, c]"
    }

    // ---------------------------------------------------------------------
    // teeing (Java 12+)
    // ---------------------------------------------------------------------

    /**
     * {@code teeing} runs TWO downstream collectors and merges their results with a BiFunction.
     * Useful when you need two aggregations in one pass.
     */
    public static String minAndMaxSummary(List<Integer> nums) {
        return nums.stream().collect(
                Collectors.teeing(
                        Collectors.minBy(Integer::compare),
                        Collectors.maxBy(Integer::compare),
                        (min, max) -> min.map(Object::toString).orElse("?") + ".." + max.map(Object::toString).orElse("?")
                )
        );                                              // e.g. "1..5"
    }
}