package com.vbforge.algorithms.investigation;

import java.util.Arrays;

/**
 * INVESTIGATION — Three classic sorts.
 *
 * <p>Each method returns a NEW sorted array; the input is never mutated.
 * This makes the tests trivially assertable: {@code assertThat(demo.sort(input)).containsExactly(...)}.
 *
 * <p>Complexities (n = array length):
 * <pre>
 *   Bubble sort      O(n²) time   O(1)  space   stable
 *   Insertion sort   O(n²) time   O(1)  space   stable — and O(n) on nearly-sorted input
 *   Merge sort       O(n log n)   O(n)  space   stable
 * </pre>
 */
public final class SortingDemo {

    private SortingDemo() {}

    // ---------------------------------------------------------------------
    // Bubble sort — the simplest possible sort
    // ---------------------------------------------------------------------

    /**
     * Bubble sort: repeatedly walk the array, swapping adjacent out-of-order pairs.
     * Each pass bubbles the largest remaining element to its final position.
     *
     * <p>The {@code swapped} flag lets us stop early if a pass makes no swaps —
     * this gives O(n) behavior on already-sorted input.
     */
    public static int[] bubbleSort(int[] input) {
        int[] a = Arrays.copyOf(input, input.length);
        int n = a.length;
        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < n - 1 - pass; i++) {
                if (a[i] > a[i + 1]) {
                    int t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return a;
    }

    // ---------------------------------------------------------------------
    // Insertion sort — like sorting a hand of playing cards
    // ---------------------------------------------------------------------

    /**
     * Insertion sort: build the sorted prefix one element at a time. For each new
     * element, shift larger elements right until it finds its slot.
     *
     * <p>Very fast on small arrays and nearly-sorted arrays — real libraries use it
     * as the base case for hybrid sorts (Timsort, introsort).
     */
    public static int[] insertionSort(int[] input) {
        int[] a = Arrays.copyOf(input, input.length);
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
        return a;
    }

    // ---------------------------------------------------------------------
    // Merge sort — divide and conquer
    // ---------------------------------------------------------------------

    /**
     * Merge sort: split the array in half, sort each half recursively, then merge
     * the two sorted halves. The merge step does the work; recursion is trivial.
     */
    public static int[] mergeSort(int[] input) {
        int[] a = Arrays.copyOf(input, input.length);
        if (a.length < 2) return a;                     // base case — nothing to sort
        mergeSortInPlace(a, 0, a.length);
        return a;
    }

    private static void mergeSortInPlace(int[] a, int from, int to) {
        if (to - from < 2) return;                      // 0 or 1 element — already sorted
        int mid = (from + to) >>> 1;                    // >>> avoids overflow for huge arrays
        mergeSortInPlace(a, from, mid);
        mergeSortInPlace(a, mid, to);
        merge(a, from, mid, to);
    }

    /**
     * Merge two sorted runs — {@code a[from, mid)} and {@code a[mid, to)} —
     * into a single sorted run over {@code a[from, to)}.
     */
    private static void merge(int[] a, int from, int mid, int to) {
        int[] left  = Arrays.copyOfRange(a, from, mid);
        int[] right = Arrays.copyOfRange(a, mid, to);
        int i = 0, j = 0, k = from;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {                  // <= keeps the sort stable
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }
        while (i < left.length)  a[k++] = left[i++];
        while (j < right.length) a[k++] = right[j++];
    }
}