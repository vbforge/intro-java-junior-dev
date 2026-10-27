package com.vbforge.sql.practical;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task03_JoinQueriesTest {

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
    void titleSlashAuthor() throws SQLException {
        assertThat(Task03_JoinQueries.titleSlashAuthor(conn)).hasSize(7);
        assertThat(Task03_JoinQueries.titleSlashAuthor(conn).get(0))
                .isEqualTo("The Hobbit / Tolkien");
    }

    @Test
    void authorsWithNoBooks() throws SQLException {
        assertThat(Task03_JoinQueries.authorsWithNoBooks(conn)).containsExactly("Herbert");
    }

    @Test
    void titlesByAuthor() throws SQLException {
        assertThat(Task03_JoinQueries.titlesByAuthor(conn, "Orwell"))
                .containsExactly("1984");
        assertThat(Task03_JoinQueries.titlesByAuthor(conn, "Herbert")).isEmpty();
        assertThat(Task03_JoinQueries.titlesByAuthor(conn, "Unknown")).isEmpty();
    }
}