package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JoinQueriesDemoTest {

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
    void titleAndAuthorPairs() throws SQLException {
        List<String> pairs = JoinQueriesDemo.titleAndAuthorPairs(conn);
        assertThat(pairs).hasSize(7);
        assertThat(pairs.get(0)).isEqualTo("The Hobbit by Tolkien");
        assertThat(pairs.get(6)).isEqualTo("1984 by Orwell");
    }

    @Test
    void authorsWithNoBooks() throws SQLException {
        assertThat(JoinQueriesDemo.authorsWithNoBooks(conn)).containsExactly("Herbert");
    }

    @Test
    void titlesByAuthorName() throws SQLException {
        assertThat(JoinQueriesDemo.titlesByAuthorName(conn, "Rowling"))
                .containsExactly(
                        "Harry Potter and the Chamber of Secrets",
                        "Harry Potter and the Philosopher's Stone");
        assertThat(JoinQueriesDemo.titlesByAuthorName(conn, "Herbert")).isEmpty();
    }
}