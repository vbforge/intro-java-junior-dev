package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task04_OptionalChainTest {

    @Test void parseIntOrEmpty() {
        assertThat(Task04_OptionalChain.parseIntOrEmpty("42")).contains(42);
        assertThat(Task04_OptionalChain.parseIntOrEmpty("abc")).isEmpty();
        assertThat(Task04_OptionalChain.parseIntOrEmpty("")).isEmpty();
        assertThat(Task04_OptionalChain.parseIntOrEmpty(null)).isEmpty();
    }

    @Test void parseIntOrDefault() {
        assertThat(Task04_OptionalChain.parseIntOrDefault("42", -1)).isEqualTo(42);
        assertThat(Task04_OptionalChain.parseIntOrDefault("abc", -1)).isEqualTo(-1);
        assertThat(Task04_OptionalChain.parseIntOrDefault(null, 0)).isZero();
    }

    @Test void doubleIfPositive() {
        assertThat(Task04_OptionalChain.doubleIfPositive("21")).contains(42);
        assertThat(Task04_OptionalChain.doubleIfPositive("0")).isEmpty();
        assertThat(Task04_OptionalChain.doubleIfPositive("-5")).isEmpty();
        assertThat(Task04_OptionalChain.doubleIfPositive("abc")).isEmpty();
    }

    @Test void firstEvenSquared() {
        assertThat(Task04_OptionalChain.firstEvenSquared(List.of(1, 3, 4, 5, 6))).contains(16);
        assertThat(Task04_OptionalChain.firstEvenSquared(List.of(1, 3, 5))).isEmpty();
        assertThat(Task04_OptionalChain.firstEvenSquared(List.of())).isEmpty();
    }

    @Test void trimmedOrEmpty() {
        assertThat(Task04_OptionalChain.trimmedOrEmpty("  hi  ")).contains("hi");
        assertThat(Task04_OptionalChain.trimmedOrEmpty("   ")).isEmpty();
        assertThat(Task04_OptionalChain.trimmedOrEmpty("")).isEmpty();
        assertThat(Task04_OptionalChain.trimmedOrEmpty(null)).isEmpty();
    }
}