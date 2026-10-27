package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionsDemoTest {

    @Test void checkedExceptionIsThrown() {
        assertThatThrownBy(ExceptionsDemo::declaresCheckedException)
                .isInstanceOf(IOException.class)
                .hasMessage("simulated");
    }

    @Test void uncheckedExceptionIsThrown() {
        assertThatThrownBy(ExceptionsDemo::throwsUnchecked)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test void multiCatchHandlesBoth() {
        assertThat(ExceptionsDemo.multiCatch(true)).isEqualTo("caught: IOException");
        assertThat(ExceptionsDemo.multiCatch(false)).isEqualTo("caught: InterruptedException");
    }

    @Test void finallyRunsButCannotOverrideReturnValue() {
        // The method returns "try;returned" because that's what try returns BEFORE
        // the finally block runs. The finally still runs (a side effect on the SB),
        // but by then the return value has already been captured.
        assertThat(ExceptionsDemo.finallyRunsOnReturn()).isEqualTo("try;returned");
    }

    @Test void wrappingKeepsCause() {
        assertThatThrownBy(ExceptionsDemo::throwWithCause)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("wrapped")
                .hasCauseInstanceOf(IOException.class);
    }

    @Test void customExceptionUsedForValidation() {
        assertThat(ExceptionsDemo.requirePositive(3)).isEqualTo(3);
        assertThatThrownBy(() -> ExceptionsDemo.requirePositive(-1))
                .isInstanceOf(ExceptionsDemo.BadInputException.class)
                .hasMessageContaining("must be > 0");
    }
}