package com.vbforge.streams.practical;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * PRACTICAL TASK 01 — Lambdas and method references.
 *
 * <p>Return lambdas (or method references) matching the Javadoc for each method.
 * Do NOT pre-create objects — build the function and return it.
 */
public final class Task01_LambdaBasics {

    private Task01_LambdaBasics() {}

    /**
     * Returns a function that parses a base-10 integer.
     * @throws NumberFormatException when input is not a valid integer (from {@code Integer.parseInt}).
     */
    public static Function<String, Integer> parseToInt() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a predicate that is true iff the string is non-null and not empty.
     * ({@code null} → false; {@code ""} → false; {@code " "} → true.)
     */
    public static Predicate<String> isNotEmpty() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a supplier that creates a fresh, empty, MUTABLE {@link java.util.ArrayList} on each call.
     */
    public static Supplier<List<String>> newStringList() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a consumer that appends "!" to a StringBuilder.
     * (Given a StringBuilder with "hi", calling {@code accept} results in "hi!".)
     */
    public static Consumer<StringBuilder> appendBang() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Returns a function that returns the larger of two integers.
     * Use {@code Integer.compare} semantics (may return either value on ties — tests don't require a specific tie behavior).
     */
    public static BiFunction<Integer, Integer, Integer> maxOfTwo() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Comparator: ascending by string length, then alphabetical (natural order) for ties.
     * <ul>
     *   <li>{@code ["ccc", "a", "bb", "aa", "b"]} → {@code ["a", "b", "aa", "bb", "ccc"]}</li>
     * </ul>
     */
    public static Comparator<String> byLengthThenAlpha() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}