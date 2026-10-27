package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OptionalDemoTest {

    @Test void creation() {
        assertThat(OptionalDemo.nonEmpty()).contains("hello");
        assertThat(OptionalDemo.ofNullableNull()).isEmpty();
        assertThat(OptionalDemo.empty()).isEmpty();
    }

    @Test void ofRejectsNull() {
        assertThat(OptionalDemo.ofRejectsNull()).isTrue();
    }

    @Test void orElseIsEager() {
        assertThat(OptionalDemo.orElseIsEager()).isEqualTo(1);
    }

    @Test void orElseGetIsLazy() {
        assertThat(OptionalDemo.orElseGetIsLazy()).isZero();
    }

    @Test void mapAndFlatMap() {
        assertThat(OptionalDemo.length("hello")).contains(5);
        assertThat(OptionalDemo.length(null)).isEmpty();
        assertThat(OptionalDemo.parseAndDouble("21")).contains(42);
        assertThat(OptionalDemo.parseAndDouble("abc")).isEmpty();
        assertThat(OptionalDemo.parseAndDouble(null)).isEmpty();
    }

    @Test void ifPresentOrElse() {
        assertThat(OptionalDemo.describe(Optional.of("x"))).isEqualTo("value=x");
        assertThat(OptionalDemo.describe(Optional.empty())).isEqualTo("empty");
    }

    @Test void orElseThrow() {
        assertThat(OptionalDemo.orElseThrow(Optional.of("ok"), IllegalStateException::new)).isEqualTo("ok");
        assertThatThrownBy(() -> OptionalDemo.orElseThrow(Optional.empty(), IllegalStateException::new))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test void getOnEmptyThrows() {
        assertThat(OptionalDemo.getOnEmptyThrows()).isTrue();
    }
}