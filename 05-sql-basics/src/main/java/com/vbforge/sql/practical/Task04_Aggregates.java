package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * PRACTICAL TASK 04 — aggregates, {@code GROUP BY}, {@code HAVING}.
 */
public final class Task04_Aggregates {

    private Task04_Aggregates() {}

    /**
     * {@code SELECT AVG(price_cents) FROM books}.
     * @return {@link OptionalDouble#empty()} if the books table is empty.
     * Hint: after {@code rs.next()}, call {@code rs.getDouble(1)} then {@code rs.wasNull()}.
     */
    public static OptionalDouble averagePriceCents(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Book count per author NAME (INNER JOIN + GROUP BY). Only authors with ≥ 1 book appear.
     * Ordering: sort the returned map entries by author name ascending (use a LinkedHashMap).
     */
    public static Map<String, Integer> bookCountByAuthorName(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Author names with more than {@code threshold} books, ordered by count DESC then name ASC.
     * Use HAVING.
     */
    public static List<String> authorsWithMoreThan(Connection conn, int threshold) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}