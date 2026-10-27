package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaBasicsDemoTest {

    @Test void increment() {
        assertThat(LambdaBasicsDemo.increment().apply(41)).isEqualTo(42);
    }

    @Test void isEmpty() {
        assertThat(LambdaBasicsDemo.isEmpty().test("")).isTrue();
        assertThat(LambdaBasicsDemo.isEmpty().test("x")).isFalse();
    }

    @Test void greeting() {
        assertThat(LambdaBasicsDemo.greeting().get()).isEqualTo("hello");
    }

    @Test void add() {
        assertThat(LambdaBasicsDemo.add().applyAsInt(2, 3)).isEqualTo(5);
    }

    @Test void prefixedWithCapturesEffectivelyFinal() {
        Function<String, String> f = LambdaBasicsDemo.prefixedWith(">> ");
        assertThat(f.apply("hello")).isEqualTo(">> hello");
    }

    @Test void captureDemo() {
        assertThat(LambdaBasicsDemo.captureDemo()).isEqualTo("Hi there");
    }

    @Test void methodReferenceKinds() {
        assertThat(LambdaBasicsDemo.staticRef().apply("42")).isEqualTo(42);
        assertThat(LambdaBasicsDemo.boundInstanceRef().get()).isEqualTo("HELLO");
        assertThat(LambdaBasicsDemo.unboundInstanceRef().apply("abc")).isEqualTo("ABC");
        assertThat(LambdaBasicsDemo.constructorRef().apply("hi").toString()).isEqualTo("hi");
    }

    @Test void safeCompareNeverOverflows() {
        assertThat(LambdaBasicsDemo.safeCompare(1, 2)).isNegative();
        assertThat(LambdaBasicsDemo.safeCompare(2, 1)).isPositive();
        assertThat(LambdaBasicsDemo.safeCompare(5, 5)).isZero();
    }
}