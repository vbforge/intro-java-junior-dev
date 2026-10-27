package com.vbforge.algorithms.investigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * INVESTIGATION — A singly linked list.
 *
 * <p>Trade-offs vs {@link java.util.ArrayList}:
 * <pre>
 *                   Singly linked list          ArrayList
 *   get(i)          O(n) — must walk             O(1)
 *   addFirst        O(1)                         O(n)
 *   addLast         O(1) (with tail pointer)     O(1) amortized
 *   remove(Object)  O(n) — must find first       O(n)
 *   memory          ~3x more per element         compact
 * </pre>
 */
public final class LinkedListDemo {

    private LinkedListDemo() {}

    /** A generic singly linked list with a tail pointer for O(1) addLast. */
    public static final class SinglyLinkedList<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        private static final class Node<T> {
            T value;
            Node<T> next;
            Node(T value) { this.value = value; }
        }

        /** Insert at the front — O(1). */
        public void addFirst(T value) {
            Node<T> n = new Node<>(value);
            n.next = head;
            head = n;
            if (tail == null) tail = n;
            size++;
        }

        /** Insert at the end — O(1) with the tail pointer. */
        public void addLast(T value) {
            Node<T> n = new Node<>(value);
            if (tail == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
            size++;
        }

        /** Search — O(n). Returns the first matching node's value (which is the only one for a list of unique values). */
        public boolean contains(T value) {
            for (Node<T> n = head; n != null; n = n.next) {
                if (Objects.equals(n.value, value)) return true;
            }
            return false;
        }

        /** Remove the first node whose value equals {@code value}. */
        public boolean remove(T value) {
            Node<T> prev = null;
            Node<T> cur  = head;
            while (cur != null) {
                if (Objects.equals(cur.value, value)) {
                    if (prev == null) head = cur.next;
                    else              prev.next = cur.next;
                    if (cur == tail)  tail = prev;
                    size--;
                    return true;
                }
                prev = cur;
                cur  = cur.next;
            }
            return false;
        }

        /**
         * Reverse in place — O(n) time, O(1) space.
         * Uses the three-pointer technique: {@code prev, cur, next}.
         */
        public void reverse() {
            Node<T> prev = null;
            Node<T> cur  = head;
            tail = head;                                // old head becomes the new tail
            while (cur != null) {
                Node<T> next = cur.next;
                cur.next = prev;
                prev = cur;
                cur  = next;
            }
            head = prev;
        }

        public int size()      { return size; }
        public boolean isEmpty() { return size == 0; }

        /** Snapshot into a list (for inspection / testing). */
        public List<T> toList() {
            List<T> out = new ArrayList<>(size);
            for (Node<T> n = head; n != null; n = n.next) out.add(n.value);
            return out;
        }
    }
}