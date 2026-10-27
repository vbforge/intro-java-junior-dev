package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * INVESTIGATION — reading a {@link ResultSet}.
 */
public final class ResultSetDemo {

    private ResultSetDemo() {}

    /**
     * {@code rs.next()} returns {@code false} on an empty result set. Use it as the "row exists?" check.
     */
    public static Optional<String> titleById(Connection conn, int id) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT title FROM books WHERE id = " + id)) {
            return rs.next()
                    ? Optional.of(rs.getString("title"))
                    : Optional.empty();
        }
    }

    /**
     * Reading the SAME column by NAME and by INDEX produces the same value — indexes are 1-based.
     */
    public static String sameColumnByNameAndIndex(Connection conn, int bookId) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT title FROM books WHERE id = " + bookId)) {
            rs.next();
            String byName  = rs.getString("title");
            String byIndex = rs.getString(1);
            // Return by index (identical to by name) — test asserts they're equal.
            if (!byName.equals(byIndex)) {
                throw new IllegalStateException("byName and byIndex should match");
            }
            return byIndex;
        }
    }

    /**
     * {@link ResultSetMetaData} exposes the column NAMES and TYPES of the result set.
     * Useful for building generic readers or for validating expected column names in tests.
     */
    public static List<String> columnNames(Connection conn, String sql) throws SQLException {
        List<String> names = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                names.add(meta.getColumnLabel(i));
            }
        }
        return names;
    }

    /**
     * Reading the full result set into a list of maps — the "generic reader" pattern.
     * Column names become keys, values are raw {@code Object}s from {@code getObject}.
     * In real code you'd map into typed records instead.
     */
    public static List<Map<String, Object>> readAsRows(Connection conn, String sql) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                rows.add(row);
            }
        }
        return rows;
    }
}