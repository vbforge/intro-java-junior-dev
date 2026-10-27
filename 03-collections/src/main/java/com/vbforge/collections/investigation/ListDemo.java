package com.vbforge.collections.investigation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * INVESTIGATION — {@link List}: ArrayList vs LinkedList.
 *
 * <p>Both implement the same {@code List} contract but with very different
 * data structures and performance characteristics:
 *
 * <pre>
 *                   ArrayList                      LinkedList
 *   storage         contiguous array               doubly-linked nodes
 *   get(i)          O(1)                           O(n) — must walk nodes
 *   add(end)        O(1) amortized                 O(1)
 *   add(0, x)       O(n) — shifts the array        O(1)
 *   remove(0)       O(n) — shifts the array        O(1)
 *   memory          compact                        ~3x more per element (node objects)
 * </pre>
 *
 * <p>RULE OF THUMB: in 99% of real code you want {@link ArrayList}. Only reach for
 * {@link LinkedList} when you do lots of insertion/removal at the ENDS via
 * {@code Deque} operations — but for that, {@code ArrayDeque} is usually better.
 */
public final class ListDemo {

    private ListDemo() {}

    /** Build a mutable list of a few ints (a demo shortcut). */
    public static List<Integer> of(int... values) {
        List<Integer> list = new ArrayList<>();
        for (int v : values) list.add(v);
        return list;
    }

    // ---------------------------------------------------------------------
    // Common operations
    // ---------------------------------------------------------------------

    /** {@code get(i)} is O(1) for {@code ArrayList} because it indexes an array. */
    public static int getByIndex(List<Integer> list, int index) {
        return list.get(index);
    }

    /** {@code indexOf} is O(n) — walks the list until the value is found. */
    public static int indexOf(List<Integer> list, int value) {
        return list.indexOf(value);                     // -1 if absent
    }

    /** {@code subList} returns a VIEW into the same backing store. */
    public static List<Integer> sublist(List<Integer> list, int from, int to) {
        return list.subList(from, to);                  // note: mutations affect the parent
    }

    /** True copy — the returned list can be mutated without touching the original. */
    public static List<Integer> copy(List<Integer> list) {
        return new ArrayList<>(list);
    }

    // ---------------------------------------------------------------------
    // The GOTCHA: mutating the view returned by subList mutates the parent
    // ---------------------------------------------------------------------

    public static boolean subListMutatesParent() {
        List<Integer> parent = of(1, 2, 3, 4, 5);
        List<Integer> view = parent.subList(1, 4);      // [2, 3, 4]
        view.set(0, 99);                                // writes into 'parent'
        return parent.get(1) == 99;                     // true
    }

    // ---------------------------------------------------------------------
    // LinkedList also implements Deque — you can use it as a queue/stack
    // ---------------------------------------------------------------------

    public static int linkedListAsDeque() {
        LinkedList<Integer> dq = new LinkedList<>();
        dq.addFirst(2);
        dq.addFirst(1);                                 // [1, 2]
        dq.addLast(3);                                  // [1, 2, 3]
        return dq.removeFirst() + dq.removeLast();      // 1 + 3 = 4
    }

    // ---------------------------------------------------------------------
    // List.of — immutable, convenient factory
    // ---------------------------------------------------------------------

    public static boolean listOfIsImmutable() {
        List<Integer> fixed = List.of(1, 2, 3);
        try {
            fixed.add(4);
            return false;
        } catch (UnsupportedOperationException e) {
            return true;
        }
    }
}