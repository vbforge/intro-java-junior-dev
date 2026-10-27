package com.vbforge.collections.practical;

import java.util.List;

/**
 * PRACTICAL TASK 06 — {@code PriorityQueue} + custom {@code Comparator}.
 *
 * <p>A task queue where every task has a priority (higher number = more urgent)
 * and an arrival timestamp (lower = older). Ordering rule:
 * <ol>
 *   <li>Higher priority wins.</li>
 *   <li>Ties in priority are broken by earlier arrival (lower timestamp wins).</li>
 *   <li>Ties in both are broken by task name (alphabetical).</li>
 * </ol>
 */
public final class Task06_PriorityTaskQueue {

    public record Task(String name, int priority, long arrival) {}

    // TODO: store tasks in a PriorityQueue<Task> with the comparator below

    /**
     * The ordering comparator. Higher priority first, then older arrival first,
     * then name ascending.
     */
    public static final java.util.Comparator<Task> ORDER =
            (a, b) -> {
                int byPriority = Integer.compare(b.priority(), a.priority());   // higher first
                if (byPriority != 0) return byPriority;
                int byArrival = Long.compare(a.arrival(), b.arrival());         // older first
                if (byArrival != 0) return byArrival;
                return a.name().compareTo(b.name());
            };

    /** Create an empty queue ordered by {@link #ORDER}. */
    public Task06_PriorityTaskQueue() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Add a task. Null → {@link NullPointerException} (natural — do not catch). */
    public void add(Task task) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Remove and return the highest-priority task, or {@code null} if empty. */
    public Task poll() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Return (without removing) the highest-priority task, or {@code null} if empty. */
    public Task peek() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Drain the queue and return ALL tasks in the order they would be polled.
     * Leaves the queue empty. Empty queue → empty list.
     */
    public List<Task> drainAllInOrder() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of tasks currently queued. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}