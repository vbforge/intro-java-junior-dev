package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task06_RecursionTest {

    @Test void power() {
        assertThat(Task06_Recursion.power(2, 0)).isEqualTo(1L);
        assertThat(Task06_Recursion.power(2, 10)).isEqualTo(1024L);
        assertThat(Task06_Recursion.power(5, 1)).isEqualTo(5L);
        assertThat(Task06_Recursion.power(3, 4)).isEqualTo(81L);
        assertThatThrownBy(() -> Task06_Recursion.power(2, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void sumDigits() {
        assertThat(Task06_Recursion.sumDigits(0)).isZero();
        assertThat(Task06_Recursion.sumDigits(9)).isEqualTo(9);
        assertThat(Task06_Recursion.sumDigits(123)).isEqualTo(6);
        assertThat(Task06_Recursion.sumDigits(9999)).isEqualTo(36);
    }

    @Test void reverseString() {
        assertThat(Task06_Recursion.reverse("")).isEmpty();
        assertThat(Task06_Recursion.reverse("a")).isEqualTo("a");
        assertThat(Task06_Recursion.reverse("hello")).isEqualTo("olleh");
        assertThat(Task06_Recursion.reverse(null)).isEmpty();
    }

    @Test void isPalindrome() {
        assertThat(Task06_Recursion.isPalindrome("")).isTrue();
        assertThat(Task06_Recursion.isPalindrome("a")).isTrue();
        assertThat(Task06_Recursion.isPalindrome("racecar")).isTrue();
        assertThat(Task06_Recursion.isPalindrome("A man a plan a canal Panama")).isTrue();
        assertThat(Task06_Recursion.isPalindrome("hello")).isFalse();
        assertThat(Task06_Recursion.isPalindrome("ab")).isFalse();
    }

    @Test void climbWays() {
        assertThat(Task06_Recursion.climbWays(0)).isEqualTo(1L);
        assertThat(Task06_Recursion.climbWays(1)).isEqualTo(1L);
        assertThat(Task06_Recursion.climbWays(2)).isEqualTo(2L);
        assertThat(Task06_Recursion.climbWays(3)).isEqualTo(3L);
        assertThat(Task06_Recursion.climbWays(10)).isEqualTo(89L);
        assertThatThrownBy(() -> Task06_Recursion.climbWays(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}