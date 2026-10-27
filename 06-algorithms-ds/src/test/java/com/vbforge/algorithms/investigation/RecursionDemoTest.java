package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecursionDemoTest {

    @Test void factorial() {
        assertThat(RecursionDemo.factorial(0)).isEqualTo(1L);
        assertThat(RecursionDemo.factorial(1)).isEqualTo(1L);
        assertThat(RecursionDemo.factorial(5)).isEqualTo(120L);
        assertThat(RecursionDemo.factorial(10)).isEqualTo(3_628_800L);
        assertThatThrownBy(() -> RecursionDemo.factorial(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void sumDigits() {
        assertThat(RecursionDemo.sumDigits(0)).isZero();
        assertThat(RecursionDemo.sumDigits(9)).isEqualTo(9);
        assertThat(RecursionDemo.sumDigits(123)).isEqualTo(6);
        assertThat(RecursionDemo.sumDigits(9999)).isEqualTo(36);
    }

    @Test void gcd() {
        assertThat(RecursionDemo.gcd(12, 18)).isEqualTo(6);
        assertThat(RecursionDemo.gcd(17, 13)).isEqualTo(1);
        assertThat(RecursionDemo.gcd(0, 5)).isEqualTo(5);
        assertThat(RecursionDemo.gcd(-12, 18)).isEqualTo(6);
    }

    @Test void fibonacci() {
        assertThat(RecursionDemo.fibNaive(10)).isEqualTo(55L);
        assertThat(RecursionDemo.fibMemo(10)).isEqualTo(55L);
        assertThat(RecursionDemo.fibMemo(40)).isEqualTo(102_334_155L);
        assertThat(RecursionDemo.fibMemo(50)).isEqualTo(12_586_269_025L);
    }

    @Test void hanoiMoves() {
        assertThat(RecursionDemo.hanoiMoves(0)).isZero();
        assertThat(RecursionDemo.hanoiMoves(1)).isEqualTo(1L);
        assertThat(RecursionDemo.hanoiMoves(3)).isEqualTo(7L);
        assertThat(RecursionDemo.hanoiMoves(10)).isEqualTo(1023L);
    }

    @Test void permutations() {
        assertThat(RecursionDemo.permutations("ab")).containsExactlyInAnyOrder("ab", "ba");
        assertThat(RecursionDemo.permutations("abc"))
                .hasSize(6)
                .containsExactlyInAnyOrder("abc", "acb", "bac", "bca", "cab", "cba");
        assertThat(RecursionDemo.permutations("")).containsExactly("");
        assertThat(RecursionDemo.permutations(null)).isEmpty();
    }
}