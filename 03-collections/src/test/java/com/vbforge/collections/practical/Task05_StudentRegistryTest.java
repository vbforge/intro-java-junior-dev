package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.vbforge.collections.practical.Task05_StudentRegistry.Student;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@Disabled
class Task05_StudentRegistryTest {

    @Test void addAndFind() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "Alice", 90));
        assertThat(r.findById(1).name()).isEqualTo("Alice");
        assertThat(r.findById(99)).isNull();
        assertThat(r.size()).isEqualTo(1);
    }

    @Test void addReplacesExisting() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "Alice", 90));
        r.add(new Student(1, "Alice", 95));
        assertThat(r.size()).isEqualTo(1);
        assertThat(r.findById(1).grade()).isEqualTo(95);
    }

    @Test void addRejectsNull() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        assertThatThrownBy(() -> r.add(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test void allSortedByGradeDescending() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "Alice", 85));
        r.add(new Student(2, "Bob",   92));
        r.add(new Student(3, "Cara",  85));      // tie with Alice; Bob first, then Alice, then Cara (name asc)
        List<Student> sorted = r.allSortedByGradeDesc();
        assertThat(sorted).extracting(Student::name).containsExactly("Bob", "Alice", "Cara");
    }

    @Test void topN() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "Alice", 85));
        r.add(new Student(2, "Bob",   92));
        r.add(new Student(3, "Cara",  88));
        assertThat(r.topN(2)).extracting(Student::name).containsExactly("Bob", "Cara");
        assertThat(r.topN(0)).isEmpty();
        assertThat(r.topN(-1)).isEmpty();
        assertThat(r.topN(100)).hasSize(3);
    }

    @Test void allSortedByName() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "Zara", 70));
        r.add(new Student(2, "Alice", 90));
        r.add(new Student(3, "Mia", 80));
        assertThat(r.allSortedByName()).extracting(Student::name).containsExactly("Alice", "Mia", "Zara");
    }

    @Test void averageGrade() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        assertThat(r.averageGrade()).isZero();
        r.add(new Student(1, "A", 80));
        r.add(new Student(2, "B", 90));
        assertThat(r.averageGrade()).isCloseTo(85.0, within(1e-9));
    }

    @Test void gradeDistribution() {
        Task05_StudentRegistry r = new Task05_StudentRegistry();
        r.add(new Student(1, "A", 95));
        r.add(new Student(2, "B", 92));
        r.add(new Student(3, "C", 85));
        r.add(new Student(4, "D", 75));
        r.add(new Student(5, "E", 55));
        Map<String, Integer> dist = r.gradeDistribution();
        assertThat(dist).containsEntry("90-100", 2)
                        .containsEntry("80-89", 1)
                        .containsEntry("70-79", 1)
                        .containsEntry("0-59", 1)
                        .doesNotContainKey("60-69");
    }
}