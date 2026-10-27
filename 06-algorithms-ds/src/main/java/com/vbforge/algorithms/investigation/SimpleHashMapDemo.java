package com.vbforge.algorithms.investigation;

import java.util.Objects;

/**
 * INVESTIGATION — A from-scratch hash map, built to demonstrate the concepts
 * (bucket array + linked-list chaining + resize on load factor) end to end.
 *
 * <p>This is a teaching implementation, not a substitute for {@link java.util.HashMap}.
 * It omits many details that production {@code HashMap} handles: treeification,
 * iterators, null keys/values (we reject {@code null} keys here), and thread-safety.
 *
 * <p>Design:
 * <pre>
 *   buckets[ ]  — array of "bucket" heads. Each bucket is a singly linked list.
 *   bucketIndex — (hash(key) ^ (hash >>> 16)) & (capacity - 1)
 *                 (spreads high bits into low bits; capacity is always a power of 2)
 *   load factor — 0.75; when size > capacity * 0.75, double the capacity and rehash.
 *   put         — walk the bucket's list: if the key exists, replace; else insert at HEAD.
 *   get         — walk the bucket's list comparing with Objects.equals.
 * </pre>
 */
public final class SimpleHashMapDemo {

    private SimpleHashMapDemo() {}

    /** A minimal hash map: generic keys, generic values, null values allowed, null keys NOT. */
    public static final class SimpleHashMap<K, V> {
        private static final int INITIAL_CAPACITY = 16;
        private static final double LOAD_FACTOR   = 0.75;

        @SuppressWarnings("unchecked")
        private Node<K, V>[] buckets = new Node[INITIAL_CAPACITY];

        private int size = 0;

        private static final class Node<K, V> {
            final K key;
            V value;
            Node<K, V> next;

            Node(K key, V value, Node<K, V> next) {
                this.key   = key;
                this.value = value;
                this.next  = next;
            }
        }

        private int bucketIndex(K key) {
            int h = key.hashCode();
            int spread = h ^ (h >>> 16);                // spread high bits into low
            return spread & (buckets.length - 1);       // modulo a power of 2
        }

        public void put(K key, V value) {
            Objects.requireNonNull(key, "key");
            int idx = bucketIndex(key);
            for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
                if (n.key.equals(key)) {
                    n.value = value;                    // key exists — replace
                    return;
                }
            }
            buckets[idx] = new Node<>(key, value, buckets[idx]);    // insert at head
            size++;
            if (size > buckets.length * LOAD_FACTOR) resize();
        }

        public V get(K key) {
            Objects.requireNonNull(key, "key");
            int idx = bucketIndex(key);
            for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
                if (n.key.equals(key)) return n.value;
            }
            return null;
        }

        public V remove(K key) {
            Objects.requireNonNull(key, "key");
            int idx = bucketIndex(key);
            Node<K, V> prev = null;
            Node<K, V> cur  = buckets[idx];
            while (cur != null) {
                if (cur.key.equals(key)) {
                    if (prev == null) buckets[idx] = cur.next;
                    else              prev.next     = cur.next;
                    size--;
                    return cur.value;
                }
                prev = cur;
                cur  = cur.next;
            }
            return null;
        }

        public boolean containsKey(K key) {
            return get(key) != null;
        }

        public int size() {
            return size;
        }

        @SuppressWarnings("unchecked")
        private void resize() {
            Node<K, V>[] old = buckets;
            buckets = new Node[old.length * 2];
            size = 0;
            for (Node<K, V> head : old) {
                for (Node<K, V> n = head; n != null; n = n.next) {
                    put(n.key, n.value);                // rehash each entry into the new array
                }
            }
        }
    }
}