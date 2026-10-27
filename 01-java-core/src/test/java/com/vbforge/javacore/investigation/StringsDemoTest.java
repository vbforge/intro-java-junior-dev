package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringsDemoTest {

    @Test void uppercaseReturnsNewInstance() {
        assertThat(StringsDemo.uppercaseReturnsNewString()).isEqualTo("HELLO");
    }

    @Test void literalsArePooled() {
        assertThat(StringsDemo.literalsAreSameReference()).isTrue();
    }

    @Test void newStringBypassesPool() {
        assertThat(StringsDemo.newStringIsNotPooled()).isFalse();
    }

    @Test void commonMethods() {
        assertThat(StringsDemo.lengthOf("hello")).isEqualTo(5);
        assertThat(StringsDemo.charAt("hello", 1)).isEqualTo('e');
        assertThat(StringsDemo.substring("hello", 1, 4)).isEqualTo("ell");
        assertThat(StringsDemo.contains("hello", "ell")).isTrue();
        assertThat(StringsDemo.trimmed("  hi  ")).isEqualTo("hi");
        assertThat(StringsDemo.splitOnComma("a,b,c")).containsExactly("a", "b", "c");
        assertThat(StringsDemo.joined(new String[]{"a", "b", "c"})).isEqualTo("a,b,c");
    }

    @Test void inefficientAndEfficientRepeatAgree() {
        assertThat(StringsDemo.repeatInefficient("ab", 3))
                .isEqualTo(StringsDemo.repeatEfficient("ab", 3))
                .isEqualTo("ababab");
    }

    @Test void stringBuilderReverses() {
        assertThat(StringsDemo.reverseWithBuilder("hello")).isEqualTo("olleh");
    }

    @Test void textBlockStripsCommonIndent() {
        assertThat(StringsDemo.textBlock())
                .isEqualTo("line one\nline two\nline three");
    }
}