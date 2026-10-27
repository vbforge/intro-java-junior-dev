package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * INVESTIGATION — {@link PreparedStatement}.
 *
 * <p>PreparedStatement has three superpowers over plain {@code Statement}:
 * <ol>
 *   <li><b>Safety</b>: placeholders are always treated as VALUES, never as SQL syntax.
 *       The DB driver sends the query structure and the parameters separately.
 *       SQL injection is impossible by construction.</li>
 *   <li><b>Performance</b>: the query is parsed and planned once, then executed with
 *       different parameters. The database can reuse its execution plan.</li>
 *   <li><b>Type safety</b>: {@code setInt}/{@code setString}/{@code setDate} enforce
 *       the type, so wrong-typed params fail loudly.</li>
 * </ol>
 */
public final class PreparedStatementDemo {

    private PreparedStatementDemo() {}

    // ---------------------------------------------------------------------
    // Parameterized query
    // ---------------------------------------------------------------------

    /**
     * {@code SELECT title FROM books WHERE title LIKE ?} — a case-insensitive
     * substring search on title. The {@code ?} is bound via {@code setString(1, ...)}.
     *
     * <p>The {@code "%" + fragment + "%"} is applied on the JAVA side, then bound as a single
     * string value. The DB never sees anything the caller put in {@code fragment} as SQL.
     */
    public static List<String> findTitlesContaining(Connection conn, String fragment) throws SQLException {
        String sql = "SELECT title FROM books WHERE LOWER(title) LIKE LOWER(?) ORDER BY id";
        List<String> hits = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + fragment + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hits.add(rs.getString("title"));
                }
            }
        }
        return hits;
    }

    // ---------------------------------------------------------------------
    // Insert
    // ---------------------------------------------------------------------

    /** {@code INSERT INTO authors (id, name) VALUES (?, ?)}. */
    public static void insertAuthor(Connection conn, int id, String name) throws SQLException {
        String sql = "INSERT INTO authors (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    // ---------------------------------------------------------------------
    // Batch insert
    // ---------------------------------------------------------------------

    /**
     * Batch insert — multiple sets of parameters in ONE round-trip.
     * {@code addBatch()} queues a row, {@code executeBatch()} sends the whole batch.
     * Always {@code clearBatch()} after execute to make the statement reusable.
     */
    public static void insertAuthors(Connection conn, List<Author> authors) throws SQLException {
        String sql = "INSERT INTO authors (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Author a : authors) {
                ps.setInt(1, a.id());
                ps.setString(2, a.name());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public record Author(int id, String name) {}

    // ---------------------------------------------------------------------
    // SQL injection — vulnerable vs safe, side by side
    // ---------------------------------------------------------------------

    /**
     * VULNERABLE — string concatenation. DO NOT DO THIS IN PRODUCTION.
     *
     * <p>Consider {@code malicious = "x' OR '1'='1"}. The concatenated SQL becomes:
     * <pre>
     *   SELECT title FROM books WHERE title = 'x' OR '1'='1'
     * </pre>
     * — and {@code '1'='1'} is always true, so EVERY row is returned. Worse, the
     * attacker could supply {@code "x'; DROP TABLE books; --"} and if the JDBC
     * driver allowed multi-statement execution, the whole table would be gone.
     */
    public static List<String> vulnerableSearchTitles(Connection conn, String userInput) throws SQLException {
        String sql = "SELECT title FROM books WHERE title = '" + userInput + "'";
        List<String> hits = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) hits.add(rs.getString("title"));
        }
        return hits;
    }

    /**
     * SAFE — parameterized. The same {@code malicious} input returns zero results
     * because there is no book with that literal title. The query stays exactly
     * {@code SELECT title FROM books WHERE title = ?}; the value is bound separately.
     */
    public static List<String> safeSearchTitles(Connection conn, String userInput) throws SQLException {
        String sql = "SELECT title FROM books WHERE title = ?";
        List<String> hits = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userInput);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) hits.add(rs.getString("title"));
            }
        }
        return hits;
    }

    // needed because we use Statement in vulnerableSearchTitles but don't want
    // to import both at the top — actually we do, so the import is at the top.
    //private interface Statement extends java.sql.Statement {}
}