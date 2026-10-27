package com.vbforge.streams.investigation;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * INVESTIGATION — Standard functional interfaces in {@code java.util.function}.
 *
 * <p>Signature cheat-sheet:
 * <pre>
 *   Function<T, R>       T → R
 *   BiFunction<T, U, R>  (T, U) → R
 *   UnaryOperator<T>     T → T                (Function<T, T>)
 *   BinaryOperator<T>    (T, T) → T           (BiFunction<T, T, T>)
 *   Predicate<T>         T → boolean
 *   BiPredicate<T, U>    (T, U) → boolean
 *   Consumer<T>          T → void
 *   BiConsumer<T, U>     (T, U) → void
 *   Supplier<T>          () → T
 *
 *   Primitive variants exist to avoid boxing:
 *     IntFunction<R>, ToIntFunction<T>, IntPredicate, IntSupplier, IntUnaryOperator, ...
 * </pre>
 */
public final class FunctionalInterfacesDemo {

    private FunctionalInterfacesDemo() {}

    // ---------------------------------------------------------------------
    // Function family
    // ---------------------------------------------------------------------

    public static Function<String, Integer> length()     { return String::length; }

    /** Chain two functions with {@code andThen} — f then g. */
    public static Function<String, Integer> lengthTimesTwo() {
        return ((Function<String, Integer>) String::length).andThen(n -> n * 2);
    }

    /** {@code compose} is the mirror image of {@code andThen} — g then f. */
    public static Function<Integer, Integer> composeDemo() {
        Function<Integer, Integer> timesTwo = x -> x * 2;
        Function<Integer, Integer> plusOne  = x -> x + 1;
        return timesTwo.compose(plusOne);               // 5 → 5+1=6 → 6*2=12
    }

    public static UnaryOperator<String> trim() {
        return String::trim;
    }

    public static BiFunction<Integer, Integer, Integer> sum() {
        return Integer::sum;
    }

    public static BinaryOperator<Integer> max() {
        return Integer::max;
    }

    // ---------------------------------------------------------------------
    // Predicate family
    // ---------------------------------------------------------------------

    public static Predicate<String> startsWithA() {
        return s -> s != null && s.startsWith("A");
    }

    /** Compose with {@code and} / {@code or} / {@code negate}. */
    public static Predicate<String> startsWithAAndLongerThan2() {
        return startsWithA().and(s -> s.length() > 2);
    }

    public static Predicate<Integer> isEven() {
        return x -> x % 2 == 0;
    }

    // ---------------------------------------------------------------------
    // Consumer family
    // ---------------------------------------------------------------------

    /** A Consumer writes into a StringBuilder side-effect style (for demos). */
    public static Consumer<String> appender(StringBuilder sb) {
        return sb::append;
    }

    // ---------------------------------------------------------------------
    // Supplier family
    // ---------------------------------------------------------------------

    public static Supplier<StringBuilder> newBuilder() {
        return StringBuilder::new;
    }

    // ---------------------------------------------------------------------
    // Primitive specializations — no boxing, faster
    // ---------------------------------------------------------------------

    /** {@code IntPredicate} — takes a primitive int, avoids Integer autoboxing. */
    public static IntPredicate positive() {
        return x -> x > 0;
    }

    // ---------------------------------------------------------------------
    // Demo helpers
    // ---------------------------------------------------------------------

    public static int composeAppliedTo5() {
        return composeDemo().apply(5);                  // 12
    }

    /**
     * {@code startsWithAAndLongerThan2()} = starts with "A" AND length > 2.
     *
     * <ul>
     *   <li>{@code "Apple"} → true   (starts with A, length 5 > 2)</li>
     *   <li>{@code "App"}   → true   (starts with A, length 3 > 2)</li>
     *   <li>{@code "Ap"}    → false  (starts with A, but length 2 is NOT > 2)</li>
     *   <li>{@code "Bob"}   → false  (doesn't start with A)</li>
     * </ul>
     */
    public static boolean predicateComposition() {
        Predicate<String> p = startsWithAAndLongerThan2();
        return  p.test("Apple")        // true
                && p.test("App")          // true  — length 3 IS > 2
                && !p.test("Ap")          // false — length 2 is NOT > 2
                && !p.test("Bob");        // false — wrong first letter
    }
}