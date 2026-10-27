package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 02 — Abstract class + inheritance + polymorphism.
 *
 * <p>Implement an abstract {@code Shape} and three concrete subclasses.
 */
public abstract class Task02_Shape {

    /** A human-readable name, e.g. {@code "Circle"}, {@code "Rectangle"}, {@code "Triangle"}. */
    public abstract String name();

    public abstract double area();

    public abstract double perimeter();

    /**
     * A readable description. Format:
     * {@code "<name>: area=<area>, perimeter=<perimeter>"}
     * with each number formatted by {@link String#format(String, Object...)} using {@code "%.2f"}.
     */
    public String describe() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Static factory-style utility: sum of {@code area()} over an array of shapes.
     * Null or empty array → 0.0.
     */
    public static double totalArea(Task02_Shape[] shapes) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the shape in {@code shapes} with the largest area.
     * @throws IllegalArgumentException if the array is null or empty.
     * Hint: use {@code instanceof}? No — use {@code area()}. All subclasses have it.
     */
    public static Task02_Shape largest(Task02_Shape[] shapes) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Concrete subclasses — implement these as nested static classes
    // ---------------------------------------------------------------------

    /**
     * A circle.
     * @throws IllegalArgumentException in the constructor if {@code radius <= 0}.
     */
    public static final class Circle extends Task02_Shape {
        public Circle(double radius) {
            throw new UnsupportedOperationException("TODO: implement");
        }
        @Override public String name()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double area()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double perimeter() { throw new UnsupportedOperationException("TODO"); }
    }

    /**
     * A rectangle.
     * @throws IllegalArgumentException if {@code width <= 0} or {@code height <= 0}.
     */
    public static final class Rectangle extends Task02_Shape {
        public Rectangle(double width, double height) {
            throw new UnsupportedOperationException("TODO: implement");
        }
        @Override public String name()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double area()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double perimeter() { throw new UnsupportedOperationException("TODO"); }
    }

    /**
     * A triangle with sides a, b, c.
     * @throws IllegalArgumentException if any side {@code <= 0} or the sides
     *         don't satisfy the triangle inequality ({@code a + b > c},
     *         {@code a + c > b}, {@code b + c > a}).
     * Hint for area: Heron's formula — {@code s = (a+b+c)/2}, {@code area = sqrt(s(s-a)(s-b)(s-c))}.
     */
    public static final class Triangle extends Task02_Shape {
        public Triangle(double a, double b, double c) {
            throw new UnsupportedOperationException("TODO: implement");
        }
        @Override public String name()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double area()      { throw new UnsupportedOperationException("TODO"); }
        @Override public double perimeter() { throw new UnsupportedOperationException("TODO"); }
    }
}