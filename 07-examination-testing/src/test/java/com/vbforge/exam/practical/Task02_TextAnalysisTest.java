package com.vbforge.exam.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task02_TextAnalysisTest {

    // ---------------------------------------------------------------------
    // areAnagrams
    // ---------------------------------------------------------------------

    @Test void anagramsTrueCases() {
        assertThat(Task02_TextAnalysis.areAnagrams("listen", "silent")).isTrue();
        assertThat(Task02_TextAnalysis.areAnagrams("Hello", "hello")).isTrue();
        assertThat(Task02_TextAnalysis.areAnagrams("Dormitory", "dirtyroom")).isTrue();
        assertThat(Task02_TextAnalysis.areAnagrams("", "")).isTrue();
    }

    @Test void anagramsFalseCases() {
        assertThat(Task02_TextAnalysis.areAnagrams("hello", "world")).isFalse();
        assertThat(Task02_TextAnalysis.areAnagrams("Listen!", "silent")).isFalse();  // '!' matters
        assertThat(Task02_TextAnalysis.areAnagrams("aab", "abb")).isFalse();
        assertThat(Task02_TextAnalysis.areAnagrams(null, "abc")).isFalse();
        assertThat(Task02_TextAnalysis.areAnagrams("abc", null)).isFalse();
    }

    // ---------------------------------------------------------------------
    // wordFrequency
    // ---------------------------------------------------------------------

    @Test void wordFrequencyBasic() {
        Map<String, Integer> freq = Task02_TextAnalysis.wordFrequency("Hello, world! Hello.");
        assertThat(freq).containsEntry("hello", 2).containsEntry("world", 1).hasSize(2);
    }

    @Test void wordFrequencyWithDigits() {
        Map<String, Integer> freq = Task02_TextAnalysis.wordFrequency("Java 21 and Java 17");
        assertThat(freq)
                .containsEntry("java", 2)
                .containsEntry("21", 1)
                .containsEntry("17", 1)
                .containsEntry("and", 1)
                .hasSize(4);
    }

    @Test void wordFrequencyEmptyAndNull() {
        assertThat(Task02_TextAnalysis.wordFrequency(null)).isEmpty();
        assertThat(Task02_TextAnalysis.wordFrequency("")).isEmpty();
        assertThat(Task02_TextAnalysis.wordFrequency("   ")).isEmpty();
        assertThat(Task02_TextAnalysis.wordFrequency("!!!")).isEmpty();
    }

    // ---------------------------------------------------------------------
    // mostFrequentWord
    // ---------------------------------------------------------------------

    @Test void mostFrequentWordHighestCount() {
        assertThat(Task02_TextAnalysis.mostFrequentWord("a a b c c c d d d d"))
                .contains("d");
    }

    @Test void mostFrequentWordTieAlphabetical() {
        assertThat(Task02_TextAnalysis.mostFrequentWord("b a c a b c"))
                .contains("a");
    }

    @Test void mostFrequentWordEmpty() {
        assertThat(Task02_TextAnalysis.mostFrequentWord(null)).isEmpty();
        assertThat(Task02_TextAnalysis.mostFrequentWord("")).isEmpty();
        assertThat(Task02_TextAnalysis.mostFrequentWord("  ")).isEmpty();
    }

    // ---------------------------------------------------------------------
    // isPalindrome
    // ---------------------------------------------------------------------

    @Test void palindromeTrueCases() {
        assertThat(Task02_TextAnalysis.isPalindrome("racecar")).isTrue();
        assertThat(Task02_TextAnalysis.isPalindrome("A man a plan a canal Panama")).isTrue();
        assertThat(Task02_TextAnalysis.isPalindrome("No 'x' in Nixon")).isTrue();
        assertThat(Task02_TextAnalysis.isPalindrome("")).isTrue();
    }

    @Test void palindromeFalseCases() {
        assertThat(Task02_TextAnalysis.isPalindrome("hello")).isFalse();
        assertThat(Task02_TextAnalysis.isPalindrome("ab")).isFalse();
        assertThat(Task02_TextAnalysis.isPalindrome(null)).isFalse();
    }

    // ---------------------------------------------------------------------
    // longestWord
    // ---------------------------------------------------------------------

    @Test void longestWordReturnsFirstLongest() {
        assertThat(Task02_TextAnalysis.longestWord("The quick brown fox"))
                .contains("quick");                                    // first of two 5-char words
    }

    @Test void longestWordPreservesCase() {
        assertThat(Task02_TextAnalysis.longestWord("hi WORLD")).contains("WORLD");
    }

    @Test void longestWordEmpty() {
        assertThat(Task02_TextAnalysis.longestWord(null)).isEmpty();
        assertThat(Task02_TextAnalysis.longestWord("")).isEmpty();
        assertThat(Task02_TextAnalysis.longestWord("  ,  ")).isEmpty();
    }
}