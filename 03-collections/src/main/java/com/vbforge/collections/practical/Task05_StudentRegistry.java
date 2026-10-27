package com.vbforge.collections.practical;

import java.util.List;
import java.util.Map;

/**
 * PRACTICAL TASK 05 — {@code Map} + {@code Comparable} / {@code Comparator}.
 *
 * <p>Implement a registry that stores students by unique id and supports
 * sorted queries. The {@link Student} record already implements {@code Comparable}
 * (by grade DESCENDING, tie-break by name ASCENDING) — do not modify it.
 */
public final class Task05_StudentRegistry {

    public record Student(int id, String name, double grade) implements Comparable<Student> {
        @Override
        public int compareTo(Student other) {
            int byGrade = Double.compare(other.grade, this.grade);  // higher grade first
            if (byGrade != 0) return byGrade;
            return this.name.compareTo(other.name);
        }
    }

    // TODO: store students in a Map<Integer, Student>

    /**
     * Add a student. If a student with the same id already exists → replace it.
     * @throws IllegalArgumentException if {@code student} is null.
     */
    public void add(Student student) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Return the student with the given id, or {@code null} if absent. */
    public Student findById(int id) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Return all students, sorted by their NATURAL order (see {@link Student#compareTo}). */
    public List<Student> allSortedByGradeDesc() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Return the top {@code n} students by natural order. {@code n <= 0} → empty list. */
    public List<Student> topN(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return all students sorted ALPHABETICALLY by name.
     * Hint: {@code Comparator.comparing(Student::name)}.
     */
    public List<Student> allSortedByName() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Average grade across all students. Empty registry → 0.0. */
    public double averageGrade() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Grade distribution: buckets of 10.
     * Key {@code "90-100"} for grades in [90, 100], {@code "80-89"} for [80, 90), etc.
     * Buckets: {@code "90-100", "80-89", "70-79", "60-69", "0-59"}.
     * Only non-empty buckets must appear.
     */
    public Map<String, Integer> gradeDistribution() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of registered students. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}