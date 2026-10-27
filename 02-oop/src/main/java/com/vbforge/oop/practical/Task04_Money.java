package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 04 — equals / hashCode / toString / Comparable.
 *
 * <p>A value class. Two instances are equal iff they have the same
 * {@code cents} AND the same {@code currency} (case-insensitive — normalize to upper-case
 * in the constructor).
 */
public final class Task04_Money implements Comparable<Task04_Money> {

    /**
     * @throws IllegalArgumentException if {@code currency} is null/blank.
     *         Store {@code currency.toUpperCase()}.
     */
    public Task04_Money(long cents, String currency) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    public long cents()      { throw new UnsupportedOperationException("TODO"); }
    public String currency() { throw new UnsupportedOperationException("TODO"); }

    /**
     * Add two Money values of the SAME currency.
     * @throws IllegalArgumentException if currencies differ (message must contain "currency").
     */
    public Task04_Money plus(Task04_Money other) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return a new Money with the sign flipped.
     */
    public Task04_Money negate() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Two Money are equal iff {@code cents == other.cents} AND
     * {@code currency.equals(other.currency)}.
     */
    @Override public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    @Override public int hashCode() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Format: {@code "<cents> <currency>"}, e.g. {@code "1234 USD"}, {@code "-50 EUR"}.
     */
    @Override public String toString() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Compare by cents. If currencies differ, sort by currency first (alphabetical),
     * then by cents.
     */
    @Override public int compareTo(Task04_Money other) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}