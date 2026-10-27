package com.vbforge.exam.practical;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * PRACTICAL TASK 03 — OOP (records) + Collections + Streams + Optional + Comparators.
 *
 * <p>A tiny gradebook. Students are added to the registry; several sorted and
 * grouped views are exposed. All methods return fresh objects/lists — the
 * internal state is never leaked.
 */
public final class Task03_StudentGrades {

    /** A student entry. Do not modify. */
    public record Student(String name, String subject, int score) {}

    // ---------------------------------------------------------------------
    // Mutation
    // ---------------------------------------------------------------------

    /**
     * Add a student.
     * @throws NullPointerException if {@code student} is null.
     *
     * <p>Duplicates ARE allowed at the storage layer — the same student name can
     * appear in multiple subjects, and even multiple times in the same subject
     * (e.g. two different exams). Later views may collapse them.
     */
    public void add(Student student) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of students currently registered. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Views
    // ---------------------------------------------------------------------

    /**
     * Return ALL registered students in insertion order.
     * Returns a fresh list — mutating it must NOT affect the registry.
     */
    public List<Student> allStudents() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Group student NAMES by subject. Names within each group are sorted
     * ALPHABETICALLY. Duplicate names within the same subject appear twice.
     *
     * <ul>
     *   <li>{@code (Alice, Math, 90)}, {@code (Bob, Math, 85)} → {@code {Math: [Alice, Bob]}}</li>
     * </ul>
     *
     * <p>Empty registry → empty map.
     */
    public Map<String, List<String>> studentsBySubject() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * The student with the highest score in the given subject.
     * Ties broken by name ASCENDING (first alphabetically).
     * No students in that subject → {@code Optional.empty()}.
     *
     * <p>Example: two students in Math both score 90 — {@code topStudentIn("Math")}
     * returns the alphabetically first one.
     */
    public Optional<Student> topStudentIn(String subject) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Average score per subject. Empty registry → empty map.
     * Uses {@code double} for the average (a subject with 3 scores of 90, 85, 90 → 88.33…).
     */
    public Map<String, Double> averageScoreBySubject() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Top {@code n} students overall, sorted by score DESCENDING, ties broken by
     * name ASCENDING (alphabetical). {@code n <= 0} → empty list.
     * If {@code n >= size()}, returns all students in that order.
     *
     * <p>Note the alphabetical tie-break also applies when the SAME score appears
     * in different subjects — the exam doesn't try to pick a subject order.
     */
    public List<Student> topNOverall(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Score distribution into fixed buckets.
     * <ul>
     *   <li>{@code "90-100"} — score ∈ [90, 100]</li>
     *   <li>{@code "80-89"}  — score ∈ [80, 90)</li>
     *   <li>{@code "70-79"}  — score ∈ [70, 80)</li>
     *   <li>{@code "60-69"}  — score ∈ [60, 70)</li>
     *   <li>{@code "0-59"}   — score ∈ [0, 60)</li>
     * </ul>
     * Only NON-EMPTY buckets appear in the returned map.
     * Empty registry → empty map.
     */
    public Map<String, Integer> scoreDistribution() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}