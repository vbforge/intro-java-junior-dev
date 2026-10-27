package com.vbforge.javacore.investigation;

/**
 * INVESTIGATION — Strings, StringBuilder, and text blocks.
 */
public final class StringsDemo {

    private StringsDemo() {}

    // ---------------------------------------------------------------------
    // Immutability
    // ---------------------------------------------------------------------

    /**
     * Strings are immutable. Every "modifying" method returns a NEW string.
     * Note: the local variable {@code s} is reassigned — the original object is untouched.
     */
    public static String uppercaseReturnsNewString() {
        String s = "hello";
        String upper = s.toUpperCase();    // returns "HELLO"; s stays "hello"
        return upper;                       // "HELLO"
    }

    // ---------------------------------------------------------------------
    // String pool
    // ---------------------------------------------------------------------

    /**
     * String literals are interned in a constant pool. Two identical literals
     * point to the SAME object, so {@code ==} is true (do NOT rely on this in logic —
     * use {@code .equals}).
     */
    public static boolean literalsAreSameReference() {
        String a = "abc";
        String b = "abc";
        return a == b;                      // true (same interned instance)
    }

    /**
     * Explicit {@code new String(...)} bypasses the pool.
     */
    public static boolean newStringIsNotPooled() {
        String a = "abc";
        String b = new String("abc");
        return a == b;                      // false
    }

    // ---------------------------------------------------------------------
    // Common methods — expected outputs
    // ---------------------------------------------------------------------

    public static int lengthOf(String s)         { return s.length(); }
    public static char charAt(String s, int i)   { return s.charAt(i); }
    public static String substring(String s, int from, int to) { return s.substring(from, to); }
    public static boolean contains(String s, String needle)    { return s.contains(needle); }
    public static String trimmed(String s)       { return s.trim(); }
    public static String[] splitOnComma(String s){ return s.split(","); }
    public static String joined(String[] parts)  { return String.join(",", parts); }

    // ---------------------------------------------------------------------
    // StringBuilder — efficient in-place mutation
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: never concatenate Strings in a loop with {@code +=}.
     * Each {@code +=} allocates a new String — O(n^2) overall.
     * Use {@code StringBuilder} instead: O(n).
     */
    public static String repeatInefficient(String base, int times) {
        String s = "";
        for (int i = 0; i < times; i++) s += base;    // O(n^2) — for demonstration only
        return s;
    }

    public static String repeatEfficient(String base, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(base);
        return sb.toString();                           // O(n)
    }

    public static String reverseWithBuilder(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // ---------------------------------------------------------------------
    // Text blocks (Java 15+) — multi-line string literals
    // ---------------------------------------------------------------------

    /**
     * Text blocks preserve newlines. The common indentation (the smallest indent of any
     * non-blank line) is stripped automatically. This one has 8 leading spaces before
     * each line, so 8 spaces are removed from every line.
     */
    public static String textBlock() {
        return """
                line one
                line two
                line three""";
    }
}