package com.vbforge.collections.investigation;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * INVESTIGATION — {@link Queue} and {@link Deque}.
 *
 * <p>Use {@link ArrayDeque} for both stack (LIFO) and queue (FIFO) semantics.
 * Avoid the legacy {@code java.util.Stack} class — it's synchronized (slow),
 * extends {@code Vector}, and has nothing to recommend it over {@code Deque}.
 */
public final class QueueDequeDemo {

    private QueueDequeDemo() {}

    // ---------------------------------------------------------------------
    // ArrayDeque as FIFO queue
    // ---------------------------------------------------------------------

    /** offer/poll/peek for queue semantics (returns null / false instead of throwing). */
    public static int[] fifoOrder() {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        return new int[]{ q.poll(), q.poll(), q.poll() };   // {1, 2, 3}
    }

    // ---------------------------------------------------------------------
    // ArrayDeque as LIFO stack
    // ---------------------------------------------------------------------

    /**
     * push/pop/peek for stack semantics — top of stack is {@code peekFirst()}.
     * Note: {@code push} adds to the HEAD, {@code pop} removes from the HEAD.
     */
    public static int[] lifoOrder() {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        return new int[]{ stack.pop(), stack.pop(), stack.pop() };  // {3, 2, 1}
    }

    // ---------------------------------------------------------------------
    // PriorityQueue — ordered by natural order OR a Comparator
    // ---------------------------------------------------------------------

    /**
     * PriorityQueue does NOT sort on insert; it maintains a binary heap.
     * {@code poll()} always returns the SMALLEST element (natural order).
     * Iterating over a PriorityQueue does NOT return elements in order — only
     * successive {@code poll()} calls do.
     */
    public static int[] naturalOrderPoll() {
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);
        return new int[]{ pq.poll(), pq.poll(), pq.poll() };   // {1, 2, 3}
    }

    /** Custom comparator — descending. */
    public static int[] customComparatorPoll() {
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);
        return new int[]{ pq.poll(), pq.poll(), pq.poll() };   // {3, 2, 1}
    }

    /** What you see if you iterate without polling — NOT sorted. */
    public static Integer[] iterationIsNotSorted() {
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(4);
        pq.offer(2);
        pq.offer(3);
        return pq.toArray(Integer[]::new);                      // heap order, not [1..5]
    }
}