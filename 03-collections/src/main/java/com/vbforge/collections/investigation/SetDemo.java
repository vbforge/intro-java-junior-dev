package com.vbforge.collections.investigation;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * INVESTIGATION — {@link Set}: HashSet, LinkedHashSet, TreeSet.
 *
 * <pre>
 *                  order                   add/contains        needs
 *   HashSet        unpredictable           O(1) avg            equals+hashCode
 *   LinkedHashSet  insertion order         O(1) avg            equals+hashCode
 *   TreeSet        sorted (natural/cmp)    O(log n)            Comparable OR Comparator
 * </pre>
 */
public final class SetDemo {

    private SetDemo() {}

    /** Duplicates collapse into one entry, regardless of insertion order. */
    public static int hashSetSize() {
        Set<Integer> set = new HashSet<>();
        set.add(1); set.add(2); set.add(1); set.add(3); set.add(2);
        return set.size();                              // 3
    }

    /** LinkedHashSet preserves the FIRST occurrence order (insertion order). */
    public static Integer[] linkedHashSetOrder() {
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3); set.add(1); set.add(2); set.add(1);  // 1 is a duplicate, ignored
        return set.toArray(Integer[]::new);              // [3, 1, 2]
    }

    /** TreeSet keeps the elements in natural (ascending) sorted order. */
    public static Integer[] treeSetOrder() {
        Set<Integer> set = new TreeSet<>();
        set.add(3); set.add(1); set.add(2);
        return set.toArray(Integer[]::new);              // [1, 2, 3]
    }

    /** TreeSet with a custom comparator — here, descending. */
    public static Integer[] treeSetDescending() {
        Set<Integer> set = new TreeSet<>((a, b) -> Integer.compare(b, a));
        set.add(3); set.add(1); set.add(2);
        return set.toArray(Integer[]::new);              // [3, 2, 1]
    }

    // ---------------------------------------------------------------------
    // Set math
    // ---------------------------------------------------------------------

    public static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);                               // addAll == union
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.retainAll(b);                            // retainAll == intersection
        return result;
    }

    public static Set<Integer> difference(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.removeAll(b);                            // removeAll == difference
        return result;
    }

    public static boolean isSubset(Set<Integer> a, Set<Integer> b) {
        return b.containsAll(a);                        // a ⊆ b iff b contains all of a
    }
}