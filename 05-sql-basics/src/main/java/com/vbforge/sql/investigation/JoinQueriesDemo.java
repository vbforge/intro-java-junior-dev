package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * INVESTIGATION — {@code JOIN}s.
 */
public final class JoinQueriesDemo {

    private JoinQueriesDemo() {}

    /**
     * {@code INNER JOIN} — returns pairs that have a match on BOTH sides.
     * Every book has an author in the seed data, so the result has 7 entries.
     */
    public static List<String> titleAndAuthorPairs(Connection conn) throws SQLException {
        String sql = """
                SELECT b.title, a.name
                FROM books b
                JOIN authors a ON a.id = b.author_id
                ORDER BY b.id
                """;
        List<String> pairs = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                pairs.add(rs.getString("title") + " by " + rs.getString("name"));
            }
        }
        return pairs;
    }

    /**
     * {@code LEFT JOIN ... WHERE right.id IS NULL} — the classic "rows with NO match" idiom.
     * Seed has Herbert with no books → result is {@code ["Herbert"]}.
     */
    public static List<String> authorsWithNoBooks(Connection conn) throws SQLException {
        String sql = """
                SELECT a.name
                FROM authors a
                LEFT JOIN books b ON b.author_id = a.id
                WHERE b.id IS NULL
                ORDER BY a.name
                """;
        List<String> names = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) names.add(rs.getString("name"));
        }
        return names;
    }

    /** JOIN + WHERE — titles of a given author's books. */
    public static List<String> titlesByAuthorName(Connection conn, String authorName) throws SQLException {
        String sql = """
                SELECT b.title
                FROM books b
                JOIN authors a ON a.id = b.author_id
                WHERE a.name = ?
                ORDER BY b.title
                """;
        List<String> titles = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, authorName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) titles.add(rs.getString("title"));
            }
        }
        return titles;
    }
}