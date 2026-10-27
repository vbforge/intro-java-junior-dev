package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * INVESTIGATION — aggregate queries: COUNT, SUM, AVG, MIN, MAX, GROUP BY, HAVING.
 */
public final class AggregateQueriesDemo {

    private AggregateQueriesDemo() {}

    /**
     * {@code SELECT AVG(price_cents) FROM books}.
     * <p>GOTCHA: an aggregate over an EMPTY table returns ONE row with NULL.
     * {@code rs.next()} is still true — but {@code rs.getDouble(1)} gives 0.0 and
     * {@code rs.wasNull()} is true. Detect via {@code wasNull} and return OptionalDouble.empty().
     */
    public static OptionalDouble averagePriceCents(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT AVG(price_cents) FROM books")) {
            rs.next();
            double avg = rs.getDouble(1);
            return rs.wasNull() ? OptionalDouble.empty() : OptionalDouble.of(avg);
        }
    }

    /**
     * {@code GROUP BY author_id} — book count per author.
     * <p>Herbert (no books) is NOT in the result — {@code GROUP BY} only emits groups
     * that actually occurred in the source rows. To include "0 books" authors, you'd
     * need a LEFT JOIN with the aggregate.
     */
    public static Map<String, Integer> bookCountByAuthorName(Connection conn) throws SQLException {
        String sql = """
                SELECT a.name, COUNT(*) AS cnt
                FROM books b
                JOIN authors a ON a.id = b.author_id
                GROUP BY a.name
                ORDER BY a.name
                """;
        Map<String, Integer> counts = new LinkedHashMap<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                counts.put(rs.getString("name"), rs.getInt("cnt"));
            }
        }
        return counts;
    }

    /**
     * {@code HAVING} filters GROUPS after aggregation (contrast with {@code WHERE}, which
     * filters ROWS before aggregation). Result: Tolkien (3), Rowling (2), ordered by count
     * DESC then name ASC.
     */
    public static java.util.List<String> authorsWithMoreThanOneBook(Connection conn) throws SQLException {
        String sql = """
                SELECT a.name
                FROM books b
                JOIN authors a ON a.id = b.author_id
                GROUP BY a.name
                HAVING COUNT(*) > 1
                ORDER BY COUNT(*) DESC, a.name ASC
                """;
        java.util.List<String> names = new java.util.ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) names.add(rs.getString("name"));
        }
        return names;
    }
}