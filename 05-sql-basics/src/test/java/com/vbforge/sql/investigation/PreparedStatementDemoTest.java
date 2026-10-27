package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PreparedStatementDemoTest {

    private TestDatabase db;
    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        db = TestDatabase.create();
        conn = db.connection();
    }

    @AfterEach
    void tearDown() throws SQLException {
        db.close();
    }

    @Test
    void findTitlesContaining() throws SQLException {
        List<String> hits = PreparedStatementDemo.findTitlesContaining(conn, "harry");
        assertThat(hits).hasSize(2)
                .allMatch(t -> t.toLowerCase().contains("harry"));
    }

    @Test
    void findTitlesContainingNoHits() throws SQLException {
        assertThat(PreparedStatementDemo.findTitlesContaining(conn, "zzz")).isEmpty();
    }

    @Test
    void insertAuthorAddsRow() throws SQLException {
        long before = JdbcBasicsDemo.countBooks(conn);        // unrelated — just to prove conn works
        assertThat(before).isEqualTo(7L);

        PreparedStatementDemo.insertAuthor(conn, 10, "Herbert");

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM authors")) {
            rs.next();
            assertThat(rs.getInt(1)).isEqualTo(6);            // 5 seed + 1 new
        }
    }

    @Test
    void insertAuthorsBatch() throws SQLException {
        PreparedStatementDemo.insertAuthors(conn, List.of(
                new PreparedStatementDemo.Author(11, "Asimov"),
                new PreparedStatementDemo.Author(12, "Bradbury"),
                new PreparedStatementDemo.Author(13, "Le Guin")
        ));
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM authors")) {
            rs.next();
            assertThat(rs.getInt(1)).isEqualTo(8);            // 5 seed + 3 new
        }
    }

    @Test
    void vulnerableVersionIsInjectable() throws SQLException {
        // the classic "' OR '1'='1" injection — vulnerable version returns EVERY row
        List<String> hits = PreparedStatementDemo.vulnerableSearchTitles(conn, "x' OR '1'='1");
        assertThat(hits).hasSize(7);
    }

    @Test
    void safeVersionIsImmune() throws SQLException {
        // the same malicious input — safe version treats it as a literal, returns zero rows
        List<String> hits = PreparedStatementDemo.safeSearchTitles(conn, "x' OR '1'='1");
        assertThat(hits).isEmpty();
    }

    @Test
    void safeVersionStillFindsRealTitles() throws SQLException {
        assertThat(PreparedStatementDemo.safeSearchTitles(conn, "1984")).containsExactly("1984");
    }
}