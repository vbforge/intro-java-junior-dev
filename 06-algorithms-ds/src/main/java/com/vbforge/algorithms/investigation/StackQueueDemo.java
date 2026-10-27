package com.vbforge.algorithms.investigation;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * INVESTIGATION — A custom Stack and a custom Queue built on plain arrays.
 *
 * <p>Both grow automatically when full. Stack grows by doubling the array;
 * Queue uses a CIRCULAR BUFFER so dequeue is O(1) without shifting elements.
 */
public final class StackQueueDemo {

    private StackQueueDemo() {}

    // ---------------------------------------------------------------------
    // Stack — LIFO, backed by an array
    // ---------------------------------------------------------------------

    /** A minimal LIFO stack of ints. Push/pop/peek are O(1) amortized. */
    public static final class IntStack {
        private int[] data = new int[8];
        private int size = 0;

        public void push(int v) {
            if (size == data.length) {
                int[] bigger = new int[data.length * 2];
                System.arraycopy(data, 0, bigger, 0, size);
                data = bigger;
            }
            data[size++] = v;
        }

        public int pop() {
            if (size == 0) throw new EmptyStackException();
            return data[--size];
        }

        public int peek() {
            if (size == 0) throw new EmptyStackException();
            return data[size - 1];
        }

        public boolean isEmpty() { return size == 0; }
        public int size()        { return size; }
    }

    // ---------------------------------------------------------------------
    // Queue — FIFO, backed by a circular buffer
    // ---------------------------------------------------------------------

    /**
     * A minimal FIFO queue of ints.
     *
     * <p>A naive array-based queue would need to shift all elements on every
     * {@code dequeue} (O(n)). A CIRCULAR BUFFER avoids this: {@code head} and
     * {@code tail} advance modulo the array length, so both operations are O(1).
     */
    public static final class IntQueue {
        private int[] data = new int[8];
        private int head = 0;
        private int tail = 0;
        private int size = 0;

        public void enqueue(int v) {
            if (size == data.length) grow();
            data[tail] = v;
            tail = (tail + 1) % data.length;
            size++;
        }

        public int dequeue() {
            if (size == 0) throw new NoSuchElementException();
            int v = data[head];
            head = (head + 1) % data.length;
            size--;
            return v;
        }

        public int peek() {
            if (size == 0) throw new NoSuchElementException();
            return data[head];
        }

        public boolean isEmpty() { return size == 0; }
        public int size()        { return size; }

        private void grow() {
            int[] bigger = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                bigger[i] = data[(head + i) % data.length];
            }
            data = bigger;
            head = 0;
            tail = size;
        }
    }
}