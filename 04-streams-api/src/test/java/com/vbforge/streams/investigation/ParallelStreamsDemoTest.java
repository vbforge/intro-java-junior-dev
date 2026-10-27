package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelStreamsDemoTest {

    @Test void parallelSumMatchesSequential() {
        // difference must be 0 — sum is associative
        assertThat(ParallelStreamsDemo.parallelSumMatchesSequential(1_000_000)).isZero();
    }

    @Test void collectPreservesOrderInParallel() {
        assertThat(ParallelStreamsDemo.collectPreservesOrderInParallel(1000)).isTrue();
    }

    @Test void forEachOrderedIsSequential() {
        assertThat(ParallelStreamsDemo.forEachOrderedIsSequential(100)).isEqualTo(100);
    }

    @Test void parallelReduceSum() {
        assertThat(ParallelStreamsDemo.parallelReduceSum(100)).isEqualTo(5050);
    }
}