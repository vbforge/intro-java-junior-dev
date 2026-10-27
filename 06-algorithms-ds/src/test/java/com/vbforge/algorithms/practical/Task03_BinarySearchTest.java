package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task03_BinarySearchTest {

    @Test void binarySearchFindsElements() {
        int[] arr = {1, 3, 5, 7, 9};
        assertThat(Task03_BinarySearch.binarySearch(arr, 1)).isEqualTo(0);
        assertThat(Task03_BinarySearch.binarySearch(arr, 5)).isEqualTo(2);
        assertThat(Task03_BinarySearch.binarySearch(arr, 9)).isEqualTo(4);
    }

    @Test void binarySearchMissing() {
        int[] arr = {1, 3, 5, 7, 9};
        assertThat(Task03_BinarySearch.binarySearch(arr, 4)).isEqualTo(-1);
        assertThat(Task03_BinarySearch.binarySearch(arr, 0)).isEqualTo(-1);
        assertThat(Task03_BinarySearch.binarySearch(arr, 10)).isEqualTo(-1);
    }

    @Test void binarySearchEdgeCases() {
        assertThat(Task03_BinarySearch.binarySearch(new int[]{}, 5)).isEqualTo(-1);
        assertThat(Task03_BinarySearch.binarySearch(new int[]{7}, 7)).isZero();
        assertThat(Task03_BinarySearch.binarySearch(new int[]{7}, 1)).isEqualTo(-1);
    }

    @Test void binarySearchDuplicatesReturnsAny() {
        int[] arr = {1, 2, 2, 2, 3};
        int idx = Task03_BinarySearch.binarySearch(arr, 2);
        assertThat(idx).isBetween(1, 3);
        assertThat(arr[idx]).isEqualTo(2);
    }

    @Test void findFirstOccurrence() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task03_BinarySearch.findFirstOccurrence(arr, 1)).isEqualTo(0);
        assertThat(Task03_BinarySearch.findFirstOccurrence(arr, 2)).isEqualTo(1);
        assertThat(Task03_BinarySearch.findFirstOccurrence(arr, 3)).isEqualTo(4);
        assertThat(Task03_BinarySearch.findFirstOccurrence(arr, 4)).isEqualTo(5);
        assertThat(Task03_BinarySearch.findFirstOccurrence(arr, 99)).isEqualTo(-1);
    }

    @Test void findFirstOccurrenceEdges() {
        assertThat(Task03_BinarySearch.findFirstOccurrence(new int[]{}, 5)).isEqualTo(-1);
        assertThat(Task03_BinarySearch.findFirstOccurrence(new int[]{7}, 7)).isZero();
        assertThat(Task03_BinarySearch.findFirstOccurrence(new int[]{2, 2, 2}, 2)).isZero();
    }
}