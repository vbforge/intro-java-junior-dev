package com.vbforge.algorithms.practical;

/**
 * PRACTICAL TASK 01 — Two simple O(n²) sorts.
 *
 * <p>Both methods must return a NEW sorted array in ascending order.
 * The input array must NOT be modified.
 */
public final class Task01_BubbleAndInsertionSort {

    private Task01_BubbleAndInsertionSort() {}

    /**
     * Implement bubble sort.
     *
     * <p>Algorithm: repeatedly walk the array, swapping adjacent pairs that are
     * out of order. After each full pass, the largest remaining element has
     * "bubbled" to its final position.
     *
     * <p>Optimization to include: if a pass makes no swaps, the array is sorted —
     * stop early.
     *
     * <p>Empty / singleton arrays must return without error.
     */
    public static int[] bubbleSort(int[] input) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Implement insertion sort.
     *
     * <p>Algorithm: build the sorted prefix one element at a time.
     * For each new element, shift larger elements of the sorted prefix right
     * until the new element finds its slot.
     *
     * <p>Empty / singleton arrays must return without error.
     */
    public static int[] insertionSort(int[] input) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}