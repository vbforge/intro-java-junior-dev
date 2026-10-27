package com.vbforge.javacore.investigation;

/**
 * INVESTIGATION — Control flow.
 *
 * <p>Covers {@code if/else}, classic {@code switch}, arrow {@code switch} (Java 14+),
 * {@code for}, {@code while}, {@code do-while}, enhanced {@code for}, and labeled {@code break}/{@code continue}.
 */
public final class ControlFlowDemo {

    private ControlFlowDemo() {}

    /** Classic {@code if/else if/else}. */
    public static String classify(int n) {
        if (n < 0) {
            return "negative";
        } else if (n == 0) {
            return "zero";
        } else {
            return "positive";
        }
    }

    /**
     * Classic {@code switch}. Requires {@code break} to prevent fall-through.
     * Works with {@code int}, {@code char}, {@code String}, and {@code enum}.
     */
    public static String dayTypeClassic(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1: case 2: case 3: case 4: case 5:
                return "weekday";
            case 6: case 7:
                return "weekend";
            default:
                return "invalid";
        }
    }

    /**
     * Arrow {@code switch} expression (Java 14+).
     * <ul>
     *   <li>Returns a value directly (it's an expression, not a statement).</li>
     *   <li>No fall-through — no {@code break} needed, and multiple labels use commas.</li>
     *   <li>No {@code default} is required if all cases are covered (for enums/sealed types).</li>
     * </ul>
     */
    public static String dayTypeArrow(int dayOfWeek) {
        return switch (dayOfWeek) {
            case 1, 2, 3, 4, 5 -> "weekday";
            case 6, 7         -> "weekend";
            default           -> "invalid";   // still required for int input
        };
    }

    /** Sum {@code 1..n} with a classic {@code for} loop. */
    public static int sumFor(int n) {
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += i;
        }
        return total;
    }

    /** Same with a {@code while}. */
    public static int sumWhile(int n) {
        int total = 0;
        int i = 1;
        while (i <= n) {
            total += i;
            i++;
        }
        return total;
    }

    /**
     * {@code do-while} runs at least once — useful when the body must execute
     * regardless of the condition (e.g., user-input loops).
     */
    public static int doWhileRunsAtLeastOnce() {
        int executions = 0;
        do {
            executions++;
        } while (false);
        return executions;                 // 1
    }

    /** Enhanced {@code for} over an array — clean when you only need values. */
    public static int sumEnhancedFor(int[] values) {
        int total = 0;
        for (int v : values) {
            total += v;
        }
        return total;
    }

    /**
     * Labeled {@code break} — jumps out of BOTH loops at once.
     * Without a label, {@code break} only exits the inner loop.
     */
    public static int[] findPair(int[][] grid, int target) {
        outer:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Labeled {@code continue} — skips to the next iteration of the OUTER loop
     * rather than the inner one.
     */
    public static int countRowsSkippingOne(int[][] grid) {
        int count = 0;
        outer:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    continue outer;        // skips the rest of row i
                }
            }
            count++;                       // only counted if no negative was seen
        }
        return count;
    }
}