package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task01_BubbleAndInsertionSortTest {

    @Test void bubbleSortSimple() {
        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(new int[]{3, 1, 2}))
                .containsExactly(1, 2, 3);
    }

    @Test void bubbleSortEdgeCases() {
        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(new int[]{}))
                .isEmpty();
        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(new int[]{42}))
                .containsExactly(42);
        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(new int[]{5, 5, 5}))
                .containsExactly(5, 5, 5);
    }

    @Test void bubbleSortWorstCase() {
        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(new int[]{5, 4, 3, 2, 1}))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test void insertionSortSimple() {
        assertThat(Task01_BubbleAndInsertionSort.insertionSort(new int[]{3, 1, 2}))
                .containsExactly(1, 2, 3);
    }

    @Test void insertionSortEdgeCases() {
        assertThat(Task01_BubbleAndInsertionSort.insertionSort(new int[]{}))
                .isEmpty();
        assertThat(Task01_BubbleAndInsertionSort.insertionSort(new int[]{42}))
                .containsExactly(42);
        assertThat(Task01_BubbleAndInsertionSort.insertionSort(new int[]{5, 4, 3, 2, 1}))
                .containsExactly(1, 2, 3, 4, 5);
    }

    @Test void bothSortsAgreeOnRandomInput() {
        int[] input = new int[100];
        Random r = new Random(42);
        for (int i = 0; i < input.length; i++) input[i] = r.nextInt(1000);

        assertThat(Task01_BubbleAndInsertionSort.bubbleSort(input))
                .isEqualTo(Task01_BubbleAndInsertionSort.insertionSort(input));
    }

    @Test void inputsAreNotMutated() {
        int[] input = {3, 1, 2};
        Task01_BubbleAndInsertionSort.bubbleSort(input);
        Task01_BubbleAndInsertionSort.insertionSort(input);
        assertThat(input).containsExactly(3, 1, 2);
    }
}