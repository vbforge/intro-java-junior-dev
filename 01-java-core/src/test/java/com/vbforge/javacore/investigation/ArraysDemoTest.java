package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArraysDemoTest {

    @Test void newArrayIsZeroInitialized() {
        assertThat(ArraysDemo.zeroInitialized()).containsExactly(0, 0, 0, 0, 0);
    }

    @Test void copyPadsWithZero() {
        assertThat(ArraysDemo.copyPadded(new int[]{1, 2, 3}, 5)).containsExactly(1, 2, 3, 0, 0);
    }

    @Test void copyTruncatesIfShorter() {
        assertThat(ArraysDemo.copyPadded(new int[]{1, 2, 3}, 2)).containsExactly(1, 2);
    }

    @Test void fillSetsAllPositions() {
        assertThat(ArraysDemo.filled(4, 7)).containsExactly(7, 7, 7, 7);
    }

    @Test void sortAscendingDoesNotMutateInput() {
        int[] input = {3, 1, 2};
        int[] sorted = ArraysDemo.sortedAscending(input);
        assertThat(sorted).containsExactly(1, 2, 3);
        assertThat(input).containsExactly(3, 1, 2);
    }

    @Test void sortDescending() {
        assertThat(ArraysDemo.sortedDescending(new Integer[]{3, 1, 2})).containsExactly(3, 2, 1);
    }

    @Test void contentEqualsComparesValues() {
        assertThat(ArraysDemo.contentEquals(new int[]{1, 2}, new int[]{1, 2})).isTrue();
        assertThat(ArraysDemo.contentEquals(new int[]{1, 2}, new int[]{1, 3})).isFalse();
    }

    @Test void binarySearchFindsExisting() {
        int[] sorted = {1, 3, 5, 7, 9};
        assertThat(ArraysDemo.binarySearch(sorted, 5)).isEqualTo(2);
    }

    @Test void binarySearchReturnsNegativeForMissing() {
        int[] sorted = {1, 3, 5, 7, 9};
        assertThat(ArraysDemo.binarySearch(sorted, 4)).isNegative();
    }

    @Test void prettyPrintsReadably() {
        assertThat(ArraysDemo.pretty(new int[]{1, 2, 3})).isEqualTo("[1, 2, 3]");
    }
}