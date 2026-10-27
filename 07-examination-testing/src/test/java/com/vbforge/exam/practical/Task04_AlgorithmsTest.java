package com.vbforge.exam.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task04_AlgorithmsTest {

    // ---------------------------------------------------------------------
    // quickSort
    // ---------------------------------------------------------------------

    @Test void quickSortBasic() {
        assertThat(Task04_Algorithms.quickSort(new int[]{3, 1, 2}))
                .containsExactly(1, 2, 3);
    }

    @Test void quickSortEdgeCases() {
        assertThat(Task04_Algorithms.quickSort(new int[]{})).isEmpty();
        assertThat(Task04_Algorithms.quickSort(new int[]{42})).containsExactly(42);
        assertThat(Task04_Algorithms.quickSort(new int[]{5, 5, 5})).containsExactly(5, 5, 5);
    }

    @Test void quickSortWorstCaseInput() {
        assertThat(Task04_Algorithms.quickSort(new int[]{5, 4, 3, 2, 1}))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test void quickSortHandlesAlreadySortedInput() {
        // a bad pivot choice would still give the right answer, just slowly — test only correctness
        assertThat(Task04_Algorithms.quickSort(new int[]{1, 2, 3, 4, 5}))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test void quickSortHandlesRandomInput() {
        int[] input = new int[200];
        Random r = new Random(42);
        for (int i = 0; i < input.length; i++) input[i] = r.nextInt(1000);

        int[] sorted = Task04_Algorithms.quickSort(input);
        for (int i = 1; i < sorted.length; i++) {
            assertThat(sorted[i - 1]).isLessThanOrEqualTo(sorted[i]);
        }
        assertThat(sorted).hasSize(input.length);
    }

    @Test void quickSortDoesNotMutateInput() {
        int[] input = {3, 1, 2};
        Task04_Algorithms.quickSort(input);
        assertThat(input).containsExactly(3, 1, 2);
    }

    // ---------------------------------------------------------------------
    // lowerBound
    // ---------------------------------------------------------------------

    @Test void lowerBoundOnDuplicates() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.lowerBound(arr, 2)).isEqualTo(1);
        assertThat(Task04_Algorithms.lowerBound(arr, 3)).isEqualTo(4);
    }

    @Test void lowerBoundEdgePositions() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.lowerBound(arr, 0)).isZero();
        assertThat(Task04_Algorithms.lowerBound(arr, 5)).isEqualTo(6);
        assertThat(Task04_Algorithms.lowerBound(arr, 1)).isZero();
        assertThat(Task04_Algorithms.lowerBound(arr, 4)).isEqualTo(5);
    }

    @Test void lowerBoundEmptyArray() {
        assertThat(Task04_Algorithms.lowerBound(new int[]{}, 5)).isZero();
    }

    // ---------------------------------------------------------------------
    // upperBound
    // ---------------------------------------------------------------------

    @Test void upperBoundOnDuplicates() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.upperBound(arr, 2)).isEqualTo(4);
        assertThat(Task04_Algorithms.upperBound(arr, 3)).isEqualTo(5);
    }

    @Test void upperBoundEdgePositions() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.upperBound(arr, 0)).isZero();
        assertThat(Task04_Algorithms.upperBound(arr, 4)).isEqualTo(6);
        assertThat(Task04_Algorithms.upperBound(arr, 5)).isEqualTo(6);
    }

    @Test void upperBoundEmptyArray() {
        assertThat(Task04_Algorithms.upperBound(new int[]{}, 5)).isZero();
    }

    // ---------------------------------------------------------------------
    // countOccurrences
    // ---------------------------------------------------------------------

    @Test void countOccurrencesFindsCount() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.countOccurrences(arr, 2)).isEqualTo(3);
        assertThat(Task04_Algorithms.countOccurrences(arr, 1)).isEqualTo(1);
        assertThat(Task04_Algorithms.countOccurrences(arr, 4)).isEqualTo(1);
    }

    @Test void countOccurrencesMissing() {
        int[] arr = {1, 2, 2, 2, 3, 4};
        assertThat(Task04_Algorithms.countOccurrences(arr, 99)).isZero();
        assertThat(Task04_Algorithms.countOccurrences(new int[]{}, 5)).isZero();
    }

    // ---------------------------------------------------------------------
    // removeDuplicatesSorted
    // ---------------------------------------------------------------------

    @Test void removeDuplicates() {
        assertThat(Task04_Algorithms.removeDuplicatesSorted(new int[]{1, 1, 2, 3, 3, 3, 4}))
                .containsExactly(1, 2, 3, 4);
    }

    @Test void removeDuplicatesEdgeCases() {
        assertThat(Task04_Algorithms.removeDuplicatesSorted(new int[]{})).isEmpty();
        assertThat(Task04_Algorithms.removeDuplicatesSorted(new int[]{5})).containsExactly(5);
        assertThat(Task04_Algorithms.removeDuplicatesSorted(new int[]{5, 5, 5})).containsExactly(5);
        assertThat(Task04_Algorithms.removeDuplicatesSorted(new int[]{1, 2, 3})).containsExactly(1, 2, 3);
    }

    @Test void removeDuplicatesDoesNotMutateInput() {
        int[] input = {1, 1, 2};
        Task04_Algorithms.removeDuplicatesSorted(input);
        assertThat(input).containsExactly(1, 1, 2);
    }
}