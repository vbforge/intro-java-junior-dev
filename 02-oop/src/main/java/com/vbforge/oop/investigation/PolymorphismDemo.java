package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Polymorphism.
 *
 * <ul>
 *   <li><b>Compile-time polymorphism</b> = method overloading (chosen by the compiler from argument types).</li>
 *   <li><b>Runtime polymorphism</b>   = method overriding (chosen by the JVM from the ACTUAL object type).</li>
 * </ul>
 */
public final class PolymorphismDemo {

    private PolymorphismDemo() {}

    public static class Animal {
        public String sound() { return "..."; }
    }

    public static class Dog extends Animal {
        @Override public String sound() { return "Woof"; }
    }

    public static class Cat extends Animal {
        @Override public String sound() { return "Meow"; }
    }

    // ---------------------------------------------------------------------
    // Runtime dispatch
    // ---------------------------------------------------------------------

    /**
     * Dynamic dispatch: the JVM calls the OVERRIDE that matches the ACTUAL object,
     * not the declared variable type.
     */
    public static String[] allSounds() {
        Animal[] animals = { new Dog(), new Cat(), new Animal() };
        String[] sounds = new String[animals.length];
        for (int i = 0; i < animals.length; i++) {
            sounds[i] = animals[i].sound();             // {"Woof", "Meow", "..."}
        }
        return sounds;
    }

    // ---------------------------------------------------------------------
    // Up-casting is always safe; down-casting can fail
    // ---------------------------------------------------------------------

    /** Up-cast (Dog → Animal) is always safe and implicit. */
    public static Animal upcast() {
        Dog d = new Dog();
        Animal a = d;                                   // implicit upcast
        return a;
    }

    /**
     * Down-cast (Animal → Dog) requires an explicit cast and can fail at runtime
     * with {@link ClassCastException}. Use {@code instanceof} to check first.
     */
    public static String safeDowncast(Animal a) {
        if (a instanceof Dog dog) {                     // instanceof + binding (Java 16+)
            return "Dog says " + dog.sound();
        }
        if (a instanceof Cat cat) {
            return "Cat says " + cat.sound();
        }
        return "Unknown animal";
    }

    // ---------------------------------------------------------------------
    // Compile-time polymorphism via overloading
    // ---------------------------------------------------------------------

    public static String overloaded(int x)    { return "int " + x; }
    public static String overloaded(long x)   { return "long " + x; }
    public static String overloaded(double x) { return "double " + x; }
}