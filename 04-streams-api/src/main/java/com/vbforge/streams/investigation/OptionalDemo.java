package com.vbforge.streams.investigation;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * INVESTIGATION — {@link Optional}.
 *
 * <p>{@code Optional} is a container that may or may not hold a non-null value.
 * It was introduced in Java 8 primarily as a RETURN TYPE for methods that might
 * legitimately produce "no value" — a way to make that possibility visible in the
 * type system.
 *
 * <p><b>Not</b> recommended as a field type or a collection element; not a
 * general-purpose "null replacement".
 */
public final class OptionalDemo {

    private OptionalDemo() {}

    // ---------------------------------------------------------------------
    // Creation
    // ---------------------------------------------------------------------

    /** {@code Optional.of(value)} — non-null value. Passing null throws NPE. */
    public static Optional<String> nonEmpty() {
        return Optional.of("hello");
    }

    /** {@code Optional.ofNullable(value)} — accepts null. */
    public static Optional<String> ofNullableNull() {
        return Optional.ofNullable(null);               // Optional.empty()
    }

    /** {@code Optional.empty()} — the canonical empty instance. */
    public static Optional<String> empty() {
        return Optional.empty();
    }

    public static boolean ofRejectsNull() {
        try {
            Optional.of(null);
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }

    // ---------------------------------------------------------------------
    // orElse vs orElseGet — the eager-vs-lazy distinction
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: {@code orElse(x)} EVALUATES {@code x} eagerly, even if the Optional is non-empty.
     * The argument is just a value — a plain Java expression — so the method call happens
     * before {@code orElse} is even invoked.
     */
    public static int orElseIsEager() {
        int[] evaluations = {0};
        Optional.of("value").orElse(countCall(evaluations));
        return evaluations[0];                          // 1 — orElse argument WAS evaluated
    }

    /**
     * {@code orElseGet(supplier)} is LAZY — the supplier only runs if the Optional
     * is empty. This is what you want when the fallback is expensive.
     */
    public static int orElseGetIsLazy() {
        int[] evaluations = {0};
        Optional.of("value").orElseGet(() -> countCall(evaluations));
        return evaluations[0];                          // 0 — supplier NOT called
    }

    private static String countCall(int[] counter) {
        counter[0]++;
        return "fallback";
    }

    // ---------------------------------------------------------------------
    // map vs flatMap
    // ---------------------------------------------------------------------

    /** {@code map} applies a function to the value (if present). Wraps the result in Optional. */
    public static Optional<Integer> length(String s) {
        return Optional.ofNullable(s).map(String::length);
    }

    /** {@code flatMap} is for functions that ALREADY return Optional — avoids {@code Optional<Optional<...>>}. */
    public static Optional<Integer> parseAndDouble(String s) {
        return parseInt(s).map(x -> x * 2);
    }

    public static Optional<Integer> parseInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException | NullPointerException e) {
            return Optional.empty();
        }
    }

    // ---------------------------------------------------------------------
    // Consumers
    // ---------------------------------------------------------------------

    /**
     * {@code ifPresentOrElse} (Java 9+) — run one action if present, another if empty.
     * Note: its return is void; use the returned StringBuilder for testing.
     */
    public static String describe(Optional<String> opt) {
        StringBuilder sb = new StringBuilder();
        opt.ifPresentOrElse(
                v -> sb.append("value=").append(v),
                () -> sb.append("empty")
        );
        return sb.toString();
    }

    // ---------------------------------------------------------------------
    // Extractors
    // ---------------------------------------------------------------------

    /** {@code orElseThrow} — throw a supplier-provided exception if empty. */
    public static String orElseThrow(Optional<String> opt, Supplier<? extends RuntimeException> factory) {
        return opt.orElseThrow(factory);
    }

    /** {@code get()} on empty throws {@link NoSuchElementException}. Avoid it; prefer {@code orElseThrow}. */
    public static boolean getOnEmptyThrows() {
        try {
            Optional.empty().get();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}