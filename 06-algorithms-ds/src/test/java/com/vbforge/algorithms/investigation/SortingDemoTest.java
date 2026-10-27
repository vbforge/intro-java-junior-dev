package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SortingDemoTest {

    @Test void bubbleSortBasic() {
        assertThat(SortingDemo.bubbleSort(new int[]{3, 1, 2})).containsExactly(1, 2, 3);
    }

    @Test void bubbleSortHandlesEdgeCases() {
        assertThat(SortingDemo.bubbleSort(new int[]{})).isEmpty();
        assertThat(SortingDemo.bubbleSort(new int[]{5})).containsExactly(5);
        assertThat(SortingDemo.bubbleSort(new int[]{1, 1, 2, 1})).containsExactly(1, 1, 1, 2);
        assertThat(SortingDemo.bubbleSort(new int[]{5, 4, 3, 2, 1})).containsExactly(1, 2, 3, 4, 5);
    }

    @Test void insertionSortBasic() {
        assertThat(SortingDemo.insertionSort(new int[]{3, 1, 2})).containsExactly(1, 2, 3);
        assertThat(SortingDemo.insertionSort(new int[]{})).isEmpty();
        assertThat(SortingDemo.insertionSort(new int[]{5, 4, 3, 2, 1})).containsExactly(1, 2, 3, 4, 5);
    }

    @Test void mergeSortBasic() {
        assertThat(SortingDemo.mergeSort(new int[]{3, 1, 2})).containsExactly(1, 2, 3);
        assertThat(SortingDemo.mergeSort(new int[]{})).isEmpty();
        assertThat(SortingDemo.mergeSort(new int[]{5, 4, 3, 2, 1})).containsExactly(1, 2, 3, 4, 5);
        assertThat(SortingDemo.mergeSort(new int[]{1, 1, 2, 1})).containsExactly(1, 1, 1, 2);
    }

    @Test void allThreeSortsAgreeOnRandomInput() {
        int[] input = new int[100];
        Random r = new Random(42);
        for (int i = 0; i < input.length; i++) input[i] = r.nextInt(1000);

        int[] b = SortingDemo.bubbleSort(input);
        int[] i = SortingDemo.insertionSort(input);
        int[] m = SortingDemo.mergeSort(input);
        assertThat(b).isEqualTo(m);
        assertThat(i).isEqualTo(m);
    }

    @Test void inputsAreNotMutated() {
        int[] input = {3, 1, 2};
        SortingDemo.bubbleSort(input);
        SortingDemo.insertionSort(input);
        SortingDemo.mergeSort(input);
        assertThat(input).containsExactly(3, 1, 2);
    }
}