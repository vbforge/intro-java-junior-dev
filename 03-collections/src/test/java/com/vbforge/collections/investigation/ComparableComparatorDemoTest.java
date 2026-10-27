package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComparableComparatorDemoTest {

    @Test void naturalOrderById() {
        assertThat(ComparableComparatorDemo.naturalOrderIds()).containsExactly(1, 2, 3);
    }

    @Test void byName() {
        assertThat(ComparableComparatorDemo.sortedByName())
                .containsExactly("Alice", "Bob", "Charlie");
    }

    @Test void bySalaryDescending() {
        assertThat(ComparableComparatorDemo.sortedBySalaryDesc())
                .containsExactly("Alice", "Charlie", "Bob");
    }

    @Test void reversedNaturalOrder() {
        assertThat(ComparableComparatorDemo.reversedIds()).containsExactly(3, 2, 1);
    }
}