package com.vbforge.streams.investigation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * INVESTIGATION — Ways to create streams.
 */
public final class StreamCreationDemo {

    private StreamCreationDemo() {}

    /** From a collection. */
    public static List<Integer> fromList() {
        return List.of(1, 2, 3, 4, 5).stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());          // [2, 4]
    }

    /** From an object array via {@code Arrays.stream}. */
    public static List<String> fromObjectArray() {
        String[] words = {"a", "bb", "ccc"};
        return Arrays.stream(words)
                .map(String::toUpperCase)
                .collect(Collectors.toList());          // [A, BB, CCC]
    }

    /**
     * From a PRIMITIVE array. Note: {@code Stream.of(intArray)} would give
     * a stream of ONE element (the array); {@code Arrays.stream(intArray)}
     * gives the correct stream of ints.
     */
    public static int sumOfPrimitiveArray() {
        int[] nums = {1, 2, 3, 4};
        return Arrays.stream(nums).sum();               // 10
    }

    /**
     * GOTCHA demonstration: {@code Stream.of(int[])} — the varargs parameter type
     * is {@code T[]}, and {@code int[]} is not an {@code Integer[]}, so Java
     * treats the whole array as a SINGLE element.
     */
    public static int countOfStreamOfPrimitiveArray() {
        int[] nums = {1, 2, 3, 4};
        return (int) Stream.of(nums).count();           // 1 (the array is one element)
    }

    /** {@code Stream.of(T...)} — the varargs form works for object types. */
    public static List<String> fromVarargs() {
        return Stream.of("x", "y", "z").collect(Collectors.toList());
    }

    /** {@code Stream.empty()} — an empty stream. */
    public static long emptyCount() {
        return Stream.empty().count();                  // 0
    }

    /** {@code Stream.iterate(seed, f)} — infinite stream, use {@code limit}. */
    public static List<Integer> firstFivePowersOfTwo() {
        return Stream.iterate(1, x -> x * 2)
                .limit(5)
                .collect(Collectors.toList());          // [1, 2, 4, 8, 16]
    }

    /** {@code Stream.iterate(seed, hasNext, next)} (Java 9+) — finite. */
    public static List<Integer> iterateWithPredicate() {
        return Stream.iterate(1, x -> x < 10, x -> x + 2)
                .collect(Collectors.toList());          // [1, 3, 5, 7, 9]
    }

    /** {@code IntStream.range} excludes the upper bound; {@code rangeClosed} includes it. */
    public static List<Integer> rangeExclusive() {
        return IntStream.range(0, 5).boxed().collect(Collectors.toList());     // [0, 1, 2, 3, 4]
    }

    public static List<Integer> rangeInclusive() {
        return IntStream.rangeClosed(0, 5).boxed().collect(Collectors.toList()); // [0, 1, 2, 3, 4, 5]
    }

    /** {@code Stream.builder()} — build a stream element-by-element. */
    public static List<String> builderStream() {
        return Stream.<String>builder()
                .add("a").add("b").add("c")
                .build()
                .collect(Collectors.toList());
    }

    /** {@code Stream.concat} — concatenate two streams. */
    public static List<Integer> concatStreams() {
        return Stream.concat(Stream.of(1, 2), Stream.of(3, 4))
                .collect(Collectors.toList());          // [1, 2, 3, 4]
    }

    /** Primitive stream from {@code IntStream} over a range, useful for sums. */
    public static long sumRangeClosed(int n) {
        return LongStream.rangeClosed(1, n).sum();      // e.g. n=5 → 15
    }
}