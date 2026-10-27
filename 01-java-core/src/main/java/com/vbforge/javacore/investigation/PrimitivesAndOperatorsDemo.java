package com.vbforge.javacore.investigation;

/**
 * INVESTIGATION — Primitives, literals, casting, operators.
 *
 * <p>Java has 8 primitive types with fixed sizes on every platform:
 * <pre>
 *   byte    8-bit signed
 *   short  16-bit signed
 *   int    32-bit signed   (default integer literal type)
 *   long   64-bit signed   (needs 'L' suffix)
 *   float  32-bit IEEE-754 (needs 'f' suffix)
 *   double 64-bit IEEE-754 (default floating literal type)
 *   char   16-bit unsigned Unicode
 *   boolean true/false
 * </pre>
 *
 * <p>Each method below demonstrates one narrow behavior; the matching test verifies it.
 * Read the method body, then read the test — together they are the spec.
 */
public final class PrimitivesAndOperatorsDemo {

    private PrimitivesAndOperatorsDemo() {}

    // ---------------------------------------------------------------------
    // Division
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: {@code int / int} always returns {@code int} — the fractional part is
     * truncated toward zero, not rounded.
     */
    public static int intDivision() {
        return 7 / 2;                    // 3, not 3.5
    }

    /**
     * Cast at least one operand to get real (floating-point) division.
     */
    public static double realDivision() {
        return (double) 7 / 2;           // 3.5
    }

    /**
     * Modulo in Java follows the sign of the DIVIDEND, not the divisor.
     * This surprises people coming from Python (where 1 == -7 % 3 in Python).
     */
    public static int negativeModulo() {
        return -7 % 3;                   // -1
    }

    // ---------------------------------------------------------------------
    // Overflow and narrowing
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: integer overflow wraps around silently. No exception is thrown.
     * Use {@code Math.addExact}, {@code Math.multiplyExact}, etc. if you need
     * an {@code ArithmeticException} on overflow.
     */
    public static int integerOverflow() {
        return Integer.MAX_VALUE + 1;    // -2147483648
    }

    /**
     * GOTCHA: a narrow cast truncates the upper bits — it does NOT clamp.
     * 130 (int) is 0b...1000_0010; as a signed byte that bit pattern is -126.
     */
    public static byte narrowingCastTruncates() {
        return (byte) 130;               // -126
    }

    // ---------------------------------------------------------------------
    // Compound assignment hides casts
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: {@code x += y} is roughly {@code x = (T)(x + y)} where T is the type of x.
     * An implicit narrow cast is performed, silently truncating.
     * Contrast: {@code x = x + y} would NOT compile here (double → int).
     */
    public static int compoundAssignmentTruncates() {
        int x = 10;
        x += 3.9;                        // → 13 (the .9 is truncated)
        return x;
    }

    // ---------------------------------------------------------------------
    // Short-circuit vs non-short-circuit boolean operators
    // ---------------------------------------------------------------------

    /**
     * {@code &&} short-circuits: if the left side is {@code false}, the right side is NOT evaluated.
     */
    public static boolean logicalAndShortCircuits() {
        return false && sideEffect();    // sideEffect() NOT called
    }

    /**
     * {@code &} on booleans does NOT short-circuit: both sides are always evaluated.
     * Rarely what you want for boolean logic — prefer {@code &&}.
     */
    public static boolean bitwiseAndEvaluatesBoth() {
        return false & sideEffect();     // sideEffect() IS called (returns true)
    }

    private static boolean sideEffect() {
        return true;
    }

    // ---------------------------------------------------------------------
    // Autoboxing equality gotcha
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: Integer caches values -128..127. Two {@code new} boxings of a value in that
     * range are the SAME object (so {@code ==} returns true). Outside that range, they are
     * different objects, so {@code ==} is {@code false}. Always use {@code .equals} for wrappers.
     */
    public static boolean wrapperEqualityOutsideCache() {
        Integer a = 1000;
        Integer b = 1000;
        return a == b;                   // false
    }

    /**
     * Inside the cache range the two boxed references are identical.
     */
    public static boolean wrapperEqualityInsideCache() {
        Integer a = 100;
        Integer b = 100;
        return a == b;                   // true
    }
}