package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StreamCreationDemoTest {

    @Test void fromList() {
        assertThat(StreamCreationDemo.fromList()).containsExactly(2, 4);
    }

    @Test void fromObjectArray() {
        assertThat(StreamCreationDemo.fromObjectArray()).containsExactly("A", "BB", "CCC");
    }

    @Test void primitiveArraySumIsCorrect() {
        assertThat(StreamCreationDemo.sumOfPrimitiveArray()).isEqualTo(10);
    }

    @Test void streamOfPrimitiveArrayIsOneElement() {
        // the classic gotcha: Stream.of(intArray) treats the whole array as one element
        assertThat(StreamCreationDemo.countOfStreamOfPrimitiveArray()).isEqualTo(1);
    }

    @Test void fromVarargs() {
        assertThat(StreamCreationDemo.fromVarargs()).containsExactly("x", "y", "z");
    }

    @Test void emptyCount() {
        assertThat(StreamCreationDemo.emptyCount()).isZero();
    }

    @Test void firstFivePowersOfTwo() {
        assertThat(StreamCreationDemo.firstFivePowersOfTwo()).containsExactly(1, 2, 4, 8, 16);
    }

    @Test void iterateWithPredicate() {
        assertThat(StreamCreationDemo.iterateWithPredicate()).containsExactly(1, 3, 5, 7, 9);
    }

    @Test void rangeExclusiveAndInclusive() {
        assertThat(StreamCreationDemo.rangeExclusive()).containsExactly(0, 1, 2, 3, 4);
        assertThat(StreamCreationDemo.rangeInclusive()).containsExactly(0, 1, 2, 3, 4, 5);
    }

    @Test void builderStream() {
        assertThat(StreamCreationDemo.builderStream()).containsExactly("a", "b", "c");
    }

    @Test void concatStreams() {
        assertThat(StreamCreationDemo.concatStreams()).containsExactly(1, 2, 3, 4);
    }

    @Test void sumRangeClosed() {
        assertThat(StreamCreationDemo.sumRangeClosed(5)).isEqualTo(15);
        assertThat(StreamCreationDemo.sumRangeClosed(0)).isZero();
    }
}