package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ResultSetDemoTest {

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
    void titleByIdPresentAndMissing() throws SQLException {
        assertThat(ResultSetDemo.titleById(conn, 1)).contains("The Hobbit");
        assertThat(ResultSetDemo.titleById(conn, 999)).isEmpty();
    }

    @Test
    void sameColumnByNameAndIndex() throws SQLException {
        assertThat(ResultSetDemo.sameColumnByNameAndIndex(conn, 7)).isEqualTo("1984");
    }

    @Test
    void columnNames() throws SQLException {
        assertThat(ResultSetDemo.columnNames(conn, "SELECT * FROM books"))
                .containsExactly("id", "title", "author_id", "price_cents", "published_year");
    }

    @Test
    void readAsRows() throws SQLException {
        List<Map<String, Object>> rows = ResultSetDemo.readAsRows(
                conn, "SELECT id, title FROM books WHERE id <= 2 ORDER BY id");
        assertThat(rows).hasSize(2);
        assertThat(rows.get(0)).containsEntry("id", 1).containsEntry("title", "The Hobbit");
        assertThat(rows.get(1)).containsEntry("id", 2).containsEntry("title", "The Lord of the Rings");
    }
}