package com.vbforge.javacore.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task05_SafeDividerTest {

    @Test void dividesNormally() {
        assertThat(Task05_SafeDivider.divide(10, 2)).isEqualTo(5);
        assertThat(Task05_SafeDivider.divide(7, 2)).isEqualTo(3);       // integer division
        assertThat(Task05_SafeDivider.divide(-9, 3)).isEqualTo(-3);
    }

    @Test void throwsCustomExceptionOnZero() {
        assertThatThrownBy(() -> Task05_SafeDivider.divide(10, 0))
                .isInstanceOf(DivisionByZeroException.class)
                .hasMessageContaining("zero");
    }
}