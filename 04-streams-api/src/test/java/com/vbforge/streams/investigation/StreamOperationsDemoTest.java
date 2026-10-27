package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StreamOperationsDemoTest {

    @Test void evensAndDoubled() {
        assertThat(StreamOperationsDemo.evens(List.of(1, 2, 3, 4, 5, 6))).containsExactly(2, 4, 6);
        assertThat(StreamOperationsDemo.doubled(List.of(1, 2, 3))).containsExactly(2, 4, 6);
    }

    @Test void splitWordsFlattensLines() {
        assertThat(StreamOperationsDemo.splitWords(List.of("hello world", "java streams")))
                .containsExactly("hello", "world", "java", "streams");
    }

    @Test void distinctAndSorted() {
        assertThat(StreamOperationsDemo.distinct(List.of(3, 1, 2, 1, 3))).containsExactly(3, 1, 2);
        assertThat(StreamOperationsDemo.sorted(List.of(3, 1, 2))).containsExactly(1, 2, 3);
    }

    @Test void sortedByLengthThenAlpha() {
        List<String> input = List.of("ccc", "a", "bb", "aa", "b");
        assertThat(StreamOperationsDemo.sortedByLengthThenAlpha(input))
                .containsExactly("a", "b", "aa", "bb", "ccc");
    }

    @Test void limitAndSkip() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        assertThat(StreamOperationsDemo.firstThree(input)).containsExactly(1, 2, 3);
        assertThat(StreamOperationsDemo.skipTwo(input)).containsExactly(3, 4, 5);
    }

    @Test void takeWhileAndDropWhile() {
        List<Integer> input = List.of(1, 2, 5, 3, 4);
        assertThat(StreamOperationsDemo.takeWhileLessThan3(input)).containsExactly(1, 2);
        assertThat(StreamOperationsDemo.dropWhileLessThan3(input)).containsExactly(5, 3, 4);
    }

    @Test void reduceWithAndWithoutIdentity() {
        assertThat(StreamOperationsDemo.sumViaReduce(List.of(1, 2, 3))).contains(6);
        assertThat(StreamOperationsDemo.sumViaReduce(List.of())).isEmpty();
        assertThat(StreamOperationsDemo.sumViaReduceWithIdentity(List.of(1, 2, 3))).isEqualTo(6);
        assertThat(StreamOperationsDemo.sumViaReduceWithIdentity(List.of())).isZero();
    }

    @Test void primitiveStreams() {
        assertThat(StreamOperationsDemo.sumAsInt(List.of(1, 2, 3, 4))).isEqualTo(10);
        assertThat(StreamOperationsDemo.averageAsDouble(List.of(1, 2, 3, 4))).contains(2.5);
        assertThat(StreamOperationsDemo.averageAsDouble(List.of())).isEmpty();
    }

    @Test void findFirstAndMatches() {
        assertThat(StreamOperationsDemo.firstPositive(List.of(-1, -2, 3, 4))).contains(3);
        assertThat(StreamOperationsDemo.firstPositive(List.of(-1, -2))).isEmpty();
        assertThat(StreamOperationsDemo.anyEven(List.of(1, 3, 4))).isTrue();
        assertThat(StreamOperationsDemo.allPositive(List.of(1, 2, 3))).isTrue();
        assertThat(StreamOperationsDemo.noneNegative(List.of(1, 2, 3))).isTrue();
    }

    @Test void sumOfCharCodes() {
        // "a" == 97, "b" == 98, "c" == 99
        assertThat(StreamOperationsDemo.sumOfCharCodes(new String[]{"ab", "c"})).isEqualTo(97 + 98 + 99);
    }

    @Test void boxedRange() {
        assertThat(StreamOperationsDemo.boxedRange()).containsExactly(1, 2, 3);
    }
}