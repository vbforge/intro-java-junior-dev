package com.vbforge.javacore.investigation;

import java.io.IOException;

/**
 * INVESTIGATION — Exceptions.
 *
 * <p>Java has two broad categories:
 * <ul>
 *   <li><b>Checked</b> (subclasses of {@link Exception} but not {@link RuntimeException}) —
 *       the compiler forces you to {@code catch} or {@code throws}. E.g. {@link IOException}.</li>
 *   <li><b>Unchecked</b> (subclasses of {@link RuntimeException}) — programming errors
 *       the compiler does NOT force you to handle. E.g. {@code NullPointerException},
 *       {@code IllegalArgumentException}.</li>
 * </ul>
 * {@link Error} (e.g. {@code OutOfMemoryError}) means the JVM is in trouble — do NOT catch.
 */
public final class ExceptionsDemo {

    private ExceptionsDemo() {}

    /** A checked exception must be declared or caught — this method declares it. */
    public static void declaresCheckedException() throws IOException {
        throw new IOException("simulated");
    }

    /** An unchecked exception does not need to be declared. */
    public static void throwsUnchecked() {
        throw new IllegalStateException("simulated");
    }

    /**
     * Multi-catch: catch several unrelated checked types with one block,
     * using {@code |}. The parameter is implicitly {@code final}.
     */
    public static String multiCatch(boolean useIO) {
        try {
            if (useIO) throw new IOException("io");
            throw new InterruptedException("interrupted");
        } catch (IOException | InterruptedException e) {
            return "caught: " + e.getClass().getSimpleName();
        }
    }

    /**
     * GOTCHA: {@code finally} runs even when {@code try} returns.
     * The return in {@code finally} would OVERRIDE the try's return — do NOT do this;
     * it's shown here only to demonstrate the semantics.
     */
    public static String finallyRunsOnReturn() {
        StringBuilder log = new StringBuilder();
        try {
            log.append("try;");
            return log.append("returned").toString();
        } finally {
            log.append("finally;");
        }
    }

    /**
     * Prefer throwing with a CAUSE — it preserves the original stack trace.
     * {@code throw new RuntimeException("wrapper", e)} is the pattern.
     */
    public static void throwWithCause() {
        try {
            throw new IOException("root cause");
        } catch (IOException e) {
            throw new RuntimeException("wrapped", e);
        }
    }

    // ---------------------------------------------------------------------
    // Custom exception
    // ---------------------------------------------------------------------

    /** A custom UNCHECKED exception. Prefer this style when the error is a caller bug. */
    public static class BadInputException extends RuntimeException {
        public BadInputException(String message) {
            super(message);
        }
    }

    public static int requirePositive(int x) {
        if (x <= 0) throw new BadInputException("x must be > 0, got " + x);
        return x;
    }
}