package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Nested classes.
 *
 * <ul>
 *   <li><b>Static nested class</b> — no reference to the outer instance. Use when the class
 *       is logically part of the outer class but doesn't need its state.</li>
 *   <li><b>Inner (non-static) class</b> — holds an implicit {@code Outer.this} reference.
 *       Use when the class needs access to the outer instance.</li>
 *   <li><b>Local class</b> — declared inside a method body.</li>
 *   <li><b>Anonymous class</b> — inline one-off implementation of an interface or abstract class.</li>
 * </ul>
 */
public final class NestedClassesDemo {

    private NestedClassesDemo() {}

    // ---------------------------------------------------------------------
    // Outer with a small amount of state
    // ---------------------------------------------------------------------

    public static class Outer {
        private final int value;

        public Outer(int value) { this.value = value; }

        // ---- static nested class: no implicit outer reference ----
        public static class StaticNested {
            private final int ownValue;
            public StaticNested(int ownValue) { this.ownValue = ownValue; }
            public int ownValue()             { return ownValue; }
        }

        // ---- inner (non-static) class: has implicit Outer.this ----
        public class Inner {
            public int outerValue() { return value; }   // implicitly reads Outer.this.value
        }

        // ---- factory methods (so tests can create instances) ----
        public Inner newInner()           { return new Inner(); }
        public StaticNested newStatic(int n) { return new StaticNested(n); }
    }

    // ---------------------------------------------------------------------
    // Local class — declared inside a method
    // ---------------------------------------------------------------------

    public static String localClassDemo(int n) {
        class Doubler {                                 // local class — only visible in this method
            int twice(int x) { return 2 * x; }
        }
        return "twice(" + n + ") = " + new Doubler().twice(n);
    }

    // ---------------------------------------------------------------------
    // Anonymous class — inline implementation
    // ---------------------------------------------------------------------

    public interface Greeter {
        String greet(String name);
    }

    public static String anonymousClassDemo() {
        Greeter polite = new Greeter() {
            @Override public String greet(String name) { return "Hello, " + name; }
        };
        return polite.greet("World");                   // "Hello, World"
    }

    // ---------------------------------------------------------------------
    // Test helpers
    // ---------------------------------------------------------------------

    public static int staticNestedValue() {
        Outer.StaticNested nested = new Outer.StaticNested(99);
        return nested.ownValue();                       // 99 — no Outer instance needed
    }

    public static int innerReadsOuter() {
        Outer outer = new Outer(42);
        Outer.Inner inner = outer.newInner();
        return inner.outerValue();                      // 42
    }
}