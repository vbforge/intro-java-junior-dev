package com.vbforge.collections.investigation;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * INVESTIGATION — Iterators and fail-fast semantics.
 *
 * <p>Most collection iterators are <b>fail-fast</b>: they carry a "modCount" snapshot
 * and throw {@link ConcurrentModificationException} on the NEXT {@code next()}
 * call if the underlying collection was structurally modified since the iterator
 * was created (unless the modification is via {@code Iterator.remove()} itself).
 *
 * <p>NOTE: fail-fast is a best-effort DETECTION, not a guarantee. It is NOT a
 * substitute for real thread-safety.
 */
public final class IteratorDemo {

    private IteratorDemo() {}

    private static List<Integer> range(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) list.add(i);
        return list;
    }

    // ---------------------------------------------------------------------
    // The happy path: iterate + collect
    // ---------------------------------------------------------------------

    public static List<Integer> collectOdd(int n) {
        List<Integer> src = range(n);
        List<Integer> out = new ArrayList<>();
        for (Integer x : src) {
            if (x % 2 == 1) out.add(x);
        }
        return out;                                     // [1, 3, 5, ...]
    }

    // ---------------------------------------------------------------------
    // THE classic bug: modifying during for-each
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: this throws {@link ConcurrentModificationException}.
     * The for-each loop uses an iterator under the hood; {@code list.add} bumps
     * the modCount; the next {@code next()} call detects the mismatch.
     */
    public static void addDuringForEachThrows() {
        List<Integer> list = range(3);
        for (Integer x : list) {
            list.add(99);                               // never mind the infinite loop — it throws first
        }
    }

    // ---------------------------------------------------------------------
    // The two safe ways to remove
    // ---------------------------------------------------------------------

    /** SAFE: {@code Iterator.remove()} is aware of the iterator's own bookkeeping. */
    public static List<Integer> removeEvenWithIterator(int n) {
        List<Integer> list = range(n);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }
        return list;                                    // [1, 3, 5, ...]
    }

    /** SAFE and simpler: {@code removeIf}. Internally it uses {@code Iterator.remove}. */
    public static List<Integer> removeEvenWithRemoveIf(int n) {
        List<Integer> list = range(n);
        list.removeIf(x -> x % 2 == 0);
        return list;
    }

    // ---------------------------------------------------------------------
    // The sneaky case: modification that does NOT throw
    // ---------------------------------------------------------------------

    /**
     * GOTCHA variant: NOT every in-loop modification throws CME.
     * The fail-fast check lives inside {@code next()}, not {@code hasNext()}.
     * So if, after removal, the iterator's {@code cursor} happens to equal
     * the new {@code size}, {@code hasNext()} returns {@code false},
     * {@code next()} is never called, and the fail-fast check never runs.
     * The removal goes through — and the element right AFTER the removed one
     * is silently SKIPPED (never visited).
     *
     * <p>Concretely with {@code [1, 2, 3]} removing the SECOND-TO-LAST element (2):
     * <pre>
     *   next() → 1       cursor = 1
     *   next() → 2       cursor = 2
     *   body:  list.remove(2)      → list = [1, 3], size = 2, modCount++
     *   hasNext(): cursor(2) != size(2) → false → loop exits. No CME.
     *   "3" was never visited — silently lost from the traversal.
     * </pre>
     *
     * <p>By contrast, removing the LAST element DOES throw CME:
     * <pre>
     *   next() → 3       cursor = 3
     *   body:  list.remove(3)      → list = [1, 2], size = 2, modCount++
     *   hasNext(): cursor(3) != size(2) → true
     *   next(): checkForComodification() → ConcurrentModificationException
     * </pre>
     */
    public static List<Integer> removeSecondToLastSilentlySkips() {
        List<Integer> list = range(3);                  // [1, 2, 3]
        for (Integer x : list) {
            if (x == 2) list.remove(x);                 // removes index 1 — cursor(2) == newSize(2)
        }
        return list;                                    // [1, 3] — 3 was never visited
    }
}