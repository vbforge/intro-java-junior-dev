package com.vbforge.javacore.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task03_ArrayUtilsTest {

    @Test void sumHandlesEmptyAndRegular() {
        assertThat(Task03_ArrayUtils.sum(new int[]{})).isZero();
        assertThat(Task03_ArrayUtils.sum(new int[]{1, 2, 3})).isEqualTo(6);
        assertThat(Task03_ArrayUtils.sum(new int[]{-1, 1})).isZero();
    }

    @Test void averageComputedAndThrowsOnEmpty() {
        assertThat(Task03_ArrayUtils.average(new int[]{1, 2, 3})).isEqualTo(2.0);
        assertThatThrownBy(() -> Task03_ArrayUtils.average(new int[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void maxComputedAndThrowsOnEmpty() {
        assertThat(Task03_ArrayUtils.max(new int[]{3, 1, 2})).isEqualTo(3);
        assertThatThrownBy(() -> Task03_ArrayUtils.max(new int[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void reverseInPlace() {
        int[] arr = {1, 2, 3, 4, 5};
        Task03_ArrayUtils.reverseInPlace(arr);
        assertThat(arr).containsExactly(5, 4, 3, 2, 1);
    }

    @Test void reverseInPlaceHandlesEvenLength() {
        int[] arr = {1, 2, 3, 4};
        Task03_ArrayUtils.reverseInPlace(arr);
        assertThat(arr).containsExactly(4, 3, 2, 1);
    }

    @Test void secondLargest() {
        assertThat(Task03_ArrayUtils.secondLargest(new int[]{3, 1, 2})).isEqualTo(2);
        assertThat(Task03_ArrayUtils.secondLargest(new int[]{5, 5, 3})).isEqualTo(3);
        assertThatThrownBy(() -> Task03_ArrayUtils.secondLargest(new int[]{5}))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task03_ArrayUtils.secondLargest(new int[]{5, 5}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}