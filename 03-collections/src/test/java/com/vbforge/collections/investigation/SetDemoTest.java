package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SetDemoTest {

    @Test void hashSetDropsDuplicates() {
        assertThat(SetDemo.hashSetSize()).isEqualTo(3);
    }

    @Test void linkedHashSetKeepsInsertionOrder() {
        assertThat(SetDemo.linkedHashSetOrder()).containsExactly(3, 1, 2);
    }

    @Test void treeSetSortsAscending() {
        assertThat(SetDemo.treeSetOrder()).containsExactly(1, 2, 3);
    }

    @Test void treeSetWithReverseComparator() {
        assertThat(SetDemo.treeSetDescending()).containsExactly(3, 2, 1);
    }

    @Test void setMath() {
        Set<Integer> a = Set.of(1, 2, 3);
        Set<Integer> b = Set.of(3, 4, 5);
        assertThat(SetDemo.union(a, b)).containsExactlyInAnyOrder(1, 2, 3, 4, 5);
        assertThat(SetDemo.intersection(a, b)).containsExactly(3);
        assertThat(SetDemo.difference(a, b)).containsExactly(1, 2);
    }

    @Test void subsetCheck() {
        assertThat(SetDemo.isSubset(Set.of(1, 2), Set.of(1, 2, 3))).isTrue();
        assertThat(SetDemo.isSubset(Set.of(1, 4), Set.of(1, 2, 3))).isFalse();
    }
}