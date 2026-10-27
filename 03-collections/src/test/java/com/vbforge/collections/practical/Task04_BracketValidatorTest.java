package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task04_BracketValidatorTest {

    @Test void simpleValid() {
        assertThat(Task04_BracketValidator.isValid("()")).isTrue();
        assertThat(Task04_BracketValidator.isValid("()[]{}")).isTrue();
        assertThat(Task04_BracketValidator.isValid("([{}])")).isTrue();
        assertThat(Task04_BracketValidator.isValid("{[]}")).isTrue();
    }

    @Test void simpleInvalid() {
        assertThat(Task04_BracketValidator.isValid("(]")).isFalse();
        assertThat(Task04_BracketValidator.isValid("([)]")).isFalse();    // LIFO violated
        assertThat(Task04_BracketValidator.isValid("(((")).isFalse();
        assertThat(Task04_BracketValidator.isValid(")")).isFalse();
        assertThat(Task04_BracketValidator.isValid("]")).isFalse();
        assertThat(Task04_BracketValidator.isValid("())")).isFalse();
    }

    @Test void emptyAndNonBrackets() {
        assertThat(Task04_BracketValidator.isValid("")).isTrue();
        assertThat(Task04_BracketValidator.isValid(null)).isTrue();
        assertThat(Task04_BracketValidator.isValid("abc")).isTrue();
        assertThat(Task04_BracketValidator.isValid("a(b)c")).isTrue();
        assertThat(Task04_BracketValidator.isValid("{[hello] world}")).isTrue();
    }

    @Test void mixed() {
        assertThat(Task04_BracketValidator.isValid("a(b[c]d)e")).isTrue();
        assertThat(Task04_BracketValidator.isValid("a(b[c)d]e")).isFalse();
    }
}