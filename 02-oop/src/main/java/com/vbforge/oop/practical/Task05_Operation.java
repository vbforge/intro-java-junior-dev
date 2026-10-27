package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 05 — Enum with fields and methods.
 *
 * <p>Implement an enum {@code Operation} representing {@code PLUS, MINUS, MULTIPLY, DIVIDE},
 * each with a {@code symbol} ({@code "+"}, {@code "-"}, {@code "*"}, {@code "/"})
 * and an arithmetic method.
 */
public enum Task05_Operation {

    // TODO: declare constants with symbol arguments

    ;
    // TODO: private final String symbol; + constructor

    /** Return the symbol: {@code "+"}, {@code "-"}, {@code "*"}, or {@code "/"}. */
    public String symbol() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Apply this operation to two operands.
     * Division uses integer division. {@code DIVIDE} by zero →
     * {@link ArithmeticException} with message {@code "Division by zero"}.
     */
    public int apply(int a, int b) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the operation matching the given symbol.
     * @throws IllegalArgumentException if no operation matches (message must contain the symbol).
     */
    public static Task05_Operation fromSymbol(String symbol) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}