package com.vbforge.exam.practical;

import java.util.List;

/**
 * PRACTICAL TASK 05 — Recursion + Linked list + Stack.
 *
 * <p>Combines three distinct techniques from the course:
 * <ul>
 *   <li>Backtracking recursion ({@link #powerSet(int[])})</li>
 *   <li>Stack-based parsing ({@link #balancedBrackets(String)}, {@link #evaluateRPN(String[])})</li>
 *   <li>Singly-linked list manipulation ({@link IntLinkedList})</li>
 * </ul>
 */
public final class Task05_RecursionAndDS {

    private Task05_RecursionAndDS() {}

    // ---------------------------------------------------------------------
    // Power set — backtracking recursion
    // ---------------------------------------------------------------------

    /**
     * Return all subsets (the "power set") of the given array. Order is unspecified —
     * the tests compare as sets.
     *
     * <ul>
     *   <li>{@code []} → {@code [[]]}  (the empty set has one subset — itself)</li>
     *   <li>{@code [1]} → {@code [[], [1]]}</li>
     *   <li>{@code [1, 2]} → {@code [[], [1], [2], [1, 2]]}</li>
     *   <li>{@code [1, 2, 3]} → 8 subsets</li>
     * </ul>
     *
     * <p>Each subset must be a fresh list — mutating one subset must not affect any other.
     * The input array must NOT be modified.
     *
     * <p>Two standard approaches:
     * <ol>
     *   <li><b>Backtracking</b>: for each element, either include it or exclude it, recurse.</li>
     *   <li><b>Iterative bitmask</b>: iterate {@code mask} from 0 to {@code 2ⁿ - 1}; element i
     *       is in the subset iff bit i of mask is set.</li>
     * </ol>
     * Either is acceptable. {@code null} input → empty list (not a list containing null).
     */
    public static List<List<Integer>> powerSet(int[] arr) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Balanced brackets — stack-based
    // ---------------------------------------------------------------------

    /**
     * Return {@code true} iff all brackets in {@code s} are balanced.
     * Supported pairs: {@code ()}, {@code []}, {@code {}}.
     * Non-bracket characters are ignored.
     *
     * <ul>
     *   <li>{@code "()"} → true</li>
     *   <li>{@code "()[]{}"} → true</li>
     *   <li>{@code "([{}])"} → true</li>
     *   <li>{@code "([)]"} → false  (LIFO violated)</li>
     *   <li>{@code "((("} → false  (unclosed)</li>
     *   <li>{@code ")("} → false  (closer with nothing to close)</li>
     *   <li>{@code "a(b)c"} → true</li>
     *   <li>{@code ""} or null → true</li>
     * </ul>
     *
     * <p>Use {@link java.util.ArrayDeque} as a stack ({@code push} / {@code pop} / {@code peek} / {@code isEmpty}).
     */
    public static boolean balancedBrackets(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Reverse Polish Notation — stack-based
    // ---------------------------------------------------------------------

    /**
     * Evaluate a Reverse Polish Notation (postfix) expression.
     * Supported operators: {@code "+"}, {@code "-"}, {@code "*"}, {@code "/"} (INTEGER division).
     * Operands are decimal integer strings.
     *
     * <p>RPN evaluation: read tokens left to right; push numbers; on an operator,
     * pop TWO numbers, apply the operator, push the result.
     *
     * <ul>
     *   <li>{@code ["2", "3", "+"]} → 5</li>
     *   <li>{@code ["2", "3", "+", "4", "*"]} → 20  →  (2+3)*4</li>
     *   <li>{@code ["10", "2", "/"]} → 5</li>
     *   <li>{@code ["5"]} → 5  (single token)</li>
     * </ul>
     *
     * @throws IllegalArgumentException if {@code tokens} is null or empty,
     *         or if a token is neither a valid integer nor a supported operator.
     * @throws IllegalStateException if the expression is malformed — for example,
     *         an operator has fewer than 2 operands, or the stack doesn't end with
     *         exactly one value.
     */
    public static int evaluateRPN(String[] tokens) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Singly linked list — build it from scratch
    // ---------------------------------------------------------------------

    /**
     * A singly linked list of ints. Implement every method.
     *
     * <p>You may NOT delegate to {@link java.util.LinkedList} or any other collection —
     * implement the list from scratch using a {@code Node} inner class with
     * {@code int value} and {@code Node next} fields.
     *
     * <p>For {@link #addLast}, maintain a {@code tail} pointer so the operation is O(1).
     */
    public static final class IntLinkedList {

        // TODO: declare head, tail, size

        /** Append {@code value} at the end. O(1). */
        public void addLast(int value) {
            throw new UnsupportedOperationException("TODO: implement");
        }

        /**
         * Reverse the list in place — O(n) time, O(1) extra space.
         * After this call, {@link #addLast} must still work correctly
         * (i.e. the tail pointer must be updated).
         */
        public void reverse() {
            throw new UnsupportedOperationException("TODO: implement");
        }

        /**
         * Return a snapshot of all values in list order, as an {@code int[]}.
         * Empty list → empty array.
         */
        public int[] toArray() {
            throw new UnsupportedOperationException("TODO: implement");
        }

        /** Number of elements. */
        public int size() {
            throw new UnsupportedOperationException("TODO: implement");
        }

        /** {@code true} iff the list is empty. */
        public boolean isEmpty() {
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}