package com.vbforge.algorithms.practical;

import java.util.List;

/**
 * PRACTICAL TASK 04 — A singly linked list.
 *
 * <p>Build a generic singly linked list from scratch. Implement every method below.
 * No use of {@code java.util.LinkedList} — you're building the data structure.
 *
 * <p>The class is {@code Iterable} — implementing {@code iterator()} is optional
 * for the tests to pass, but you're welcome to add it if you want to practice.
 */
public final class Task04_LinkedList<T> {

    /**
     * Add a value at the front of the list. O(1) required.
     */
    public void addFirst(T value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Add a value at the end of the list. O(1) required — maintain a tail pointer.
     */
    public void addLast(T value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return {@code true} if the list contains an element equal to {@code value}.
     * Use {@link java.util.Objects#equals(Object, Object)} for null-safe comparison.
     */
    public boolean contains(T value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Remove the FIRST element equal to {@code value}.
     * @return {@code true} if an element was removed, {@code false} otherwise.
     * Must handle removing the head, the tail, and the only element.
     */
    public boolean remove(T value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Reverse the list in place. O(n) time, O(1) extra space (do not build a new list).
     * After this call, the old head is the new tail — {@code addLast} must still work.
     */
    public void reverse() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return a snapshot of all values in list order (front to back).
     * The returned list must be a fresh copy — mutations to it must NOT affect this list.
     */
    public List<T> toList() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of elements currently in the list. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** {@code true} iff {@link #size()} is 0. */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}