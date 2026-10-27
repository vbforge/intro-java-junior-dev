package com.vbforge.oop.investigation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * INVESTIGATION — {@code equals}, {@code hashCode}, {@code toString}.
 *
 * <p>The contracts:
 * <ul>
 *   <li>{@code equals}: reflexive, symmetric, transitive, consistent, and {@code x.equals(null) == false}.</li>
 *   <li>{@code hashCode}: equal objects MUST have equal hashCodes. Unequal objects SHOULD
 *       (for good performance) have different ones.</li>
 *   <li>Together: if you override one, you MUST override the other — collections rely on both.</li>
 *   <li>{@code toString}: not formally a "contract" like the above, but its default output
 *       ({@code ClassName@hash}) is useless for debugging.</li>
 * </ul>
 */
public final class ObjectContractDemo {

    private ObjectContractDemo() {}

    // ---------------------------------------------------------------------
    // BROKEN: equals() but no hashCode()
    // ---------------------------------------------------------------------

    /**
     * GOTCHA: this class overrides {@code equals} but not {@code hashCode}.
     * Logically, two {@code BrokenPoint(1,2)} are "equal", but {@code HashSet}
     * uses hashCode first — so lookups silently fail.
     */
    public static class BrokenPoint {
        final int x, y;

        public BrokenPoint(int x, int y) { this.x = x; this.y = y; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BrokenPoint p)) return false;
            return x == p.x && y == p.y;
        }
        // hashCode NOT overridden — inherits Object's identity hash.
    }

    // ---------------------------------------------------------------------
    // CORRECT: equals + hashCode + toString
    // ---------------------------------------------------------------------

    public static class Point {
        final int x, y;

        public Point(int x, int y) { this.x = x; this.y = y; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;                 // fast path
            if (!(o instanceof Point p)) return false;  // null / wrong type
            return x == p.x && y == p.y;
        }

        @Override public int hashCode() {
            return Objects.hash(x, y);                  // consistent with equals
        }

        @Override public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    // ---------------------------------------------------------------------
    // Demonstration
    // ---------------------------------------------------------------------

    public static boolean brokenPointFoundInHashSet() {
        Set<BrokenPoint> set = new HashSet<>();
        set.add(new BrokenPoint(1, 2));
        return set.contains(new BrokenPoint(1, 2));     // false — equals says "equal" but hashCode differs
    }

    public static boolean correctPointFoundInHashSet() {
        Set<Point> set = new HashSet<>();
        set.add(new Point(1, 2));
        return set.contains(new Point(1, 2));           // true
    }

    public static boolean equalsIsReflexive() {
        Point p = new Point(3, 4);
        return p.equals(p);
    }

    public static boolean equalsIsNullSafe() {
        Point p = new Point(3, 4);
        return p.equals(null);                          // must be false, not NullPointerException
    }

    public static String toStringIsUseful() {
        return new Point(7, 8).toString();              // "Point(7, 8)"
    }
}