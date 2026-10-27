package com.vbforge.javacore.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task01_FizzBuzzTest {

    @Test void divisibleBy15IsFizzBuzz() {
        assertThat(Task01_FizzBuzz.fizzBuzz(15)).isEqualTo("FizzBuzz");
        assertThat(Task01_FizzBuzz.fizzBuzz(0)).isEqualTo("FizzBuzz");       // 0 % 15 == 0
        assertThat(Task01_FizzBuzz.fizzBuzz(-30)).isEqualTo("FizzBuzz");
    }

    @Test void divisibleBy3OnlyIsFizz() {
        assertThat(Task01_FizzBuzz.fizzBuzz(3)).isEqualTo("Fizz");
        assertThat(Task01_FizzBuzz.fizzBuzz(9)).isEqualTo("Fizz");
        assertThat(Task01_FizzBuzz.fizzBuzz(-9)).isEqualTo("Fizz");
    }

    @Test void divisibleBy5OnlyIsBuzz() {
        assertThat(Task01_FizzBuzz.fizzBuzz(5)).isEqualTo("Buzz");
        assertThat(Task01_FizzBuzz.fizzBuzz(10)).isEqualTo("Buzz");
    }

    @Test void otherIsNumberAsString() {
        assertThat(Task01_FizzBuzz.fizzBuzz(1)).isEqualTo("1");
        assertThat(Task01_FizzBuzz.fizzBuzz(7)).isEqualTo("7");
        assertThat(Task01_FizzBuzz.fizzBuzz(-1)).isEqualTo("-1");
    }
}