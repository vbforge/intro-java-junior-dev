package com.vbforge.exam.practical;

/**
 * PRACTICAL TASK 04 — Big-O + Sorting + Searching + Arrays.
 *
 * <p>All binary-search-based methods require a SORTED ASCENDING input array.
 * The caller is responsible for that precondition — do NOT sort inside the
 * binary search methods.
 */
public final class Task04_Algorithms {

    private Task04_Algorithms() {}

    // ---------------------------------------------------------------------
    // Quick sort
    // ---------------------------------------------------------------------

    /**
     * Sort {@code input} ascending using QUICK sort. Return a NEW array; do NOT
     * modify the input.
     *
     * <p>Target: O(n log n) average time.
     *
     * <p><b>Pivot choice matters.</b> Picking the last element gives O(n²) on
     * already-sorted input. Pick the middle element (or a random one) to avoid
     * that degenerate case.
     *
     * <ul>
     *   <li>Empty / singleton arrays → return a copy unchanged.</li>
     *   <li>Duplicates are allowed; sorted output must contain the same multiset.</li>
     * </ul>
     */
    public static int[] quickSort(int[] input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Binary-search extensions
    // ---------------------------------------------------------------------

    /**
     * Lower bound: the SMALLEST index {@code i} such that {@code sorted[i] >= target}.
     * If no such index exists (all elements are {@code < target}),
     * return {@code sorted.length}.
     *
     * <p>Examples on {@code [1, 2, 2, 2, 3, 4]}:
     * <ul>
     *   <li>{@code lowerBound(arr, 2)} → 1</li>
     *   <li>{@code lowerBound(arr, 0)} → 0</li>
     *   <li>{@code lowerBound(arr, 5)} → 6 (= arr.length)</li>
     * </ul>
     *
     * <p>Target complexity: O(log n).
     */
    public static int lowerBound(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Upper bound: the SMALLEST index {@code i} such that {@code sorted[i] > target}.
     * If no such index exists (all elements are {@code <= target}),
     * return {@code sorted.length}.
     *
     * <p>Examples on {@code [1, 2, 2, 2, 3, 4]}:
     * <ul>
     *   <li>{@code upperBound(arr, 2)} → 4</li>
     *   <li>{@code upperBound(arr, 4)} → 6 (= arr.length)</li>
     *   <li>{@code upperBound(arr, 0)} → 0</li>
     * </ul>
     *
     * <p>Target complexity: O(log n).
     */
    public static int upperBound(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Count how many times {@code target} appears in the sorted array.
     * Should be built on top of {@code lowerBound} and {@code upperBound} —
     * do NOT loop over the array (that would be O(n) instead of O(log n)).
     *
     * <p>Target complexity: O(log n). Return 0 if absent.
     */
    public static int countOccurrences(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Two-pointer dedup
    // ---------------------------------------------------------------------

    /**
     * Remove duplicates from a SORTED array, keeping one occurrence of each value.
     * Return a NEW array with the unique elements, in order.
     * The input must NOT be modified.
     *
     * <ul>
     *   <li>{@code [1, 1, 2, 3, 3, 3, 4]} → {@code [1, 2, 3, 4]}</li>
     *   <li>{@code []} → {@code []}</li>
     *   <li>{@code [5, 5, 5]} → {@code [5]}</li>
     * </ul>
     *
     * <p>Target: O(n) time and O(n) extra space (a single output array is fine).
     */
    public static int[] removeDuplicatesSorted(int[] sorted) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}