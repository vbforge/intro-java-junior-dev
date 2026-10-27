package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StreamLifecycleDemoTest {

    @Test void noWorkUntilTerminal() {
        assertThat(StreamLifecycleDemo.noWorkUntilTerminal()).isTrue();
    }

    @Test void reuseThrows() {
        assertThat(StreamLifecycleDemo.reuseThrows()).isTrue();
    }

    @Test void anyMatchStopsEarly() {
        assertThat(StreamLifecycleDemo.anyMatchStopsEarly()).isTrue();
    }

    @Test void limitOnInfinite() {
        assertThat(StreamLifecycleDemo.limitOnInfinite()).containsExactly(1, 2, 3);
    }

    @Test void peekLogsAllElementsAfterFilter() {
        assertThat(StreamLifecycleDemo.peekLogsAllElements()).containsExactly(2, 4);
    }

    @Test void twoFreshStreamsAreIndependent() {
        assertThat(StreamLifecycleDemo.consumeTwoFreshStreams()).isEqualTo(12);
    }
}