package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchingDemoTest {

    @Test void linearSearchFindsFirstMatch() {
        assertThat(SearchingDemo.linearSearch(new int[]{5, 1, 3, 1, 2}, 1)).isEqualTo(1);
        assertThat(SearchingDemo.linearSearch(new int[]{5, 1, 3}, 9)).isEqualTo(-1);
        assertThat(SearchingDemo.linearSearch(new int[]{}, 5)).isEqualTo(-1);
    }

    @Test void binarySearchFindsTarget() {
        int[] sorted = {1, 3, 5, 7, 9};
        assertThat(SearchingDemo.binarySearch(sorted, 1)).isEqualTo(0);
        assertThat(SearchingDemo.binarySearch(sorted, 5)).isEqualTo(2);
        assertThat(SearchingDemo.binarySearch(sorted, 9)).isEqualTo(4);
        assertThat(SearchingDemo.binarySearch(sorted, 4)).isEqualTo(-1);
        assertThat(SearchingDemo.binarySearch(new int[]{}, 5)).isEqualTo(-1);
    }

    @Test void binarySearchEmptyAndSingleton() {
        assertThat(SearchingDemo.binarySearch(new int[]{42}, 42)).isZero();
        assertThat(SearchingDemo.binarySearch(new int[]{42}, 1)).isEqualTo(-1);
    }

    @Test void findFirstOccurrence() {
        int[] sorted = {1, 2, 2, 2, 3, 4};
        assertThat(SearchingDemo.findFirstOccurrence(sorted, 2)).isEqualTo(1);
        assertThat(SearchingDemo.findFirstOccurrence(sorted, 3)).isEqualTo(4);
        assertThat(SearchingDemo.findFirstOccurrence(sorted, 1)).isEqualTo(0);
        assertThat(SearchingDemo.findFirstOccurrence(sorted, 4)).isEqualTo(5);
        assertThat(SearchingDemo.findFirstOccurrence(sorted, 99)).isEqualTo(-1);
    }
}