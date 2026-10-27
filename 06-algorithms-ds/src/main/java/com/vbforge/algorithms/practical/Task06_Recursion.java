package com.vbforge.algorithms.practical;

/**
 * PRACTICAL TASK 06 — Classic recursive methods.
 *
 * <p>Every method in this class must be implemented RECURSIVELY —
 * no loops, no helper methods with loops. The point is to practice recursion.
 * (It's fine to have a helper method that recurses — you just can't loop.)
 */
public final class Task06_Recursion {

    private Task06_Recursion() {}

    /**
     * Compute {@code base^exp} for non-negative exp, recursively.
     * <ul>
     *   <li>{@code power(2, 0)} → 1</li>
     *   <li>{@code power(2, 10)} → 1024</li>
     *   <li>{@code power(5, 1)} → 5</li>
     * </ul>
     * Hint: {@code base^exp == base * base^(exp-1)}, base case {@code exp == 0 → 1}.
     * Optional: use exponentiation-by-squaring for O(log exp).
     *
     * @throws IllegalArgumentException if {@code exp < 0}.
     */
    public static long power(int base, int exp) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Sum the decimal digits of {@code n}. {@code n >= 0}.
     * <ul>
     *   <li>{@code sumDigits(0)} → 0</li>
     *   <li>{@code sumDigits(123)} → 6</li>
     *   <li>{@code sumDigits(9999)} → 36</li>
     * </ul>
     * Hint: last digit is {@code n % 10}, "rest of digits" is {@code n / 10}.
     */
    public static int sumDigits(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Reverse a string recursively.
     * <ul>
     *   <li>{@code reverse("")} → ""</li>
     *   <li>{@code reverse("a")} → "a"</li>
     *   <li>{@code reverse("hello")} → "olleh"</li>
     *   <li>{@code reverse(null)} → ""</li>
     * </ul>
     * Hint: {@code reverse("hello") == reverse("ello") + "h"}.
     */
    public static String reverse(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * {@code true} iff {@code s} is a palindrome (case-insensitive, whitespace ignored).
     * <ul>
     *   <li>{@code isPalindrome("")} → true</li>
     *   <li>{@code isPalindrome("a")} → true</li>
     *   <li>{@code isPalindrome("racecar")} → true</li>
     *   <li>{@code isPalindrome("A man a plan a canal Panama")} → true</li>
     *   <li>{@code isPalindrome("hello")} → false</li>
     * </ul>
     * Hint: strip whitespace and lowercase FIRST, then the recursive check is
     * "{@code first == last} AND the middle is a palindrome".
     */
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Number of ways to climb a staircase of {@code n} steps, moving 1 or 2 steps at a time.
     * This is the Fibonacci sequence shifted by one.
     * <ul>
     *   <li>{@code climbWays(0)} → 1 (empty climb = 1 way)</li>
     *   <li>{@code climbWays(1)} → 1</li>
     *   <li>{@code climbWays(2)} → 2 (1+1 or 2)</li>
     *   <li>{@code climbWays(3)} → 3 (1+1+1, 1+2, 2+1)</li>
     *   <li>{@code climbWays(10)} → 89</li>
     * </ul>
     * Hint: {@code ways(n) = ways(n-1) + ways(n-2)}, base cases {@code ways(0)=1}, {@code ways(1)=1}.
     *
     * @throws IllegalArgumentException if {@code n < 0}.
     */
    public static long climbWays(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}