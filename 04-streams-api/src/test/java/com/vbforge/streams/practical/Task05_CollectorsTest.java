package com.vbforge.streams.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.vbforge.streams.practical.Task05_Collectors.Person;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task05_CollectorsTest {

    @Test void groupByFirstLetter() {
        Map<Character, List<String>> m = Task05_Collectors.groupByFirstLetter(
                List.of("apple", "avocado", "banana", "cherry", "blueberry"));
        assertThat(m.get('a')).containsExactly("apple", "avocado");
        assertThat(m.get('b')).containsExactly("banana", "blueberry");
        assertThat(m.get('c')).containsExactly("cherry");
        assertThat(m).hasSize(3);
    }

    @Test void countWords() {
        assertThat(Task05_Collectors.countWords(List.of("a", "b", "a", "c", "a")))
                .containsEntry("a", 3L).containsEntry("b", 1L).containsEntry("c", 1L);
        assertThat(Task05_Collectors.countWords(List.of())).isEmpty();
    }

    @Test void adultNamesCsv() {
        List<Person> people = List.of(
                new Person("Zed", 30),
                new Person("Alice", 20),
                new Person("Timmy", 10),
                new Person("Bob", 40)
        );
        assertThat(Task05_Collectors.adultNamesCsv(people)).isEqualTo("Alice, Bob, Zed");
        assertThat(Task05_Collectors.adultNamesCsv(List.of())).isEmpty();
    }

    @Test void partitionByAdult() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 10),
                new Person("Cara", 18)
        );
        Map<Boolean, List<Person>> m = Task05_Collectors.partitionByAdult(people);
        assertThat(m.get(true)).extracting(Person::name).containsExactlyInAnyOrder("Alice", "Cara");
        assertThat(m.get(false)).extracting(Person::name).containsExactly("Bob");
    }

    @Test void partitionAlwaysHasBothKeys() {
        List<Person> onlyAdults = List.of(new Person("Alice", 30));
        Map<Boolean, List<Person>> m = Task05_Collectors.partitionByAdult(onlyAdults);
        assertThat(m).containsKey(true).containsKey(false);
        assertThat(m.get(false)).isEmpty();
    }

    @Test void wordToLengthWithDuplicates() {
        assertThat(Task05_Collectors.wordToLength(List.of("hi", "hello", "hi")))
                .containsEntry("hi", 2).containsEntry("hello", 5).hasSize(2);
    }

    @Test void totalAge() {
        List<Person> people = List.of(
                new Person("A", 20),
                new Person("B", 30),
                new Person("C", 40)
        );
        assertThat(Task05_Collectors.totalAge(people)).isEqualTo(90L);
        assertThat(Task05_Collectors.totalAge(List.of())).isZero();
    }
}