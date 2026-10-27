package com.vbforge.algorithms.investigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * INVESTIGATION — Classic recursion patterns.
 *
 * <p>Every recursive method has two parts:
 * <ol>
 *   <li><b>Base case</b> — the stopping condition (the "no more work" case).</li>
 *   <li><b>Recursive case</b> — reduce the problem, call yourself with the smaller input.</li>
 * </ol>
 * Forgetting the base case → {@link StackOverflowError}.
 */
public final class RecursionDemo {

    private RecursionDemo() {}

    // ---------------------------------------------------------------------
    // Simple linear recursion
    // ---------------------------------------------------------------------

    /** {@code n!} — linear recursion. {@code 0! = 1}. Negative → IllegalArgumentException. */
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n == 0) return 1;                           // base case
        return n * factorial(n - 1);                    // recursive case
    }

    /** Sum of the decimal digits of a non-negative int, recursively. */
    public static int sumDigits(int n) {
        if (n < 10) return n;                           // base case — single digit
        return n % 10 + sumDigits(n / 10);              // last digit + recurse on the rest
    }

    /** GCD via Euclid — a beautiful O(log(min(a, b))) recursion. */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) return a;                           // base case
        return gcd(b, a % b);                           // recursive case — shrink the pair
    }

    // ---------------------------------------------------------------------
    // Fibonacci — naive vs memoized
    // ---------------------------------------------------------------------

    /**
     * Naive Fibonacci — exponential time (roughly O(φⁿ)). For n = 40, that's ~2 billion
     * calls. This is the poster child for "add memoization".
     */
    public static long fibNaive(int n) {
        if (n < 2) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    /**
     * Memoized Fibonacci — O(n) time, O(n) space. Every subproblem is computed once
     * and cached. Same recursion, but 40 is now instant.
     */
    public static long fibMemo(int n) {
        return fibMemoHelper(n, new HashMap<>());
    }

    private static long fibMemoHelper(int n, Map<Integer, Long> memo) {
        if (n < 2) return n;
        Long cached = memo.get(n);
        if (cached != null) return cached;
        long value = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        memo.put(n, value);
        return value;
    }

    // ---------------------------------------------------------------------
    // Tower of Hanoi — classic exponential recursion
    // ---------------------------------------------------------------------

    /**
     * Minimum number of moves to solve the Tower of Hanoi with {@code n} disks — exactly
     * {@code 2ⁿ - 1}. The recursive solution is:
     * <ol>
     *   <li>Move n-1 disks to the auxiliary peg.</li>
     *   <li>Move the largest disk to the target peg.</li>
     *   <li>Move n-1 disks from auxiliary to target.</li>
     * </ol>
     */
    public static long hanoiMoves(int n) {
        if (n <= 0) return 0;
        return 2 * hanoiMoves(n - 1) + 1;               // equals 2ⁿ - 1
    }

    // ---------------------------------------------------------------------
    // Permutations — backtracking recursion
    // ---------------------------------------------------------------------

    /**
     * All permutations of a string (assumes distinct characters — otherwise duplicates appear).
     * Uses backtracking: fix a character at position {@code from}, permute the rest, undo.
     */
    public static List<String> permutations(String s) {
        List<String> results = new ArrayList<>();
        if (s == null) return results;
        permute(s.toCharArray(), 0, results);
        return results;
    }

    private static void permute(char[] chars, int from, List<String> results) {
        if (from == chars.length) {                     // base case — one permutation complete
            results.add(new String(chars));
            return;
        }
        for (int i = from; i < chars.length; i++) {
            swap(chars, from, i);
            permute(chars, from + 1, results);
            swap(chars, from, i);                       // backtrack
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}