package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcBasicsDemoTest {

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
    void countBooks() throws SQLException {
        assertThat(JdbcBasicsDemo.countBooks(conn)).isEqualTo(7L);
    }

    @Test
    void allBookTitlesOrderedById() throws SQLException {
        List<String> titles = JdbcBasicsDemo.allBookTitlesOrderedById(conn);
        assertThat(titles).hasSize(7);
        assertThat(titles.get(0)).isEqualTo("The Hobbit");
        assertThat(titles.get(3)).isEqualTo("Harry Potter and the Philosopher's Stone");
        assertThat(titles.get(6)).isEqualTo("1984");
    }

    @Test
    void totalPriceCents() throws SQLException {
        assertThat(JdbcBasicsDemo.totalPriceCents(conn)).isEqualTo(13_893L);
    }
}