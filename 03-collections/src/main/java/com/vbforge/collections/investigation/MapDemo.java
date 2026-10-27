package com.vbforge.collections.investigation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * INVESTIGATION — {@link Map}: HashMap, LinkedHashMap, TreeMap.
 *
 * <pre>
 *                  order                   get/put             null keys?
 *   HashMap        unpredictable           O(1) avg            yes
 *   LinkedHashMap  insertion order         O(1) avg            yes
 *   TreeMap        sorted by key           O(log n)            NO (natural ordering)
 * </pre>
 */
public final class MapDemo {

    private MapDemo() {}

    // ---------------------------------------------------------------------
    // Common operations
    // ---------------------------------------------------------------------

    public static Map<String, Integer> basicMap() {
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        m.put("b", 2);
        m.put("c", 3);
        return m;
    }

    /** {@code getOrDefault} avoids the "containsKey + get" dance. */
    public static int getOrDefaultDemo(Map<String, Integer> m, String k) {
        return m.getOrDefault(k, -1);
    }

    /** {@code putIfAbsent} — only writes if the key is absent (or mapped to null). */
    public static Map<String, Integer> putIfAbsentDemo() {
        Map<String, Integer> m = new HashMap<>();
        m.put("x", 1);
        m.putIfAbsent("x", 99);                         // ignored, x already exists
        m.putIfAbsent("y", 2);                          // added
        return m;                                       // {x=1, y=2}
    }

    /** {@code computeIfAbsent} — lazy init, very common when populating maps of lists. */
    public static Map<String, java.util.List<Integer>> groupByModulus(int[] values, int mod) {
        Map<String, java.util.List<Integer>> groups = new HashMap<>();
        for (int v : values) {
            String key = (v % mod == 0) ? "divisible" : "not-divisible";
            groups.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(v);
        }
        return groups;
    }

    /** {@code merge} — insert / update in one call. Here: count occurrences of each word. */
    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String w : words) {
            counts.merge(w, 1, Integer::sum);
        }
        return counts;
    }

    // ---------------------------------------------------------------------
    // Iteration
    // ---------------------------------------------------------------------

    /** Iterate key/value pairs — preferred over {@code keySet() + get()}. */
    public static String joinEntries(Map<String, Integer> m) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(e.getKey()).append('=').append(e.getValue());
        }
        return sb.toString();
    }

    // ---------------------------------------------------------------------
    // Ordering variants
    // ---------------------------------------------------------------------

    public static String[] linkedHashMapOrder() {
        Map<String, Integer> m = new LinkedHashMap<>();
        m.put("c", 1);
        m.put("a", 2);
        m.put("b", 3);
        return m.keySet().toArray(String[]::new);       // [c, a, b] — insertion order
    }

    public static String[] treeMapOrder() {
        Map<String, Integer> m = new TreeMap<>();
        m.put("c", 1);
        m.put("a", 2);
        m.put("b", 3);
        return m.keySet().toArray(String[]::new);       // [a, b, c] — sorted
    }

    // ---------------------------------------------------------------------
    // LinkedHashMap access-order mode → LRU cache
    // ---------------------------------------------------------------------

    /**
     * A tiny LRU cache: {@code LinkedHashMap} in access-order mode automatically
     * moves an entry to the END when it is accessed. {@code removeEldestEntry}
     * evicts the oldest (LRU) once the capacity exceeds {@code maxSize}.
     */
    public static class LruCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxSize;

        public LruCache(int maxSize) {
            super(16, 0.75f, /* accessOrder = */ true);
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxSize;
        }
    }
}