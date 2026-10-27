package com.vbforge.algorithms.practical;

/**
 * PRACTICAL TASK 05 — Implement a FIFO queue using two LIFO stacks.
 *
 * <p>This is a classic interview problem. The insight:
 * <ul>
 *   <li>{@code inbox} receives every {@code enqueue}.</li>
 *   <li>{@code outbox} serves every {@code dequeue} — but only refills from {@code inbox}
 *       when it's empty, by popping from {@code inbox} and pushing onto {@code outbox}.
 *       That reverses the order twice, giving FIFO.</li>
 * </ul>
 *
 * <p>Amortized cost: every element is pushed to {@code inbox} once, moved to
 * {@code outbox} once, and popped once → amortized O(1) per operation.
 *
 * <p>You may use {@code java.util.ArrayDeque} as the two stacks — do NOT use
 * {@code java.util.LinkedList} as a queue, and do NOT use {@code java.util.Queue}.
 */
public final class Task05_QueueWithTwoStacks<T> {

    // TODO: declare two stacks. Suggested: Deque<T> inbox, outbox;

    /**
     * Add {@code value} to the back of the queue. O(1) amortized.
     */
    public void enqueue(T value) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Remove and return the element at the front of the queue.
     * @throws java.util.NoSuchElementException if the queue is empty.
     */
    public T dequeue() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return (without removing) the element at the front of the queue.
     * @throws java.util.NoSuchElementException if the queue is empty.
     */
    public T peek() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of elements in the queue. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** {@code true} iff the queue is empty. */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}