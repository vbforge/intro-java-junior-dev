package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task03_FlatMapAndSortTest {

    @Test void flatten() {
        assertThat(Task03_FlatMapAndSort.flatten(
                List.of(List.of(1, 2), List.of(3), List.of(), List.of(4, 5))))
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(Task03_FlatMapAndSort.flatten(List.of())).isEmpty();
    }

    @Test void wordsFromLines() {
        assertThat(Task03_FlatMapAndSort.wordsFromLines(
                List.of("hello world", "java streams", "")))
                .containsExactly("hello", "world", "java", "streams");
    }

    @Test void sortByLengthThenAlpha() {
        assertThat(Task03_FlatMapAndSort.sortByLengthThenAlpha(
                List.of("ccc", "a", "bb", "aa", "b")))
                .containsExactly("a", "b", "aa", "bb", "ccc");
    }

    @Test void sortDescending() {
        assertThat(Task03_FlatMapAndSort.sortDescending(List.of(3, 1, 4, 1, 5)))
                .containsExactly(5, 4, 3, 1, 1);
    }

    @Test void sortByLengthDescending() {
        assertThat(Task03_FlatMapAndSort.sortByLengthDescending(List.of("a", "bbb", "cc")))
                .containsExactly("bbb", "cc", "a");
    }

    @Test void noInputsMutated() {
        List<Integer> nums = List.of(3, 1, 2);
        Task03_FlatMapAndSort.sortDescending(nums);
        assertThat(nums).containsExactly(3, 1, 2);
    }
}