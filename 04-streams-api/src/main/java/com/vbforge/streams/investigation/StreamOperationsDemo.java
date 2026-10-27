package com.vbforge.streams.investigation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * INVESTIGATION — Common intermediate and terminal stream operations.
 */
public final class StreamOperationsDemo {

    private StreamOperationsDemo() {}

    /** {@code filter} keeps elements matching the predicate. */
    public static List<Integer> evens(List<Integer> input) {
        return input.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
    }

    /** {@code map} transforms each element. */
    public static List<Integer> doubled(List<Integer> input) {
        return input.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
    }

    /**
     * {@code flatMap} flattens a "stream of streams" into a single stream.
     * Equivalent to nested loops, but composable with the rest of the pipeline.
     */
    public static List<String> splitWords(List<String> lines) {
        return lines.stream()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .collect(Collectors.toList());
    }

    /** {@code distinct} removes duplicates using {@code equals} (so element order matters — it's stable). */
    public static List<Integer> distinct(List<Integer> input) {
        return input.stream().distinct().collect(Collectors.toList());
    }

    /**
     * {@code sorted()} sorts by natural order. It's STABLE — equal elements keep their
     * original relative order (which matters for objects, not so much for ints).
     */
    public static List<Integer> sorted(List<Integer> input) {
        return input.stream().sorted().collect(Collectors.toList());
    }

    /** {@code sorted(Comparator)} sorts by a custom order. */
    public static List<String> sortedByLengthThenAlpha(List<String> input) {
        return input.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    /** {@code limit(n)} keeps at most the first {@code n} elements. */
    public static List<Integer> firstThree(List<Integer> input) {
        return input.stream().limit(3).collect(Collectors.toList());
    }

    /** {@code skip(n)} drops the first {@code n} elements. */
    public static List<Integer> skipTwo(List<Integer> input) {
        return input.stream().skip(2).collect(Collectors.toList());
    }

    /**
     * {@code takeWhile} (Java 9+) takes elements while the predicate holds — then STOPS at the first failure.
     * On ordered sequential streams this behaves as you'd expect; on unordered streams the result is unspecified.
     */
    public static List<Integer> takeWhileLessThan3(List<Integer> input) {
        return input.stream()
                .takeWhile(x -> x < 3)
                .collect(Collectors.toList());
    }

    /** {@code dropWhile} skips while the predicate holds — then keeps everything from the first failure. */
    public static List<Integer> dropWhileLessThan3(List<Integer> input) {
        return input.stream()
                .dropWhile(x -> x < 3)
                .collect(Collectors.toList());
    }

    /**
     * {@code reduce}: fold the stream into a single value.
     * <ul>
     *   <li>1-arg form: returns {@link Optional} because the stream might be empty.</li>
     *   <li>2-arg form: takes an identity; empty stream returns the identity.</li>
     * </ul>
     */
    public static Optional<Integer> sumViaReduce(List<Integer> input) {
        return input.stream().reduce(Integer::sum);     // empty → Optional.empty()
    }

    public static int sumViaReduceWithIdentity(List<Integer> input) {
        return input.stream().reduce(0, Integer::sum);  // empty → 0
    }

    /**
     * {@code mapToInt} gives an {@code IntStream} — primitive arithmetic without boxing.
     * Then {@code sum()}, {@code average()}, {@code max()} are O(1) at the end.
     */
    public static int sumAsInt(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).sum();
    }

    public static Optional<Double> averageAsDouble(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).average().stream().boxed().findFirst();
    }

    /** {@code findFirst} on a sequential stream — deterministic. */
    public static Optional<Integer> firstPositive(List<Integer> input) {
        return input.stream().filter(x -> x > 0).findFirst();
    }

    /** {@code anyMatch} / {@code allMatch} / {@code noneMatch} — boolean terminals. */
    public static boolean anyEven(List<Integer> input) {
        return input.stream().anyMatch(x -> x % 2 == 0);
    }

    public static boolean allPositive(List<Integer> input) {
        return input.stream().allMatch(x -> x > 0);
    }

    public static boolean noneNegative(List<Integer> input) {
        return input.stream().noneMatch(x -> x < 0);
    }

    /** {@code sum} of character codes via {@code flatMapToInt} + {@code chars()}. */
    public static int sumOfCharCodes(String[] words) {
        return Stream.of(words)
                .flatMapToInt(CharSequence::chars)
                .sum();
    }

    /** {@code boxed()} converts a primitive stream back to a stream of wrappers. */
    public static List<Integer> boxedRange() {
        return IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());
    }
}