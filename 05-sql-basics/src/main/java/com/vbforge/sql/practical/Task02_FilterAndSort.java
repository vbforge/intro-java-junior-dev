package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * PRACTICAL TASK 02 — {@code WHERE}, {@code ORDER BY}, {@code LIMIT}.
 *
 * <p>Every method returns titles only.
 */
public final class Task02_FilterAndSort {

    private Task02_FilterAndSort() {}

    /**
     * Books with {@code price_cents < maxCentsExclusive}, sorted by price ASC, then title ASC.
     * {@code maxCentsExclusive <= 0} → empty list.
     */
    public static List<String> booksCheaperThan(Connection conn, int maxCentsExclusive) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * The {@code n} most expensive books, sorted by price DESC, then title ASC.
     * {@code n <= 0} → empty list.
     * Hint: {@code ORDER BY price_cents DESC, title ASC LIMIT n} — but only if {@code n} is bound
     *       as a parameter or concatenated as an int. Both are safe here (int, not user input).
     */
    public static List<String> topNMostExpensive(Connection conn, int n) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Books with {@code fromYear <= published_year <= toYear}, sorted by year ASC, then title ASC.
     * {@code fromYear > toYear} → empty list (no exception).
     */
    public static List<String> booksInYearRange(Connection conn, int fromYear, int toYear) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}