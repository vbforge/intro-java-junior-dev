package com.vbforge.sql.practical;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task06_SqlInjectionSafetyTest {

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
    void findsExactTitle() throws SQLException {
        assertThat(Task06_SqlInjectionSafety.findBookByTitleSafe(conn, "1984"))
                .containsExactly("1984");
        assertThat(Task06_SqlInjectionSafety.findBookByTitleSafe(conn, "No Such Title")).isEmpty();
    }

    @Test
    void classicInjectionReturnsNothing() throws SQLException {
        // "' OR '1'='1" as a LITERAL title — should match nothing
        assertThat(Task06_SqlInjectionSafety.findBookByTitleSafe(conn, "x' OR '1'='1")).isEmpty();
    }

    @Test
    void destructiveInjectionDoesNotDropTable() throws SQLException {
        assertThat(Task06_SqlInjectionSafety.findBookByTitleSafe(conn, "x'; DROP TABLE books; --"))
                .isEmpty();
        // books table must still exist with the original 7 rows
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM books")) {
            rs.next();
            assertThat(rs.getInt(1)).isEqualTo(7);
        }
    }
}