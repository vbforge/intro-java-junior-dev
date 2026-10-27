package com.vbforge.collections.practical;

import java.util.List;

/**
 * PRACTICAL TASK 02 — {@code List} manipulation.
 */
public final class Task02_ListOperations {

    private Task02_ListOperations() {}

    /**
     * Return a new list containing only the elements of {@code input} that appear
     * EXACTLY ONCE in it, preserving the original order.
     * <ul>
     *   <li>{@code [1, 2, 2, 3, 4, 4, 5]} → {@code [1, 3, 5]}</li>
     *   <li>{@code []} → {@code []}</li>
     *   <li>{@code [1, 1, 1]} → {@code []}</li>
     * </ul>
     * {@code input} must NOT be modified. May be null → return empty list.
     */
    public static List<Integer> onlyUnique(List<Integer> input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Rotate the list left by {@code k} positions, returning a NEW list.
     * <ul>
     *   <li>{@code [1,2,3,4,5], 2 → [3,4,5,1,2]}</li>
     *   <li>{@code [1,2,3,4,5], 0 → [1,2,3,4,5]}</li>
     *   <li>{@code [1,2,3,4,5], 7 → [3,4,5,1,2]}  (7 mod 5 = 2)</li>
     *   <li>{@code []}, any k → {@code []}</li>
     * </ul>
     * {@code input} must NOT be modified.
     */
    public static List<Integer> rotateLeft(List<Integer> input, int k) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Merge two SORTED (ascending) lists into one sorted list.
     * Duplicates are kept.
     * <ul>
     *   <li>{@code [1,3,5] + [2,4,6] → [1,2,3,4,5,6]}</li>
     *   <li>{@code [1,1,3] + [1,2,2] → [1,1,1,2,2,3]}</li>
     *   <li>{@code [] + [1,2] → [1,2]}</li>
     * </ul>
     * Both inputs may be null → treated as empty.
     * Complexity target: O(n + m) — do NOT just concat + sort (that's O((n+m) log (n+m))).
     */
    public static List<Integer> mergeSorted(List<Integer> a, List<Integer> b) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}