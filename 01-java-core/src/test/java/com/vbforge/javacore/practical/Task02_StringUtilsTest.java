package com.vbforge.javacore.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task02_StringUtilsTest {

    @Test void reverse() {
        assertThat(Task02_StringUtils.reverse("hello")).isEqualTo("olleh");
        assertThat(Task02_StringUtils.reverse("")).isEqualTo("");
        assertThat(Task02_StringUtils.reverse("a")).isEqualTo("a");
    }

    @Test void isPalindrome() {
        assertThat(Task02_StringUtils.isPalindrome("racecar")).isTrue();
        assertThat(Task02_StringUtils.isPalindrome("A man a plan a canal Panama")).isTrue();
        assertThat(Task02_StringUtils.isPalindrome("hello")).isFalse();
        assertThat(Task02_StringUtils.isPalindrome("")).isTrue();
    }

    @Test void countVowels() {
        assertThat(Task02_StringUtils.countVowels("hello")).isEqualTo(2);
        assertThat(Task02_StringUtils.countVowels("HELLO")).isEqualTo(2);
        assertThat(Task02_StringUtils.countVowels("AEIOU")).isEqualTo(5);
        assertThat(Task02_StringUtils.countVowels("")).isZero();
        assertThat(Task02_StringUtils.countVowels("xyz")).isZero();
    }

    @Test void capitalizeWords() {
        assertThat(Task02_StringUtils.capitalizeWords("hello world")).isEqualTo("Hello World");
        assertThat(Task02_StringUtils.capitalizeWords("jAVA rocks")).isEqualTo("Java Rocks");
        assertThat(Task02_StringUtils.capitalizeWords("")).isEqualTo("");
    }
}