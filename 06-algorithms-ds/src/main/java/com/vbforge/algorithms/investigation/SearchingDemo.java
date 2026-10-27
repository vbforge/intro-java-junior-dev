package com.vbforge.algorithms.investigation;

/**
 * INVESTIGATION — Linear search and binary search.
 */
public final class SearchingDemo {

    private SearchingDemo() {}

    // ---------------------------------------------------------------------
    // Linear search — works on ANY array, O(n)
    // ---------------------------------------------------------------------

    /**
     * Walk the array from start to end. Return the FIRST index whose value equals
     * {@code target}, or {@code -1} if not found. O(n) — no assumptions about order.
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // ---------------------------------------------------------------------
    // Binary search — REQUIRES a sorted array, O(log n)
    // ---------------------------------------------------------------------

    /**
     * Binary search on a SORTED ascending array.
     *
     * <p>Invariant maintained by the loop: the target, if present, is in
     * {@code arr[low..high]} inclusive. Each iteration halves that range.
     *
     * <p>NOT safe on an unsorted array — the algorithm will simply produce a
     * wrong answer (no exception, no warning).
     *
     * @param sortedArr must be sorted ascending; behavior on unsorted input is undefined.
     * @return the index of any matching element, or {@code -1} if not found.
     */
    public static int binarySearch(int[] sortedArr, int target) {
        int low = 0;
        int high = sortedArr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;               // avoids overflow
            int v = sortedArr[mid];
            if (v == target) return mid;
            if (v < target) low = mid + 1;
            else            high = mid - 1;
        }
        return -1;
    }

    /**
     * Find the FIRST index at which {@code target} appears in a SORTED array,
     * or {@code -1}. Uses binary search but keeps going LEFT after a hit.
     */
    public static int findFirstOccurrence(int[] sortedArr, int target) {
        int low = 0;
        int high = sortedArr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (sortedArr[mid] == target) {
                result = mid;
                high = mid - 1;                         // keep searching LEFT
            } else if (sortedArr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}