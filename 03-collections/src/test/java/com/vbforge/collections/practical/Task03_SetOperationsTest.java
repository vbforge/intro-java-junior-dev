package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task03_SetOperationsTest {

    @Test void union() {
        assertThat(Task03_SetOperations.union(Set.of(1, 2), Set.of(3, 4)))
                .containsExactlyInAnyOrder(1, 2, 3, 4);
        assertThat(Task03_SetOperations.union(null, Set.of(1))).containsExactly(1);
        assertThat(Task03_SetOperations.union(null, null)).isEmpty();
    }

    @Test void intersection() {
        assertThat(Task03_SetOperations.intersection(Set.of(1, 2, 3), Set.of(2, 3, 4)))
                .containsExactlyInAnyOrder(2, 3);
        assertThat(Task03_SetOperations.intersection(null, Set.of(1))).isEmpty();
    }

    @Test void difference() {
        assertThat(Task03_SetOperations.difference(Set.of(1, 2, 3), Set.of(2)))
                .containsExactlyInAnyOrder(1, 3);
        assertThat(Task03_SetOperations.difference(Set.of(1), null)).containsExactly(1);
    }

    @Test void symmetricDifference() {
        assertThat(Task03_SetOperations.symmetricDifference(Set.of(1, 2, 3), Set.of(3, 4, 5)))
                .containsExactlyInAnyOrder(1, 2, 4, 5);
    }

    @Test void isSubset() {
        assertThat(Task03_SetOperations.isSubset(Set.of(1, 2), Set.of(1, 2, 3))).isTrue();
        assertThat(Task03_SetOperations.isSubset(Set.of(1, 4), Set.of(1, 2, 3))).isFalse();
        assertThat(Task03_SetOperations.isSubset(Set.of(), Set.of(1))).isTrue();   // empty is a subset
    }

    @Test void inputsAreNotMutated() {
        Set<Integer> a = Set.of(1, 2);
        Set<Integer> b = Set.of(2, 3);
        Task03_SetOperations.union(a, b);
        Task03_SetOperations.intersection(a, b);
        assertThat(a).containsExactlyInAnyOrder(1, 2);
        assertThat(b).containsExactlyInAnyOrder(2, 3);
    }
}