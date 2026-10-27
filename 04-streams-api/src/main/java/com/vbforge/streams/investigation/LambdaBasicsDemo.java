package com.vbforge.streams.investigation;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * INVESTIGATION — Lambda basics and method references.
 *
 * <p>A lambda is a compact implementation of a FUNCTIONAL INTERFACE — an interface
 * with exactly one abstract method. Its "target type" is that interface; lambdas
 * cannot exist in isolation.
 *
 * <pre>
 *   x -> x + 1
 *   (a, b) -> a * b
 *   (String s) -> s.toUpperCase()
 *   () -> 42
 *   x -> { System.out.println(x); return x * 2; }
 * </pre>
 */
public final class LambdaBasicsDemo {

    private LambdaBasicsDemo() {}

    // ---------------------------------------------------------------------
    // Basic lambdas
    // ---------------------------------------------------------------------

    public static Function<Integer, Integer> increment() {
        return x -> x + 1;
    }

    public static Predicate<String> isEmpty() {
        return String::isEmpty;                         // method reference — cleaner than s -> s.isEmpty()
    }

    public static Supplier<String> greeting() {
        return () -> "hello";
    }

    public static IntBinaryOperator add() {
        return (a, b) -> a + b;
    }

    // ---------------------------------------------------------------------
    // Effectively final capture
    // ---------------------------------------------------------------------

    /**
     * A lambda can capture a LOCAL variable from the enclosing scope, but only if
     * that variable is <b>effectively final</b> — never reassigned after its
     * initial assignment. If you reassign {@code prefix}, the code doesn't compile.
     */
    public static Function<String, String> prefixedWith(String prefix) {
        // 'prefix' is a parameter — effectively final because we never reassign it
        return s -> prefix + s;
    }

    public static String captureDemo() {
        String greeting = "Hi ";                        // effectively final
        Function<String, String> f = s -> greeting + s;
        return f.apply("there");                        // "Hi there"
    }

    // ---------------------------------------------------------------------
    // The four kinds of method references
    // ---------------------------------------------------------------------

    // 1) Static method —    ClassName::staticMethod
    public static Function<String, Integer> staticRef() {
        return Integer::parseInt;
    }

    // 2) Bound instance —   instance::instanceMethod  (a FIXED receiver)
    public static Supplier<String> boundInstanceRef() {
        String s = "hello";
        return s::toUpperCase;                          // always calls 's.toUpperCase()'
    }

    // 3) Unbound instance — ClassName::instanceMethod  (receiver is the first arg)
    public static Function<String, String> unboundInstanceRef() {
        return String::toUpperCase;                     // arg is the receiver, e.g. "abc" -> "abc".toUpperCase()
    }

    // 4) Constructor —      ClassName::new
    public static Function<String, StringBuilder> constructorRef() {
        return StringBuilder::new;
    }

    // ---------------------------------------------------------------------
    // Common conversion: compare via Integer.compare (not subtraction)
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: {@code (a, b) -> a - b} overflows for very large ints. Use
     * {@code Integer.compare(a, b)} — it never overflows and expresses intent.
     */
    public static int safeCompare(int a, int b) {
        return Integer.compare(a, b);
    }
}