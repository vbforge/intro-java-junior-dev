package com.vbforge.algorithms.practical;

/**
 * PRACTICAL TASK 03 — Binary search.
 *
 * <p>All methods take a SORTED ascending array. Do NOT sort it yourself —
 * the caller is responsible for that precondition.
 */
public final class Task03_BinarySearch {

    private Task03_BinarySearch() {}

    /**
     * Standard binary search.
     * @return the index of {@code target}, or {@code -1} if not present.
     * <ul>
     *   <li>Empty array → -1.</li>
     *   <li>If there are duplicates, return ANY matching index — the spec doesn't care which.</li>
     * </ul>
     * Target complexity: O(log n).
     */
    public static int binarySearch(int[] sortedArr, int target) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Find the FIRST index of {@code target} in the sorted array, or -1 if absent.
     *
     * <p>Example: {@code [1, 2, 2, 2, 3]}, target 2 → returns 1 (not 2 or 3).
     *
     * <p>Hint: when you find a match, record the index and keep searching LEFT.
     * Target complexity: O(log n) — still log, not linear.
     */
    public static int findFirstOccurrence(int[] sortedArr, int target) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}