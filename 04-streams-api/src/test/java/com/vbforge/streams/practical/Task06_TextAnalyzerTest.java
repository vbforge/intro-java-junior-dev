package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.OptionalDouble;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Disabled
class Task06_TextAnalyzerTest {

    @Test void topN() {
        assertThat(Task06_TextAnalyzer.topN("a a b c c c d d d d", 2))
                .containsExactly("d", "c");
    }

    @Test void topNTiesAlphabetical() {
        assertThat(Task06_TextAnalyzer.topN("b a c", 3)).containsExactly("a", "b", "c");
    }

    @Test void topNEdgeCases() {
        assertThat(Task06_TextAnalyzer.topN(null, 5)).isEmpty();
        assertThat(Task06_TextAnalyzer.topN("", 5)).isEmpty();
        assertThat(Task06_TextAnalyzer.topN("   ", 5)).isEmpty();
        assertThat(Task06_TextAnalyzer.topN("abc", 0)).isEmpty();
        assertThat(Task06_TextAnalyzer.topN("abc", -1)).isEmpty();
        assertThat(Task06_TextAnalyzer.topN("abc", 100)).containsExactly("abc");
    }

    @Test void topNIsCaseInsensitive() {
        assertThat(Task06_TextAnalyzer.topN("Java java JAVA", 1)).containsExactly("java");
    }

    @Test void letterFrequency() {
        Map<Character, Long> freq = Task06_TextAnalyzer.letterFrequency("Hello, World!");
        assertThat(freq).containsEntry('l', 3L)
                        .containsEntry('o', 2L)
                        .containsEntry('h', 1L)
                        .containsEntry('e', 1L)
                        .containsEntry('w', 1L)
                        .containsEntry('r', 1L)
                        .containsEntry('d', 1L);
    }

    @Test void letterFrequencyIgnoresNonLetters() {
        assertThat(Task06_TextAnalyzer.letterFrequency("a1b2c3!")).containsOnlyKeys('a', 'b', 'c');
        assertThat(Task06_TextAnalyzer.letterFrequency(null)).isEmpty();
        assertThat(Task06_TextAnalyzer.letterFrequency("")).isEmpty();
    }

    @Test void averageWordLength() {
        OptionalDouble avg = Task06_TextAnalyzer.averageWordLength("hi there");
        assertThat(avg).isPresent();
        assertThat(avg.getAsDouble()).isCloseTo(3.5, within(1e-9));

        assertThat(Task06_TextAnalyzer.averageWordLength("")).isEmpty();
        assertThat(Task06_TextAnalyzer.averageWordLength("   ")).isEmpty();
        assertThat(Task06_TextAnalyzer.averageWordLength(null)).isEmpty();
    }

    @Test void distinctWordsSortedByLengthDesc() {
        assertThat(Task06_TextAnalyzer.distinctWordsSortedByLengthDesc("a bb ccc bb"))
                .containsExactly("ccc", "bb", "a");
        assertThat(Task06_TextAnalyzer.distinctWordsSortedByLengthDesc("")).isEmpty();
        assertThat(Task06_TextAnalyzer.distinctWordsSortedByLengthDesc(null)).isEmpty();
    }
}