package com.vbforge.streams.practical;

import java.util.List;
import java.util.Optional;

/**
 * PRACTICAL TASK 04 — {@code Optional} chaining.
 */
public final class Task04_OptionalChain {

    private Task04_OptionalChain() {}

    /**
     * Parse a base-10 integer, returning {@code Optional.empty()} on failure or null.
     */
    public static Optional<Integer> parseIntOrEmpty(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Parse an integer, returning {@code defaultVal} if parsing fails or input is null.
     * Use {@link Optional#orElse(Object)} — or {@link Optional#orElseGet(java.util.function.Supplier)}
     * if you prefer the lazy form (either is fine here — the default is cheap).
     */
    public static int parseIntOrDefault(String s, int defaultVal) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Parse an integer, keep it if positive, then double it.
     * <ul>
     *   <li>{@code "21"} → {@code Optional.of(42)}</li>
     *   <li>{@code "0"} → {@code Optional.empty()}</li>
     *   <li>{@code "-5"} → {@code Optional.empty()}</li>
     *   <li>{@code "abc"} → {@code Optional.empty()}</li>
     * </ul>
     * Hint: {@code filter(x -> x > 0).map(x -> x * 2)} on the parsed Optional.
     */
    public static Optional<Integer> doubleIfPositive(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the FIRST even number in the list, squared.
     * {@code [1, 3, 4, 5, 6]} → {@code Optional.of(16)}.
     * {@code [1, 3, 5]} → {@code Optional.empty()}.
     * {@code []} → {@code Optional.empty()}.
     * Hint: {@code stream().filter(...).findFirst().map(x -> x * x)}.
     */
    public static Optional<Integer> firstEvenSquared(List<Integer> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Given a possibly-null String, return the trimmed value wrapped in an Optional.
     * If input is null or blank after trimming → {@code Optional.empty()}.
     * Otherwise → {@code Optional.of(trimmed)}.
     */
    public static Optional<String> trimmedOrEmpty(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}