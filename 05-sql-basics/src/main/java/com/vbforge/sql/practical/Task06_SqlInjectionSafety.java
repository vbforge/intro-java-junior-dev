package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * PRACTICAL TASK 06 — write the SQL-injection-SAFE version of a lookup.
 *
 * <p>Contrast with {@link com.vbforge.sql.investigation.PreparedStatementDemo#vulnerableSearchTitles}
 * — that method concatenates user input, which is exploitable.
 * Your job: implement the SAFE version.
 */
public final class Task06_SqlInjectionSafety {

    private Task06_SqlInjectionSafety() {}

    /**
     * Return all book titles EXACTLY equal to {@code title}.
     *
     * <p>The title must be treated as a LITERAL VALUE, not as SQL syntax. In particular,
     * passing {@code "x' OR '1'='1"} must return ZERO results and must NOT corrupt or drop
     * any data — because there is no book with that literal title.
     *
     * <p>Use {@link java.sql.PreparedStatement} with a {@code ?} placeholder.
     * Do NOT concatenate {@code title} into the SQL string.
     */
    public static List<String> findBookByTitleSafe(Connection conn, String title) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}