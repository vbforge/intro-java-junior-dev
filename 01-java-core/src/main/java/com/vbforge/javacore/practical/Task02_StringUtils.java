package com.vbforge.javacore.practical;

/**
 * PRACTICAL TASK 02 — Strings.
 */
public final class Task02_StringUtils {

    private Task02_StringUtils() {}

    /**
     * Return the reverse of {@code s}. {@code "hello" → "olleh"}, {@code "" → ""}.
     * Must be O(n). Hint: use {@link StringBuilder}.
     */
    public static String reverse(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return {@code true} if {@code s} is a palindrome, ignoring case and whitespace.
     * <ul>
     *   <li>{@code "racecar" → true}</li>
     *   <li>{@code "A man a plan a canal Panama" → true}</li>
     *   <li>{@code "hello" → false}</li>
     *   <li>{@code "" → true}</li>
     * </ul>
     */
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Count the vowels ({@code a, e, i, o, u}, case-insensitive) in {@code s}.
     * Hint: {@code switch} expression with grouped cases is elegant, or use a helper with {@code indexOf}.
     */
    public static int countVowels(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Capitalize the first letter of every whitespace-separated word, lowercase the rest.
     * <ul>
     *   <li>{@code "hello world" → "Hello World"}</li>
     *   <li>{@code "jAVA rocks" → "Java Rocks"}</li>
     *   <li>{@code "" → ""}</li>
     * </ul>
     * Hint: split on whitespace, transform each token, then {@link String#join}.
     */
    public static String capitalizeWords(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}