package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task02_ListOperationsTest {

    @Test void onlyUnique() {
        assertThat(Task02_ListOperations.onlyUnique(List.of(1, 2, 2, 3, 4, 4, 5)))
                .containsExactly(1, 3, 5);
        assertThat(Task02_ListOperations.onlyUnique(List.of())).isEmpty();
        assertThat(Task02_ListOperations.onlyUnique(List.of(1, 1, 1))).isEmpty();
        assertThat(Task02_ListOperations.onlyUnique(null)).isEmpty();
    }

    @Test void onlyUniqueDoesNotMutateInput() {
        List<Integer> in = List.of(1, 2, 2, 3);
        Task02_ListOperations.onlyUnique(in);
        assertThat(in).containsExactly(1, 2, 2, 3);
    }

    @Test void rotateLeft() {
        assertThat(Task02_ListOperations.rotateLeft(List.of(1, 2, 3, 4, 5), 2))
                .containsExactly(3, 4, 5, 1, 2);
        assertThat(Task02_ListOperations.rotateLeft(List.of(1, 2, 3, 4, 5), 0))
                .containsExactly(1, 2, 3, 4, 5);
        assertThat(Task02_ListOperations.rotateLeft(List.of(1, 2, 3, 4, 5), 7))
                .containsExactly(3, 4, 5, 1, 2);       // 7 mod 5 == 2
        assertThat(Task02_ListOperations.rotateLeft(List.of(), 3)).isEmpty();
    }

    @Test void mergeSorted() {
        assertThat(Task02_ListOperations.mergeSorted(
                List.of(1, 3, 5), List.of(2, 4, 6)))
                .containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(Task02_ListOperations.mergeSorted(
                List.of(1, 1, 3), List.of(1, 2, 2)))
                .containsExactly(1, 1, 1, 2, 2, 3);
        assertThat(Task02_ListOperations.mergeSorted(List.of(), List.of(1, 2)))
                .containsExactly(1, 2);
        assertThat(Task02_ListOperations.mergeSorted(null, null)).isEmpty();
    }
}