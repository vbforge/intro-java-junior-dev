package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task01_LambdaBasicsTest {

    @Test void parseToInt() {
        assertThat(Task01_LambdaBasics.parseToInt().apply("42")).isEqualTo(42);
        assertThatThrownBy(() -> Task01_LambdaBasics.parseToInt().apply("abc"))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test void isNotEmpty() {
        assertThat(Task01_LambdaBasics.isNotEmpty().test("a")).isTrue();
        assertThat(Task01_LambdaBasics.isNotEmpty().test(" ")).isTrue();
        assertThat(Task01_LambdaBasics.isNotEmpty().test("")).isFalse();
        assertThat(Task01_LambdaBasics.isNotEmpty().test(null)).isFalse();
    }

    @Test void newStringList() {
        List<String> a = Task01_LambdaBasics.newStringList().get();
        List<String> b = Task01_LambdaBasics.newStringList().get();
        assertThat(a).isNotNull().isInstanceOf(ArrayList.class);
        assertThat(a).isNotNull().isEmpty();
        assertThat(a).isNotSameAs(b);
        a.add("x");
        assertThat(b).isEmpty();
    }

    @Test void appendBang() {
        StringBuilder sb = new StringBuilder("hi");
        Task01_LambdaBasics.appendBang().accept(sb);
        assertThat(sb.toString()).isEqualTo("hi!");
    }

    @Test void maxOfTwo() {
        assertThat(Task01_LambdaBasics.maxOfTwo().apply(3, 5)).isEqualTo(5);
        assertThat(Task01_LambdaBasics.maxOfTwo().apply(5, 3)).isEqualTo(5);
        assertThat(Task01_LambdaBasics.maxOfTwo().apply(4, 4)).isEqualTo(4);
    }

    @Test void byLengthThenAlpha() {
        List<String> input = new ArrayList<>(List.of("ccc", "a", "bb", "aa", "b"));
        input.sort(Task01_LambdaBasics.byLengthThenAlpha());
        assertThat(input).containsExactly("a", "b", "aa", "bb", "ccc");
    }
}