package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Abstract classes and interfaces.
 *
 * <p>Rule of thumb:
 * <ul>
 *   <li><b>Abstract class</b> — "is a" plus shared STATE (fields) and common code.
 *       A class can extend only one abstract class.</li>
 *   <li><b>Interface</b> — a pure contract ("can do"). A class can implement many interfaces.</li>
 * </ul>
 */
public final class AbstractAndInterfacesDemo {

    private AbstractAndInterfacesDemo() {}

    // ---------------------------------------------------------------------
    // Abstract class — has state, partially implemented methods
    // ---------------------------------------------------------------------

    public static abstract class Animal {
        protected final String name;

        protected Animal(String name) { this.name = name; }

        /** Subclasses MUST implement. */
        public abstract String sound();

        /** Shared behavior — subclasses inherit for free. */
        public String speak() {
            return name + " says " + sound();           // calls the ABSTRACT sound() dynamically
        }
    }

    public static class Dog extends Animal {
        public Dog(String name) { super(name); }
        @Override public String sound() { return "Woof"; }
    }

    public static class Cat extends Animal {
        public Cat(String name) { super(name); }
        @Override public String sound() { return "Meow"; }
    }

    // ---------------------------------------------------------------------
    // Interface — a contract, optionally with default methods
    // ---------------------------------------------------------------------

    public interface Pet {
        /** Everyone must implement this. */
        String ownerName();

        /** Default method — a fallback the implementer inherits unless they override. */
        default String greeting() {
            return "Hello, I'm a pet of " + ownerName();
        }

        /** Static helper — call on the interface itself: {@code Pet.compareNames(a, b)}. */
        static int compareNames(Pet a, Pet b) {
            return a.ownerName().compareTo(b.ownerName());
        }
    }

    /** A class can implement multiple interfaces. */
    public static class HouseCat extends Cat implements Pet {
        private final String owner;

        public HouseCat(String name, String owner) {
            super(name);
            this.owner = owner;
        }

        @Override public String ownerName() { return owner; }

        // Overriding the default method is optional:
        @Override public String greeting() {
            return "Meow — I belong to " + owner;
        }
    }

    // ---------------------------------------------------------------------
    // Test helpers
    // ---------------------------------------------------------------------

    public static String abstractPolymorphism() {
        Animal[] animals = { new Dog("Rex"), new Cat("Tom") };
        return animals[0].speak() + " | " + animals[1].speak();
    }

    public static String interfaceDefaultUsed() {
        // If a class implements Pet without overriding greeting(), the default kicks in.
        Pet p = new Pet() {
            @Override public String ownerName() { return "Alice"; }
        };
        return p.greeting();                            // "Hello, I'm a pet of Alice"
    }

    public static String interfaceDefaultOverridden() {
        return new HouseCat("Fluffy", "Bob").greeting();
    }

    public static int interfaceStaticHelper() {
        Pet a = () -> "Zoe";                            // lambda works because Pet has ONE abstract method
        Pet b = () -> "Adam";
        return Pet.compareNames(a, b);                  // "Zoe".compareTo("Adam") > 0
    }
}