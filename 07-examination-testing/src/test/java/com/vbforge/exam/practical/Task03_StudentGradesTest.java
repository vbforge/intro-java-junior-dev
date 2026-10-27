package com.vbforge.exam.practical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.vbforge.exam.practical.Task03_StudentGrades.Student;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@Disabled
class Task03_StudentGradesTest {

    private Task03_StudentGrades reg;

    @BeforeEach
    void setUp() {
        reg = new Task03_StudentGrades();
        reg.add(new Student("Alice",  "Math",      90));
        reg.add(new Student("Bob",    "Math",      85));
        reg.add(new Student("Cara",   "Math",      90));
        reg.add(new Student("Dave",   "Physics",   78));
        reg.add(new Student("Eve",    "Physics",   82));
        reg.add(new Student("Frank",  "Chemistry", 88));
    }

    // ---------------------------------------------------------------------
    // Mutation
    // ---------------------------------------------------------------------

    @Test void addRejectsNull() {
        assertThatThrownBy(() -> reg.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test void size() {
        assertThat(reg.size()).isEqualTo(6);
    }

    // ---------------------------------------------------------------------
    // Views
    // ---------------------------------------------------------------------

    @Test void allStudentsInInsertionOrder() {
        assertThat(reg.allStudents()).extracting(Student::name)
                .containsExactly("Alice", "Bob", "Cara", "Dave", "Eve", "Frank");
    }

    @Test void allStudentsReturnsCopy() {
        List<Student> view = reg.allStudents();
        view.clear();
        assertThat(reg.size()).isEqualTo(6);
    }

    @Test void studentsBySubjectGroupsAndSortsNames() {
        Map<String, List<String>> bySubject = reg.studentsBySubject();

        assertThat(bySubject.get("Math")).containsExactly("Alice", "Bob", "Cara");
        assertThat(bySubject.get("Physics")).containsExactly("Dave", "Eve");
        assertThat(bySubject.get("Chemistry")).containsExactly("Frank");
        assertThat(bySubject).hasSize(3);
    }

    @Test void topStudentInSubject() {
        // Alice and Cara both have 90 → alphabetical tie-break = Alice
        assertThat(reg.topStudentIn("Math"))
                .hasValueSatisfying(s -> assertThat(s.name()).isEqualTo("Alice"));

        assertThat(reg.topStudentIn("Physics"))
                .hasValueSatisfying(s -> assertThat(s.name()).isEqualTo("Eve"));

        assertThat(reg.topStudentIn("History")).isEmpty();
    }

    @Test void averageScoreBySubject() {
        Map<String, Double> avg = reg.averageScoreBySubject();
        assertThat(avg.get("Math")).isCloseTo(88.3333, within(0.001));
        assertThat(avg.get("Physics")).isCloseTo(80.0, within(0.001));
        assertThat(avg.get("Chemistry")).isCloseTo(88.0, within(0.001));
    }

    @Test void averageScoreOnEmptyRegistry() {
        assertThat(new Task03_StudentGrades().averageScoreBySubject()).isEmpty();
    }

    @Test void topNOverall() {
        // Expected order: Alice (90), Cara (90), Frank (88), Bob (85), Eve (82), Dave (78)
        assertThat(reg.topNOverall(2)).extracting(Student::name)
                .containsExactly("Alice", "Cara");
        assertThat(reg.topNOverall(3)).extracting(Student::name)
                .containsExactly("Alice", "Cara", "Frank");
    }

    @Test void topNOverallEdgeCases() {
        assertThat(reg.topNOverall(0)).isEmpty();
        assertThat(reg.topNOverall(-1)).isEmpty();
        assertThat(reg.topNOverall(100)).hasSize(6);
    }

    @Test void scoreDistribution() {
        Map<String, Integer> dist = reg.scoreDistribution();
        assertThat(dist)
                .containsEntry("90-100", 2)
                .containsEntry("80-89",  3)
                .containsEntry("70-79",  1)
                .doesNotContainKey("60-69")
                .doesNotContainKey("0-59");
    }

    @Test void scoreDistributionOnEmptyRegistry() {
        assertThat(new Task03_StudentGrades().scoreDistribution()).isEmpty();
    }
}