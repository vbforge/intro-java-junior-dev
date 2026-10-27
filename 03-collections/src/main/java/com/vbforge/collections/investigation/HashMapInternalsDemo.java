package com.vbforge.collections.investigation;

import java.util.HashMap;
import java.util.Map;

/**
 * INVESTIGATION — {@code HashMap} internals.
 *
 * <p>HOW {@code HashMap} WORKS (Java 8+):
 * <ol>
 *   <li><b>Buckets</b>: an array of "buckets". Initially {@code null}. Lazily resized.</li>
 *   <li><b>Index</b>: {@code (h = key.hashCode()) ^ (h >>> 16)} then {@code bucketIndex = hash & (capacity - 1)}.
 *       The xor-shift spreads high bits into low bits — important because capacity is a power of two.</li>
 *   <li><b>Collisions</b>: multiple keys in the same bucket form a LINKED LIST.
 *       Each node stores hash, key, value, next.</li>
 *   <li><b>Load factor</b>: default 0.75. When {@code size > capacity * loadFactor},
 *       the table is <b>doubled</b> and all entries rehashed. Cost is amortized O(1) per put.</li>
 *   <li><b>Treeification</b> (Java 8+): when a single bucket's linked list exceeds
 *       {@code TREEIFY_THRESHOLD = 8} AND the table's capacity is at least
 *       {@code MIN_TREEIFY_CAPACITY = 64}, the bucket converts to a red-black tree,
 *       reducing worst-case lookup from O(n) to O(log n). If the bucket drops back to
 *       {@code UNTREEIFY_THRESHOLD = 6}, it converts back to a linked list.</li>
 *   <li><b>Worst case</b>: if every key collides AND treeification does not kick in
 *       (small table), lookups degrade to O(n). This is why a bad {@code hashCode()}
 *       (e.g., always returning 0) is catastrophic.</li>
 * </ol>
 *
 * <p>We cannot observe internal buckets without reflection + {@code --add-opens},
 * so the demos below show the OBSERVABLE consequences of these mechanics.
 */
public final class HashMapInternalsDemo {

    private HashMapInternalsDemo() {}

    // ---------------------------------------------------------------------
    // Two distinct strings with the SAME hashCode — "Aa" and "BB"
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: {@code "Aa".hashCode() == "BB".hashCode() == 2112}. Two different strings,
     * one bucket. {@code HashMap} still handles them correctly because {@code equals}
     * differentiates them after landing in the same bucket.
     */
    public static boolean collisionsOnPurpose() {
        return "Aa".hashCode() == "BB".hashCode();
    }

    /**
     * The map stores both keys despite the collision — correctness is preserved.
     * The cost is O(n) within that bucket, not O(1). With MANY collisions this becomes
     * the O(n) worst case — unless treeification kicks in.
     */
    public static Map<String, Integer> bothKeysCoexist() {
        Map<String, Integer> m = new HashMap<>();
        m.put("Aa", 1);
        m.put("BB", 2);
        return m;                                       // size 2, both retrievable
    }

    // ---------------------------------------------------------------------
    // Default load factor / capacity — observable indirectly
    // ---------------------------------------------------------------------

    /**
     * The no-arg constructor uses capacity 16 and load factor 0.75. The threshold
     * is 16 * 0.75 = 12. Putting the 13th entry triggers a resize to capacity 32,
     * then threshold 24, and so on.
     *
     * <p>We cannot read {@code table.length} without reflection, but we CAN see
     * that the map happily takes many more entries — the resize is invisible to the API.
     */
    public static int resizesAreInvisible(int n) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.put(i, i);
        return m.size();                                // == n, for any n
    }

    // ---------------------------------------------------------------------
    // Why a bad hashCode kills performance
    // ---------------------------------------------------------------------

    /**
     * A class with a degenerate {@code hashCode()}. Every instance lands in ONE bucket.
     * Lookups become O(n) — in practice this means a map of 10k entries acts like a list.
     *
     * <p>The demo places many entries and reads them back — correctness holds, but
     * performance is pathological. We only assert CORRECTNESS here; the point is that
     * you should never write {@code hashCode()} that returns a constant.
     */
    public static final class BadHash {
        final int id;
        public BadHash(int id) { this.id = id; }
        @Override public boolean equals(Object o) {
            return o instanceof BadHash b && b.id == id;
        }
        @Override public int hashCode() {
            return 0;                                   // <-- catastrophic in production
        }
    }

    public static boolean badHashMapIsStillCorrect(int n) {
        Map<BadHash, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.put(new BadHash(i), i);
        // retrieve every entry — should all be present, all correct
        for (int i = 0; i < n; i++) {
            if (!Integer.valueOf(i).equals(m.get(new BadHash(i)))) return false;
        }
        return true;
    }
}