package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Inheritance: {@code extends}, {@code super}, overriding, {@code @Override}.
 *
 * <p>In Java a class can extend exactly ONE class. This single-inheritance rule
 * avoids the diamond-of-death problems that C++ has — but it means interfaces
 * are the tool for multiple "types".
 */
public final class InheritanceDemo {

    private InheritanceDemo() {}

    public static class Vehicle {
        protected final String brand;                   // 'protected' → visible to subclasses

        public Vehicle(String brand) {
            this.brand = brand;
        }

        public String describe() {
            return "Vehicle[" + brand + "]";
        }

        public String start() {
            return "Starting vehicle";
        }
    }

    public static class Car extends Vehicle {
        private final int doors;

        public Car(String brand, int doors) {
            super(brand);                               // must be first statement in constructor
            this.doors = doors;
        }

        // @Override is NOT required by the compiler — but it makes typos into errors.
        // If the signature doesn't actually match a superclass method, @Override fails to compile.
        @Override
        public String describe() {
            return "Car[" + brand + ", " + doors + " doors]";
        }

        @Override
        public String start() {
            return super.start() + " (car)";            // super.start() → "Starting vehicle (car)"
        }

        public int doors() { return doors; }
    }

    public static class ElectricCar extends Car {
        public ElectricCar(String brand) {
            super(brand, 4);
        }

        @Override
        public String start() {
            return "Silent start (electric)";           // completely replaces the parent's message
        }
    }

    // ---------------------------------------------------------------------
    // Test helpers
    // ---------------------------------------------------------------------

    public static String describeHierarchy() {
        Vehicle v = new Vehicle("Generic");
        Car c     = new Car("Toyota", 4);
        return v.describe() + " | " + c.describe();
    }

    public static String startChain() {
        // Each class either uses super.start() or fully replaces it.
        return new Car("Honda", 2).start();             // "Starting vehicle (car)"
    }

    public static String overriddenStart() {
        return new ElectricCar("Tesla").start();        // "Silent start (electric)"
    }

    /** A subclass is always an instance of its superclass. */
    public static boolean subclassIsInstanceOfSuperclass() {
        return new ElectricCar("Tesla") instanceof Vehicle;
    }
}