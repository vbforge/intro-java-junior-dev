package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 06 — Record with compact constructor.
 *
 * <p>Implement a {@code Range} record representing an inclusive integer range
 * {@code [from, to]}. Use a compact constructor for validation.
 */
public record Task06_Range(int from, int to) {

    /**
     * Compact constructor.
     * @throws IllegalArgumentException if {@code from > to}.
     *         Message must contain both {@code from} and {@code to}.
     */
    public Task06_Range {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of integers covered, i.e. {@code to - from + 1}. */
    public int length() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** {@code true} iff {@code from <= value <= to}. */
    public boolean contains(int value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** {@code true} iff the two ranges share at least one integer. */
    public boolean overlaps(Task06_Range other) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the intersection with {@code other}.
     * @throws IllegalArgumentException if the ranges don't overlap
     *         (message must contain "overlap").
     */
    public Task06_Range intersect(Task06_Range other) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}