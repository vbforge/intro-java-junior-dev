package com.vbforge.javacore.practical;

/**
 * A custom UNCHECKED exception thrown by {@link Task05_SafeDivider} when the divisor is zero.
 *
 * <p>Task: student does not need to modify this file — it's provided as a template
 * to show how to extend {@code RuntimeException} with a message-only constructor.
 */
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}