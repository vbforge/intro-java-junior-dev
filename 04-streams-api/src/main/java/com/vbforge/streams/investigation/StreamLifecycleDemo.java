package com.vbforge.streams.investigation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * INVESTIGATION — Stream lifecycle: lazy vs eager, one-shot, {@code peek} timing.
 *
 * <p>Key facts:
 * <ol>
 *   <li>A stream is <b>one-shot</b>. Terminal operation consumes it; a second terminal call throws {@link IllegalStateException}.</li>
 *   <li>Intermediate operations are <b>lazy</b>: nothing runs until a terminal op is invoked.</li>
 *   <li>{@code peek()} is intended for debugging — it isn't a "forEach" replacement, and in some cases
 *       (e.g. some optimizations of {@code count()} on sized sources) it may not be invoked at all.
 *       We only test it inside a pipeline that must visit every element.</li>
 * </ol>
 */
public final class StreamLifecycleDemo {

    private StreamLifecycleDemo() {}

    // ---------------------------------------------------------------------
    // Lazy: nothing runs until the terminal op
    // ---------------------------------------------------------------------

    public static boolean noWorkUntilTerminal() {
        List<Integer> log = new ArrayList<>();
        Stream<Integer> stream = Stream.of(1, 2, 3).peek(log::add);
        boolean nothingRan = log.isEmpty();             // still empty — no terminal op yet
        stream.collect(Collectors.toList());            // NOW the pipeline runs
        return nothingRan && log.size() == 3;
    }

    // ---------------------------------------------------------------------
    // One-shot: reuse throws
    // ---------------------------------------------------------------------

    public static boolean reuseThrows() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.collect(Collectors.toList());
        try {
            stream.collect(Collectors.toList());
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    // ---------------------------------------------------------------------
    // Short-circuiting terminal operations stop early
    // ---------------------------------------------------------------------

    /**
     * {@code anyMatch} stops at the first match — it does NOT traverse the whole stream.
     * Proof: {@code peek} runs at most as far as the matching element.
     */
    public static boolean anyMatchStopsEarly() {
        List<Integer> visited = new ArrayList<>();
        boolean found = Stream.of(1, 2, 3, 4, 5)
                .peek(visited::add)
                .anyMatch(x -> x == 2);                 // stops after 2
        return found && visited.equals(List.of(1, 2));
    }

    /**
     * {@code limit} makes an infinite stream finite.
     */
    public static List<Integer> limitOnInfinite() {
        return Stream.iterate(1, x -> x + 1)
                .limit(3)
                .collect(Collectors.toList());          // [1, 2, 3]
    }

    // ---------------------------------------------------------------------
    // Where peek runs in a full pipeline
    // ---------------------------------------------------------------------

    /**
     * With a forcing terminal op like {@code collect(toList())}, every element goes through {@code peek}.
     * Order of logs matches encounter order (sequential stream).
     */
    public static List<Integer> peekLogsAllElements() {
        List<Integer> log = new ArrayList<>();
        IntStream.rangeClosed(1, 4).boxed()
                .filter(x -> x % 2 == 0)
                .peek(log::add)
                .collect(Collectors.toList());
        return log;                                     // [2, 4]
    }

    // ---------------------------------------------------------------------
    // Fresh streams on every call
    // ---------------------------------------------------------------------

    /**
     * A method that RETURNS a stream hands out a fresh one each time, so callers
     * can consume it independently. A method that returns a stream that has already
     * been partially consumed cannot.
     */
    public static Stream<Integer> freshStream() {
        return IntStream.rangeClosed(1, 3).boxed();
    }

    public static int consumeTwoFreshStreams() {
        int a = freshStream().mapToInt(Integer::intValue).sum();    // 6
        int b = freshStream().mapToInt(Integer::intValue).sum();    // 6
        return a + b;                                               // 12
    }
}