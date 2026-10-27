package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class AggregateQueriesDemoTest {

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
    void averagePriceOnSeededData() throws SQLException {
        double avg = AggregateQueriesDemo.averagePriceCents(conn).getAsDouble();
        assertThat(avg).isCloseTo(13_893.0 / 7.0, within(0.01));
    }

    @Test
    void averagePriceOnEmptyBooks() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DELETE FROM books");
        }
        assertThat(AggregateQueriesDemo.averagePriceCents(conn)).isEmpty();
    }

    @Test
    void bookCountByAuthorName() throws SQLException {
        Map<String, Integer> counts = AggregateQueriesDemo.bookCountByAuthorName(conn);
        assertThat(counts)
                .hasSize(4)
                .containsEntry("Tolkien", 3)
                .containsEntry("Rowling", 2)
                .containsEntry("Martin", 1)
                .containsEntry("Orwell", 1)
                .doesNotContainKey("Herbert");
    }

    @Test
    void authorsWithMoreThanOneBook() throws SQLException {
        assertThat(AggregateQueriesDemo.authorsWithMoreThanOneBook(conn))
                .containsExactly("Tolkien", "Rowling");
    }
}