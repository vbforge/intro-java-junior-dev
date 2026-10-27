package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * PRACTICAL TASK 03 — {@code JOIN}s.
 */
public final class Task03_JoinQueries {

    private Task03_JoinQueries() {}

    /**
     * For every book, return {@code "<title> / <author name>"}, sorted by book id.
     * Use an INNER JOIN.
     */
    public static List<String> titleSlashAuthor(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return author names that have NO books, sorted alphabetically.
     * Use {@code LEFT JOIN ... WHERE books.id IS NULL}.
     */
    public static List<String> authorsWithNoBooks(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return titles written by the given author name (JOIN + WHERE), sorted alphabetically.
     * Unknown author → empty list.
     */
    public static List<String> titlesByAuthor(Connection conn, String authorName) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}