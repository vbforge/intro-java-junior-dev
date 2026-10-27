package com.vbforge.streams.investigation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * INVESTIGATION — Parallel streams.
 *
 * <p>Parallel streams can help CPU-bound work on large data with stateless, associative operations.
 * They can also SLOW YOU DOWN and, worse, silently produce wrong results if you break the rules.
 *
 * <p>Rules of thumb:
 * <ul>
 *   <li>Data must be LARGE (usually 10k+ elements) — otherwise overhead dominates.</li>
 *   <li>Operations must be STATELESS and ASSOCIATIVE (order-independent).</li>
 *   <li>Never mutate shared mutable state inside {@code forEach} — use {@code collect}.</li>
 *   <li>I/O-bound work does NOT benefit — it just blocks more threads.</li>
 * </ul>
 */
public final class ParallelStreamsDemo {

    private ParallelStreamsDemo() {}

    /**
     * Sum is associative, so parallel and sequential MUST produce the same result.
     * This is the "safe" case.
     */
    public static long parallelSumMatchesSequential(int n) {
        long sequential = IntStream.rangeClosed(1, n).sum();
        long parallel   = IntStream.rangeClosed(1, n).parallel().sum();
        return sequential - parallel;                   // 0
    }

    /**
     * {@code collect(Collectors.toList())} on an ORDERED source preserves encounter order
     * even when the stream is parallel. The result equals a sequential collection.
     */
    public static boolean collectPreservesOrderInParallel(int n) {
        List<Integer> result = IntStream.range(0, n).boxed().parallel().collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            if (result.get(i) != i) return false;
        }
        return true;
    }

    /**
     * {@code forEachOrdered} preserves encounter order on parallel streams.
     * Per the JDK spec, the consumer is invoked in encounter order — so writing into a
     * plain {@link java.util.ArrayList} from {@code forEachOrdered} is safe. (Contrast
     * with {@code forEach} on a parallel stream — there, appending to a plain
     * {@code ArrayList} would be a data race.)
     *
     * @return the size {@code n} if the collected elements are exactly {@code [0, 1, ..., n-1]},
     *         otherwise {@code -1}.
     */
    public static int forEachOrderedIsSequential(int n) {
        List<Integer> result = new java.util.ArrayList<>();
        IntStream.range(0, n).parallel()
                .forEachOrdered(result::add);

        List<Integer> expected = IntStream.range(0, n).boxed()
                .collect(Collectors.toList());

        return result.equals(expected) ? n : -1;
    }

    /** Reduction in parallel — must be associative. */
    public static int parallelReduceSum(int n) {
        return IntStream.rangeClosed(1, n).parallel().reduce(0, Integer::sum);
    }
}