package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Classes, objects, instance vs static, constructors, {@code this}.
 */
public final class ClassesAndObjectsDemo {

    private ClassesAndObjectsDemo() {}

    // ---------------------------------------------------------------------
    // A minimal class with an instance field, a static field, and a constructor
    // ---------------------------------------------------------------------

    /**
     * A counter that remembers its own state per instance.
     * Its {@code instancesCreated} field is shared across ALL instances.
     */
    public static class Counter {
        /** Shared across every instance — one copy for the whole class. */
        private static int instancesCreated = 0;

        /** Per-instance state. */
        private int value;

        public Counter() {
            this(0);                                    // constructor chaining via this(...)
        }

        public Counter(int initialValue) {
            this.value = initialValue;                  // 'this.value' disambiguates from parameter
            instancesCreated++;
        }

        public void increment() { value++; }
        public int value()     { return value; }

        public static int instancesCreated() { return instancesCreated; }
    }

    /** Demonstrates per-instance isolation and shared static state. */
    public static int[] incrementTwoIndependently() {
        Counter a = new Counter(10);
        Counter b = new Counter(100);
        a.increment();
        a.increment();
        b.increment();
        return new int[]{ a.value(), b.value() };       // {12, 101}
    }

    public static int instancesEverCreated() {
        return Counter.instancesCreated();
    }

    // ---------------------------------------------------------------------
    // 'this' also lets a constructor call another constructor (chaining)
    // and lets an instance method return itself for a fluent API.
    // ---------------------------------------------------------------------

    public static class StringBuilderLike {
        private final StringBuilder sb = new StringBuilder();

        public StringBuilderLike append(String s) {
            sb.append(s);
            return this;                                // return 'this' → chaining
        }

        @Override public String toString() { return sb.toString(); }
    }

    public static String fluentChain() {
        return new StringBuilderLike()
                .append("hello")
                .append(" ")
                .append("world")
                .toString();                            // "hello world"
    }
}