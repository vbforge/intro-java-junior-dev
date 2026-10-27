package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task06_RangeTest {

    @Test void validRange() {
        Task06_Range r = new Task06_Range(1, 5);
        assertThat(r.from()).isEqualTo(1);
        assertThat(r.to()).isEqualTo(5);
    }

    @Test void invalidRangeRejected() {
        assertThatThrownBy(() -> new Task06_Range(5, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1").hasMessageContaining("5");
    }

    @Test void equalBoundsAreValid() {
        assertThat(new Task06_Range(3, 3).length()).isEqualTo(1);
    }

    @Test void length() {
        assertThat(new Task06_Range(1, 5).length()).isEqualTo(5);
    }

    @Test void contains() {
        Task06_Range r = new Task06_Range(10, 20);
        assertThat(r.contains(10)).isTrue();
        assertThat(r.contains(15)).isTrue();
        assertThat(r.contains(20)).isTrue();
        assertThat(r.contains(9)).isFalse();
        assertThat(r.contains(21)).isFalse();
    }

    @Test void overlaps() {
        Task06_Range a = new Task06_Range(1, 5);
        Task06_Range b = new Task06_Range(3, 8);
        Task06_Range c = new Task06_Range(6, 9);
        assertThat(a.overlaps(b)).isTrue();
        assertThat(a.overlaps(c)).isFalse();
        // touching boundary (5 and 5) overlaps since inclusive
        assertThat(a.overlaps(new Task06_Range(5, 5))).isTrue();
    }

    @Test void intersect() {
        assertThat(new Task06_Range(1, 5).intersect(new Task06_Range(3, 8)))
                .isEqualTo(new Task06_Range(3, 5));
        assertThatThrownBy(() -> new Task06_Range(1, 2).intersect(new Task06_Range(5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("overlap");
    }

    @Test void recordEqualsIsAutomatic() {
        assertThat(new Task06_Range(1, 5)).isEqualTo(new Task06_Range(1, 5));
        assertThat(new Task06_Range(1, 5)).isNotEqualTo(new Task06_Range(1, 6));
    }
}