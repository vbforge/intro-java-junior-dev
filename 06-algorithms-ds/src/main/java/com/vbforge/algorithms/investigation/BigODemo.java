package com.vbforge.algorithms.investigation;

/**
 * INVESTIGATION — Big-O growth classes, measured by operation count.
 *
 * <p>Each method counts a primitive operation (a loop iteration) as a proxy for
 * "work" and returns that count. The tests assert the growth relationship, not a
 * specific wall-clock time (which is non-deterministic). This is exactly what
 * Big-O describes: how the number of operations scales with input size {@code n}.
 *
 * <p>Growth classes covered:
 * <pre>
 *   O(1)        constant     — same work for any n
 *   O(log n)    logarithmic  — e.g. binary search, halving per step
 *   O(n)        linear       — one pass over the input
 *   O(n log n)  linearithmic — every comparison sort's lower bound
 *   O(n²)       quadratic    — nested loops; bubble/insertion/selection sort
 * </pre>
 */
public final class BigODemo {

    private BigODemo() {}

    /** O(1) — one operation, regardless of n. */
    public static long constantOperations(int n) {
        // Deliberately uses `n` so the parameter isn't dead — but the return is always 1.
        return (n >= 0 ? 1L : 1L);
    }

    /** O(n) — one iteration per input element. */
    public static long linearOperations(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            ops++;
        }
        return ops;
    }

    /**
     * O(log n) — halving on each iteration. Returns {@code floor(log2(n))} for n >= 1,
     * and {@code 0} for n <= 1.
     */
    public static long logarithmicOperations(int n) {
        long ops = 0;
        int x = n;
        while (x > 1) {
            x /= 2;
            ops++;
        }
        return ops;
    }

    /** O(n²) — nested loop, each iteration runs n times. */
    public static long quadraticOperations(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ops++;
            }
        }
        return ops;
    }

    /** O(n log n) — outer loop runs n times; inner loop runs log n times. */
    public static long linearithmicOperations(int n) {
        long ops = 0;
        for (int i = 0; i < n; i++) {
            int x = Math.max(n, 1);
            while (x > 1) {
                x /= 2;
                ops++;
            }
        }
        return ops;
    }
}