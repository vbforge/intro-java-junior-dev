package com.vbforge.sql.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * Test helper: an isolated, fresh, in-memory H2 database.
 *
 * <p>Every {@link #create()} call spins up a DIFFERENT in-memory database (UUID-named),
 * so tests cannot interfere with each other. Close with {@link #close()} when done —
 * the in-memory DB is destroyed automatically at that point.
 *
 * <p>Schema and seed live in {@code src/test/resources/db/} — read those files for
 * the exact table structure and initial rows.
 */
public final class TestDatabase implements AutoCloseable {

    private final Connection connection;

    private TestDatabase(Connection connection) {
        this.connection = connection;
    }

    /** Creates a fresh in-memory DB with schema + seed data. */
    public static TestDatabase create() throws SQLException {
        return createInternal(true);
    }

    /** Creates a fresh in-memory DB with schema but no rows — useful for testing empty results. */
    public static TestDatabase createEmpty() throws SQLException {
        return createInternal(false);
    }

    private static TestDatabase createInternal(boolean seed) throws SQLException {
        String dbName = "test_" + UUID.randomUUID().toString().replace("-", "");
        //String url    = "jdbc:h2:mem:" + dbName;
        // DATABASE_TO_LOWER=TRUE keeps identifiers as-written (lowercase in our DDL).
        // Without it, H2 folds unquoted identifiers to UPPERCASE and
        // ResultSetMetaData.getColumnLabel() returns "ID" instead of "id".
        String url    = "jdbc:h2:mem:" + dbName + ";DATABASE_TO_LOWER=TRUE";
        Connection conn = DriverManager.getConnection(url, "sa", "");
        executeScript(conn, readResource("/db/schema.sql"));
        if (seed) {
            executeScript(conn, readResource("/db/seed.sql"));
        }
        return new TestDatabase(conn);
    }

    public Connection connection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    /**
     * Runs a SQL script split on {@code ;}. Safe here because neither schema.sql
     * nor seed.sql contains semicolons inside string literals.
     */
    private static void executeScript(Connection conn, String script) throws SQLException {
        for (String raw : script.split(";")) {
            String stmt = raw.trim();
            if (stmt.isEmpty()) continue;
            try (Statement st = conn.createStatement()) {
                st.execute(stmt);
            }
        }
    }

    private static String readResource(String path) {
        try (InputStream in = TestDatabase.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Resource not found on classpath: " + path);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}