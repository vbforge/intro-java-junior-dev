package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task05_OperationTest {

//    @Test void symbols() {
//        assertThat(Task05_Operation.PLUS.symbol()).isEqualTo("+");
//        assertThat(Task05_Operation.MINUS.symbol()).isEqualTo("-");
//        assertThat(Task05_Operation.MULTIPLY.symbol()).isEqualTo("*");
//        assertThat(Task05_Operation.DIVIDE.symbol()).isEqualTo("/");
//    }
//
//    @Test void applyArithmetic() {
//        assertThat(Task05_Operation.PLUS.apply(3, 4)).isEqualTo(7);
//        assertThat(Task05_Operation.MINUS.apply(10, 4)).isEqualTo(6);
//        assertThat(Task05_Operation.MULTIPLY.apply(3, 4)).isEqualTo(12);
//        assertThat(Task05_Operation.DIVIDE.apply(10, 3)).isEqualTo(3);   // integer division
//    }
//
//    @Test void divideByZeroThrows() {
//        assertThatThrownBy(() -> Task05_Operation.DIVIDE.apply(1, 0))
//                .isInstanceOf(ArithmeticException.class)
//                .hasMessageContaining("Division by zero");
//    }
//
//    @Test void fromSymbol() {
//        assertThat(Task05_Operation.fromSymbol("+")).isEqualTo(Task05_Operation.PLUS);
//        assertThat(Task05_Operation.fromSymbol("/")).isEqualTo(Task05_Operation.DIVIDE);
//        assertThatThrownBy(() -> Task05_Operation.fromSymbol("!"))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("!");
//    }
}