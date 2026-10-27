package com.vbforge.javacore.investigation;

import java.util.Arrays;

/**
 * INVESTIGATION — Arrays and {@link java.util.Arrays} utilities.
 */
public final class ArraysDemo {

    private ArraysDemo() {}

    // ---------------------------------------------------------------------
    // Declaration and initialization
    // ---------------------------------------------------------------------

    /** Arrays of primitives are zero-initialized. */
    public static int[] zeroInitialized() {
        return new int[5];                 // [0, 0, 0, 0, 0]
    }

    /** Array literal syntax. */
    public static int[] literal() {
        return new int[]{1, 2, 3};
    }

    /** A 2D array is an array of arrays — rows can have different lengths (jagged). */
    public static int[][] jagged() {
        return new int[][]{
                {1, 2, 3},
                {4, 5},
                {6}
        };
    }

    // ---------------------------------------------------------------------
    // java.util.Arrays — the toolbox
    // ---------------------------------------------------------------------

    /** Copy — {@code Arrays.copyOf} pads or truncates to the target length. */
    public static int[] copyPadded(int[] original, int newLength) {
        return Arrays.copyOf(original, newLength);
    }

    /** Fill — same value everywhere. */
    public static int[] filled(int length, int value) {
        int[] arr = new int[length];
        Arrays.fill(arr, value);
        return arr;
    }

    /** Sort — ascending by default. In-place. */
    public static int[] sortedAscending(int[] values) {
        int[] copy = Arrays.copyOf(values, values.length);
        Arrays.sort(copy);
        return copy;
    }

    /** Sort — descending using only {@code Arrays.sort} (works on reference types only, needs a Comparator). */
    public static Integer[] sortedDescending(Integer[] values) {
        Integer[] copy = Arrays.copyOf(values, values.length);
        Arrays.sort(copy, (a, b) -> Integer.compare(b, a));
        return copy;
    }

    /** {@code Arrays.equals} compares CONTENT (not reference). Contrast: {@code a == b} compares references. */
    public static boolean contentEquals(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    /** Binary search — the array MUST be sorted. Returns index or a negative insertion point. */
    public static int binarySearch(int[] sortedArr, int target) {
        return Arrays.binarySearch(sortedArr, target);
    }

    /** Human-readable form. Without {@code Arrays.toString}, printing an array gives {@code [I@hash}. */
    public static String pretty(int[] arr) {
        return Arrays.toString(arr);
    }
}