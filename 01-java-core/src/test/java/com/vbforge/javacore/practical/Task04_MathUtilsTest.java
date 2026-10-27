package com.vbforge.javacore.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task04_MathUtilsTest {

    @Test void factorial() {
        assertThat(Task04_MathUtils.factorial(0)).isEqualTo(1);
        assertThat(Task04_MathUtils.factorial(1)).isEqualTo(1);
        assertThat(Task04_MathUtils.factorial(5)).isEqualTo(120);
        assertThat(Task04_MathUtils.factorial(10)).isEqualTo(3_628_800);
    }

    @Test void factorialRejectsNegative() {
        assertThatThrownBy(() -> Task04_MathUtils.factorial(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void isPrime() {
        assertThat(Task04_MathUtils.isPrime(0)).isFalse();
        assertThat(Task04_MathUtils.isPrime(1)).isFalse();
        assertThat(Task04_MathUtils.isPrime(2)).isTrue();
        assertThat(Task04_MathUtils.isPrime(17)).isTrue();
        assertThat(Task04_MathUtils.isPrime(15)).isFalse();
        assertThat(Task04_MathUtils.isPrime(-7)).isFalse();
    }

    @Test void gcd() {
        assertThat(Task04_MathUtils.gcd(12, 18)).isEqualTo(6);
        assertThat(Task04_MathUtils.gcd(0, 5)).isEqualTo(5);
        assertThat(Task04_MathUtils.gcd(5, 0)).isEqualTo(5);
        assertThat(Task04_MathUtils.gcd(17, 13)).isEqualTo(1);
        assertThat(Task04_MathUtils.gcd(-12, 18)).isEqualTo(6);
    }
}