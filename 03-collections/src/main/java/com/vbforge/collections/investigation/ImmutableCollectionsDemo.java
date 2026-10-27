package com.vbforge.collections.investigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * INVESTIGATION — Immutable collections and read-only views.
 *
 * <ul>
 *   <li>{@code List.of(...)}, {@code Set.of(...)}, {@code Map.of(...)} — factory
 *       methods (Java 9+) that build a fully immutable collection. Cannot be modified
 *       at all. Also: NO nulls allowed; duplicates throw for {@code Set.of} / {@code Map.of}.</li>
 *   <li>{@code Collections.unmodifiableList(list)} — a read-only VIEW.
 *       The underlying list remains mutable, and mutations THROUGH THE VIEW throw,
 *       but mutations to the ORIGINAL are visible through the view. Also allows nulls.</li>
 *   <li>{@code List.copyOf(list)} — an immutable COPY. Nulls rejected. Safe if you
 *       receive a list from untrusted code and want to freeze it.</li>
 * </ul>
 */
public final class ImmutableCollectionsDemo {

    private ImmutableCollectionsDemo() {}

    // ---------------------------------------------------------------------
    // List.of — fully immutable, no nulls
    // ---------------------------------------------------------------------

    public static boolean listOfRejectsMutation() {
        List<Integer> fixed = List.of(1, 2, 3);
        try {
            fixed.add(4);
            return false;
        } catch (UnsupportedOperationException e) {
            return true;
        }
    }

    public static boolean listOfRejectsNull() {
        try {
            List.of(1, null, 3);
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }

    // ---------------------------------------------------------------------
    // unmodifiableList — a VIEW, not a copy
    // ---------------------------------------------------------------------

    /**
     * The VIEW rejects mutations, but changes to the underlying list
     * are still visible through it.
     */
    public static List<Integer> unmodifiableViewReflectsParent() {
        List<Integer> mutable = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> view = Collections.unmodifiableList(mutable);
        mutable.add(4);                                 // does NOT go through the view
        return view;                                    // [1, 2, 3, 4]
    }

    public static boolean unmodifiableViewRejectsMutation() {
        List<Integer> view = Collections.unmodifiableList(new ArrayList<>(List.of(1, 2, 3)));
        try {
            view.add(4);
            return false;
        } catch (UnsupportedOperationException e) {
            return true;
        }
    }

    // ---------------------------------------------------------------------
    // List.copyOf — an immutable COPY (safe against later mutations to source)
    // ---------------------------------------------------------------------

    public static List<Integer> copyOfIsDetachedFromSource() {
        List<Integer> mutable = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> snapshot = List.copyOf(mutable);
        mutable.add(99);                                // does NOT affect snapshot
        return snapshot;                                // [1, 2, 3]
    }

    // ---------------------------------------------------------------------
    // Set.of and Map.of — same story
    // ---------------------------------------------------------------------

    public static Set<String> setOf() {
        return Set.of("a", "b", "c");                   // iteration order NOT guaranteed
    }

    public static Map<String, Integer> mapOf() {
        return Map.of("one", 1, "two", 2, "three", 3);
    }

    /**
     * {@code Map.copyOf} rejects null KEYS and null VALUES by throwing NPE at
     * copy time — the source can be a mutable {@code HashMap} that does allow nulls.
     * The null must be present in the source BEFORE the copy.
     */
    public static boolean mapOfRejectsNull() {
        try {
            Map<String, Integer> source = new HashMap<>();
            source.put("k", null);                      // allowed by HashMap…
            Map.copyOf(source);                         // …but NOT by copyOf → NPE
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }
}