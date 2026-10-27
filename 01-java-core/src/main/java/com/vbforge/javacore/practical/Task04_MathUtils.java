package com.vbforge.javacore.practical;

/**
 * PRACTICAL TASK 04 — Methods & loops.
 */
public final class Task04_MathUtils {

    private Task04_MathUtils() {}

    /**
     * {@code n!}. Define {@code 0! == 1}.
     * @throws IllegalArgumentException if {@code n < 0}.
     * Hint: return {@code long} to avoid overflow before {@code n == 20}.
     */
    public static long factorial(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Prime check.
     * <ul>
     *   <li>{@code n < 2 → false}</li>
     *   <li>{@code 2 → true}</li>
     *   <li>{@code 17 → true}, {@code 15 → false}</li>
     * </ul>
     * Hint: check divisibility from 2 up to {@code sqrt(n)} only — that's O(sqrt(n)).
     */
    public static boolean isPrime(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Euclidean GCD. Both zero → 0; negative values treated as their absolute value.
     * <ul>
     *   <li>{@code gcd(12, 18) → 6}</li>
     *   <li>{@code gcd(0, 5) → 5}</li>
     *   <li>{@code gcd(17, 13) → 1}</li>
     *   <li>{@code gcd(-12, 18) → 6}</li>
     * </ul>
     */
    public static int gcd(int a, int b) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}