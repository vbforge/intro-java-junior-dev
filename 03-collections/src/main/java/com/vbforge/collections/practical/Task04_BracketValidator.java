package com.vbforge.collections.practical;

/**
 * PRACTICAL TASK 04 — {@code Deque} used as a stack.
 *
 * <p>Validate that brackets in a string are balanced. Bracket pairs:
 * {@code ()}, {@code []}, {@code {}}. Non-bracket characters are ignored.
 *
 * <p>Rules:
 * <ul>
 *   <li>Every opener must have a matching closer of the SAME type, in LIFO order.</li>
 *   <li>Closers with nothing to close → invalid.</li>
 *   <li>Unclosed openers at the end → invalid.</li>
 *   <li>Empty or all-non-bracket string → valid.</li>
 * </ul>
 *
 * <p>Examples:
 * <pre>
 *   "()"          → true
 *   "()[]{}"      → true
 *   "([{}])"      → true
 *   "(]"          → false
 *   "([)]"        → false   (LIFO violated)
 *   "((("         → false   (unclosed)
 *   "a(b)c"       → true    (non-brackets ignored)
 * </pre>
 *
 * Hint: use {@link java.util.ArrayDeque} as a stack ({@code push}/{@code pop}/{@code peek}).
 */
public final class Task04_BracketValidator {

    private Task04_BracketValidator() {}

    public static boolean isValid(String s) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}