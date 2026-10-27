package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * INVESTIGATION — JDBC basics with {@link Statement}.
 *
 * <p>These methods deliberately avoid user-supplied strings. For any input that
 * comes from outside your program, use {@code PreparedStatement} — see
 * {@link PreparedStatementDemo}.
 */
public final class JdbcBasicsDemo {

    private JdbcBasicsDemo() {}

    // ---------------------------------------------------------------------
    // A single scalar result
    // ---------------------------------------------------------------------

    /**
     * {@code SELECT COUNT(*) FROM books} — returns the row count.
     * The ResultSet has exactly one row with one column; {@code getLong(1)} reads it by index.
     */
    public static long countBooks(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getLong(1);
        }
    }

    // ---------------------------------------------------------------------
    // A list of results
    // ---------------------------------------------------------------------

    /**
     * {@code SELECT title FROM books ORDER BY id} — returns all titles in ID order.
     * {@code rs.next()} returns {@code true} once for each row and {@code false} at the end.
     */
    public static List<String> allBookTitlesOrderedById(Connection conn) throws SQLException {
        String sql = "SELECT title FROM books ORDER BY id";
        List<String> titles = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                titles.add(rs.getString("title"));
            }
        }
        return titles;
    }

    /**
     * {@code SELECT SUM(price_cents) FROM books} — a single aggregate.
     * Sum of seed data is 13893.
     */
    public static long totalPriceCents(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT SUM(price_cents) FROM books")) {
            rs.next();
            return rs.getLong(1);
        }
    }
}