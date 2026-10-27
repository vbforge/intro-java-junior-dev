package com.vbforge.javacore.investigation;

import java.util.Arrays;

/**
 * INVESTIGATION — Methods: overloading, varargs, and the "pass-by-value" myth.
 */
public final class MethodsDemo {

    private MethodsDemo() {}

    // ---------------------------------------------------------------------
    // Overloading — same name, different parameter list (return type alone is NOT enough)
    // ---------------------------------------------------------------------

    public static String describe(int x)    { return "int: "    + x; }
    public static String describe(long x)   { return "long: "   + x; }
    public static String describe(double x) { return "double: " + x; }
    public static String describe(String x) { return "String: " + x; }

    // ---------------------------------------------------------------------
    // Varargs
    // ---------------------------------------------------------------------

    /**
     * Varargs compile to an array. The caller can pass 0..N arguments
     * OR pass an array directly. Must be the LAST parameter.
     */
    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    // ---------------------------------------------------------------------
    // Pass-by-value — the classic gotcha
    // ---------------------------------------------------------------------

    /**
     * Primitives are passed by value: the method gets a COPY.
     * Assigning a new value inside the method does NOT affect the caller.
     */
    public static void tryToChangeInt(int x) {
        x = 999;                           // caller unaffected
    }

    /**
     * References are passed by value: the method gets a COPY of the REFERENCE.
     * You CAN mutate the object it points to (visible to caller), but reassigning the
     * local reference to a new object does NOT change the caller's variable.
     */
    public static void mutateArray(int[] arr) {
        arr[0] = 42;                       // caller sees this
    }

    public static void reassignArrayLocal(int[] arr) {
        arr = new int[]{9, 9, 9};          // caller does NOT see this
    }

    public static void demonstrateBoth() {
        int x = 1;
        tryToChangeInt(x);                 // x is still 1
        // ---
        int[] a = {1, 2, 3};
        mutateArray(a);                    // a is now [42, 2, 3]
        // ---
        int[] b = {1, 2, 3};
        reassignArrayLocal(b);             // b is still [1, 2, 3]
        System.out.println(Arrays.toString(b));
    }
}