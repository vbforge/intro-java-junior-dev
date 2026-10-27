package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * PRACTICAL TASK 01 — basic CRUD with {@code PreparedStatement}.
 *
 * <p>All SQL must use {@link java.sql.PreparedStatement} with {@code ?} placeholders for
 * any variable that is a String. Integers MAY be concatenated directly into SQL (safe),
 * but the habit of using PreparedStatement everywhere is better.
 */
public final class Task01_BasicCrud {

    private Task01_BasicCrud() {}

    /**
     * {@code INSERT INTO authors (id, name) VALUES (?, ?)}.
     * Existing id → propagates the {@link SQLException} (duplicate PK).
     */
    public static void insertAuthor(Connection conn, int id, String name) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * {@code UPDATE books SET price_cents = ? WHERE id = ?}.
     * @return the number of rows affected (0 if no book with that id, 1 otherwise).
     */
    public static int updateBookPrice(Connection conn, int bookId, int newPriceCents) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * {@code DELETE FROM books WHERE id = ?}.
     * @return the number of rows affected (0 or 1).
     */
    public static int deleteBook(Connection conn, int bookId) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * {@code SELECT name FROM authors WHERE id = ?} — returns {@code Optional.empty()} if absent.
     */
    public static Optional<String> findAuthorName(Connection conn, int id) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * {@code SELECT id, name FROM authors ORDER BY id} — returns all rows as "id:name" strings.
     * Example entry: {@code "1:Tolkien"}.
     */
    public static List<String> allAuthorsAsStrings(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}