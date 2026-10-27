package com.vbforge.collections.investigation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * INVESTIGATION — {@link Comparable} vs {@link Comparator}.
 *
 * <ul>
 *   <li>{@code Comparable<T>} — "I know how to compare myself to another T". ONE natural order.
 *       Method: {@code int compareTo(T other)}. Implemented by the class ITSELF.</li>
 *   <li>{@code Comparator<T>} — "I compare two Ts". MANY possible orders.
 *       Method: {@code int compare(T a, T b)}. External to the class.</li>
 * </ul>
 *
 * <p>Both methods return a negative int, zero, or a positive int — like {@code a - b}.
 * Prefer {@code Integer.compare} / {@code Comparator.comparing} to avoid overflow.
 */
public final class ComparableComparatorDemo {

    private ComparableComparatorDemo() {}

    // ---------------------------------------------------------------------
    // A class with NATURAL ordering
    // ---------------------------------------------------------------------

    /** Natural order: by id ascending. */
    public record Employee(int id, String name, double salary) implements Comparable<Employee> {
        @Override
        public int compareTo(Employee other) {
            return Integer.compare(this.id, other.id);
        }
    }

    // ---------------------------------------------------------------------
    // Various Comparators
    // ---------------------------------------------------------------------

    public static final Comparator<Employee> BY_NAME =
            Comparator.comparing(Employee::name);

    public static final Comparator<Employee> BY_SALARY_DESC =
            Comparator.comparingDouble(Employee::salary).reversed();

    /** Chain comparators: primary by name, tie-break by id. */
    public static final Comparator<Employee> BY_NAME_THEN_ID =
            Comparator.comparing(Employee::name).thenComparingInt(Employee::id);

    // ---------------------------------------------------------------------
    // Demos
    // ---------------------------------------------------------------------

    private static List<Employee> sample() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(3, "Charlie", 50_000));
        list.add(new Employee(1, "Alice",   70_000));
        list.add(new Employee(2, "Bob",     40_000));
        return list;
    }

    /** {@code Collections.sort} / {@code List.sort} uses {@code compareTo}. */
    public static int[] naturalOrderIds() {
        List<Employee> list = sample();
        list.sort(null);                                // null → natural order (Comparable)
        int[] ids = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ids[i] = list.get(i).id();
        return ids;                                     // {1, 2, 3}
    }

    public static String[] sortedByName() {
        List<Employee> list = sample();
        list.sort(BY_NAME);
        return list.stream().map(Employee::name).toArray(String[]::new);    // [Alice, Bob, Charlie]
    }

    public static String[] sortedBySalaryDesc() {
        List<Employee> list = sample();
        list.sort(BY_SALARY_DESC);
        return list.stream().map(Employee::name).toArray(String[]::new);    // [Alice, Charlie, Bob]
    }

    // ---------------------------------------------------------------------
    // Reverse natural order using Comparator.reverseOrder()
    // ---------------------------------------------------------------------

    public static int[] reversedIds() {
        List<Employee> list = sample();
        list.sort(Comparator.reverseOrder());
        int[] ids = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ids[i] = list.get(i).id();
        return ids;                                     // {3, 2, 1}
    }
}