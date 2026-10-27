package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalInterfacesDemoTest {

    @Test void length() {
        assertThat(FunctionalInterfacesDemo.length().apply("hello")).isEqualTo(5);
    }

    @Test void lengthTimesTwo() {
        assertThat(FunctionalInterfacesDemo.lengthTimesTwo().apply("abc")).isEqualTo(6);
    }

    @Test void composeAppliesPlusOneThenTimesTwo() {
        assertThat(FunctionalInterfacesDemo.composeAppliedTo5()).isEqualTo(12);
    }

    @Test void trim() {
        assertThat(FunctionalInterfacesDemo.trim().apply("  hi  ")).isEqualTo("hi");
    }

    @Test void sumAndMax() {
        assertThat(FunctionalInterfacesDemo.sum().apply(3, 4)).isEqualTo(7);
        assertThat(FunctionalInterfacesDemo.max().apply(3, 4)).isEqualTo(4);
    }

    @Test void predicateComposition() {
        assertThat(FunctionalInterfacesDemo.predicateComposition()).isTrue();
    }

    @Test void consumerAndSupplier() {
        StringBuilder sb = new StringBuilder();
        FunctionalInterfacesDemo.appender(sb).accept("hello");
        assertThat(sb.toString()).isEqualTo("hello");

        assertThat(FunctionalInterfacesDemo.newBuilder().get()).isNotNull();
    }

    @Test void intPredicate() {
        assertThat(FunctionalInterfacesDemo.positive().test(1)).isTrue();
        assertThat(FunctionalInterfacesDemo.positive().test(-1)).isFalse();
        assertThat(FunctionalInterfacesDemo.positive().test(0)).isFalse();
    }
}