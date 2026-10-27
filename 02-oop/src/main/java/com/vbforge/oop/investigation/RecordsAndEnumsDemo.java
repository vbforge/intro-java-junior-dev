package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Records (Java 16+) and Enums.
 */
public final class RecordsAndEnumsDemo {

    private RecordsAndEnumsDemo() {}

    // ---------------------------------------------------------------------
    // Records — compact data carriers
    // ---------------------------------------------------------------------

    /**
     * A record gives you AUTOMATICALLY:
     * <ul>
     *   <li>{@code private final} fields for each component.</li>
     *   <li>A canonical constructor.</li>
     *   <li>Accessor methods named after the components.</li>
     *   <li>{@code equals}, {@code hashCode}, and {@code toString}.</li>
     * </ul>
     * A record is implicitly {@code final} — you cannot extend it.
     */
    public record Point(int x, int y) {}

    /**
     * You may add a COMPACT constructor for validation — the fields are assigned automatically
     * AFTER the block runs. You don't re-assign them yourself.
     */
    public record Range(int from, int to) {
        public Range {
            if (from > to) {
                throw new IllegalArgumentException("from must be <= to, got " + from + ".." + to);
            }
        }

        // Extra methods are fine:
        public int length()             { return to - from; }
        public boolean contains(int v)  { return v >= from && v <= to; }
    }

    public static boolean recordsHaveValueEquals() {
        return new Point(1, 2).equals(new Point(1, 2)); // true — auto-generated equals
    }

    public static int recordsHaveConsistentHashCode() {
        return new Point(1, 2).hashCode() == new Point(1, 2).hashCode() ? 1 : 0;
    }

    public static String recordsHaveReadableToString() {
        return new Point(3, 4).toString();              // "Point[x=3, y=4]"
    }

    public static String recordValidation() {
        try {
            new Range(5, 2);
            return "no exception";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // ---------------------------------------------------------------------
    // Enums — fixed set of instances
    // ---------------------------------------------------------------------

    /** A simple enum. Each constant is a singleton instance. */
    public enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN;

        public boolean isWeekend() {
            return this == SAT || this == SUN;
        }
    }

    /**
     * An enum with fields and a constructor — the classic "Planet" example
     * from Joshua Bloch's Effective Java.
     */
    public enum Planet {
        MERCURY(3.303e+23, 2.4397e6),
        VENUS  (4.869e+24, 6.0518e6),
        EARTH  (5.976e+24, 6.37814e6),
        MARS   (6.421e+23, 3.3972e6);

        private final double mass;                      // kg
        private final double radius;                    // m

        Planet(double mass, double radius) {
            this.mass   = mass;
            this.radius = radius;
        }

        public double mass()   { return mass; }
        public double radius() { return radius; }

        /** Surface gravity — an instance method on the enum. */
        public double surfaceGravity() {
            final double G = 6.67300E-11;
            return G * mass / (radius * radius);
        }
    }

    public static boolean enumIsSingleton() {
        return Day.MON == Day.MON;                      // enums are singletons — '==' is safe
    }

    public static String enumSwitchExpression(Day d) {
        // Pattern matching + switch works well with enums.
        return switch (d) {
            case MON, TUE, WED, THU, FRI -> "weekday";
            case SAT, SUN                -> "weekend";
        };
    }

    public static int valuesOrder() {
        return Day.values().length;                     // 7 — insertion order
    }

    public static Planet valueOfWorks() {
        return Planet.valueOf("EARTH");
    }
}