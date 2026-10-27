package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task02_FilterMapTest {

    @Test void evenSquares() {
        assertThat(Task02_FilterMap.evenSquares(List.of(1, 2, 3, 4, 5, 6)))
                .containsExactly(4, 16, 36);
        assertThat(Task02_FilterMap.evenSquares(List.of(1, 3, 5))).isEmpty();
    }

    @Test void nonBlankUpper() {
        assertThat(Task02_FilterMap.nonBlankUpper(List.of("a", " ", "bb", "ccc")))
                .containsExactly("A", "BB", "CCC");
    }

    @Test void nonBlankUpperHandlesNullElements() {
        List<String> input = new java.util.ArrayList<>();
        input.add("a");
        input.add(null);
        input.add("bb");
        assertThat(Task02_FilterMap.nonBlankUpper(input)).containsExactly("A", "BB");
    }

    @Test void distinctSorted() {
        assertThat(Task02_FilterMap.distinctSorted(List.of(3, 1, 2, 1, 3)))
                .containsExactly(1, 2, 3);
    }

    @Test void firstNames() {
        assertThat(Task02_FilterMap.firstNames(List.of("Alice Smith", "Bob Jones", "Charlie")))
                .containsExactly("Alice", "Bob", "Charlie");
    }

    @Test void inputNotMutated() {
        List<Integer> input = List.of(1, 2, 3);
        Task02_FilterMap.evenSquares(input);
        assertThat(input).containsExactly(1, 2, 3);
    }
}