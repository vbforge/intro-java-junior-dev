package com.vbforge.sql.practical;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Disabled
class Task04_AggregatesTest {

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
    void averagePriceCents() throws SQLException {
        double avg = Task04_Aggregates.averagePriceCents(conn).getAsDouble();
        assertThat(avg).isCloseTo(13_893.0 / 7.0, within(0.01));
    }

    @Test
    void averagePriceCentsEmpty() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DELETE FROM books");
        }
        assertThat(Task04_Aggregates.averagePriceCents(conn)).isEmpty();
    }

    @Test
    void bookCountByAuthorName() throws SQLException {
        Map<String, Integer> counts = Task04_Aggregates.bookCountByAuthorName(conn);
        assertThat(counts).hasSize(4)
                .containsEntry("Tolkien", 3)
                .containsEntry("Rowling", 2)
                .containsEntry("Martin", 1)
                .containsEntry("Orwell", 1)
                .doesNotContainKey("Herbert");
        // LinkedHashMap ordering — name ascending
        assertThat(counts.keySet()).containsExactly("Martin", "Orwell", "Rowling", "Tolkien");
    }

    @Test
    void authorsWithMoreThan() throws SQLException {
        assertThat(Task04_Aggregates.authorsWithMoreThan(conn, 1))
                .containsExactly("Tolkien", "Rowling");
        assertThat(Task04_Aggregates.authorsWithMoreThan(conn, 2))
                .containsExactly("Tolkien");
        assertThat(Task04_Aggregates.authorsWithMoreThan(conn, 99)).isEmpty();
    }
}