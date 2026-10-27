package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task01_WordCounterTest {

    @Test void emptyOrNull() {
        assertThat(Task01_WordCounter.count(null)).isEmpty();
        assertThat(Task01_WordCounter.count("")).isEmpty();
        assertThat(Task01_WordCounter.count("   ")).isEmpty();
    }

    @Test void countsAreCaseInsensitive() {
        Map<String, Integer> counts = Task01_WordCounter.count("The the THE");
        assertThat(counts).containsEntry("the", 3).hasSize(1);
    }

    @Test void punctuationIsSeparator() {
        Map<String, Integer> counts = Task01_WordCounter.count("Hello, world! Hello.");
        assertThat(counts).containsEntry("hello", 2).containsEntry("world", 1);
    }

    @Test void digitsAreWordsToo() {
        Map<String, Integer> counts = Task01_WordCounter.count("Java 21 and Java 17");
        assertThat(counts).containsEntry("java", 2).containsEntry("21", 1).containsEntry("17", 1);
    }

    @Test void topNReturnsRanked() {
        Map<String, Integer> top = Task01_WordCounter.topN("a a b c c c d d d d", 2);
        assertThat(top).containsEntry("d", 4).containsEntry("c", 3).hasSize(2);
        // map iteration order should be by rank
        assertThat(top.keySet()).containsExactly("d", "c");
    }

    @Test void topNTiesBrokenAlphabetically() {
        Map<String, Integer> top = Task01_WordCounter.topN("b a c", 3);
        // all count 1; alphabetical: a, b, c
        assertThat(top.keySet()).containsExactly("a", "b", "c");
    }

    @Test void topNEdgeCases() {
        assertThat(Task01_WordCounter.topN("abc", 0)).isEmpty();
        assertThat(Task01_WordCounter.topN("abc", -1)).isEmpty();
        assertThat(Task01_WordCounter.topN("abc", 100)).hasSize(1);
    }
}