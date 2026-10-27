package com.vbforge.streams.investigation;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.vbforge.streams.investigation.CollectorsDemo.Person;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CollectorsDemoTest {

    @Test void toListAndToSet() {
        assertThat(CollectorsDemo.toList()).containsExactly(1, 2, 3);
        assertThat(CollectorsDemo.toSet()).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test void toMapDuplicateThrows() {
        assertThat(CollectorsDemo.toMapDuplicateThrows()).isTrue();
    }

    @Test void toMapWithMergeSumsValues() {
        // "a" has length 1, "A" has length 1 → 1 + 1 = 2
        Map<String, Integer> m = CollectorsDemo.toMapWithMerge();
        assertThat(m).containsEntry("a", 2).containsEntry("b", 1);
    }

    @Test void numericCollectors() {
        List<Person> people = List.of(
                new Person("A", 20),
                new Person("B", 30),
                new Person("C", 40)
        );
        assertThat(CollectorsDemo.count(List.of("a", "b", "c"))).isEqualTo(3);
        assertThat(CollectorsDemo.sumOfAges(people)).isEqualTo(90);
        assertThat(CollectorsDemo.averageAge(people)).isCloseTo(30.0, within(1e-9));
        assertThat(CollectorsDemo.maxAge(people)).isEqualTo(40);
    }

    @Test void groupingByLength() {
        Map<Integer, List<String>> m = CollectorsDemo.byLength(List.of("hi", "bye", "hello", "ok", "cat"));
        assertThat(m.get(2)).containsExactlyInAnyOrder("hi", "ok");
        assertThat(m.get(3)).containsExactlyInAnyOrder("bye", "cat");
        assertThat(m.get(5)).containsExactly("hello");
    }

    @Test void countByLength() {
        Map<Integer, Long> m = CollectorsDemo.countByLength(List.of("hi", "bye", "hello", "ok", "cat"));
        assertThat(m.get(2)).isEqualTo(2L);
        assertThat(m.get(3)).isEqualTo(2L);
        assertThat(m.get(5)).isEqualTo(1L);
    }

    @Test void partitioningByAdult() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 15),
                new Person("Cara", 18)
        );
        Map<Boolean, List<String>> m = CollectorsDemo.namesByAdultStatus(people);
        assertThat(m.get(true)).containsExactlyInAnyOrder("Alice", "Cara");
        assertThat(m.get(false)).containsExactly("Bob");
    }

    @Test void partitioningAlwaysHasBothKeys() {
        Map<Boolean, List<Integer>> m = CollectorsDemo.evenOddAlwaysHasBothKeys(List.of(2, 4, 6));
        assertThat(m.get(true)).containsExactlyInAnyOrder(2, 4, 6);
        assertThat(m.get(false)).isEmpty();     // must exist as a key, but empty
        assertThat(m).containsKey(false);
    }

    @Test void joining() {
        List<String> words = List.of("a", "b", "c");
        assertThat(CollectorsDemo.joinedSimple(words)).isEqualTo("abc");
        assertThat(CollectorsDemo.joinedWithDelimiter(words)).isEqualTo("a, b, c");
        assertThat(CollectorsDemo.joinedWithPrefixSuffix(words)).isEqualTo("[a, b, c]");
    }

    @Test void teeingMinAndMax() {
        assertThat(CollectorsDemo.minAndMaxSummary(List.of(3, 1, 5, 2, 4))).isEqualTo("1..5");
    }
}