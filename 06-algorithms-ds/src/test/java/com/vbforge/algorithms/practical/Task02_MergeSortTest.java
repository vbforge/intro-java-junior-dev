package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task02_MergeSortTest {

    @Test void simple() {
        assertThat(Task02_MergeSort.mergeSort(new int[]{3, 1, 2})).containsExactly(1, 2, 3);
    }

    @Test void edgeCases() {
        assertThat(Task02_MergeSort.mergeSort(new int[]{})).isEmpty();
        assertThat(Task02_MergeSort.mergeSort(new int[]{42})).containsExactly(42);
        assertThat(Task02_MergeSort.mergeSort(new int[]{1, 1, 1})).containsExactly(1, 1, 1);
    }

    @Test void worstCase() {
        assertThat(Task02_MergeSort.mergeSort(new int[]{5, 4, 3, 2, 1}))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test void randomLarge() {
        int[] input = new int[500];
        Random r = new Random(42);
        for (int i = 0; i < input.length; i++) input[i] = r.nextInt(10_000);
        int[] sorted = Task02_MergeSort.mergeSort(input);
        for (int i = 1; i < sorted.length; i++) {
            assertThat(sorted[i - 1]).isLessThanOrEqualTo(sorted[i]);
        }
        assertThat(sorted).hasSize(input.length);
    }

    @Test void inputNotMutated() {
        int[] input = {3, 1, 2};
        Task02_MergeSort.mergeSort(input);
        assertThat(input).containsExactly(3, 1, 2);
    }
}