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
class Task02_FilterAndSortTest {

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
    void booksCheaperThan() throws SQLException {
        // prices below 1700: 1299, 1599, 1699
        assertThat(Task02_FilterAndSort.booksCheaperThan(conn, 1700))
                .containsExactly(
                        "1984",
                        "Harry Potter and the Philosopher's Stone",
                        "Harry Potter and the Chamber of Secrets");
    }

    @Test
    void booksCheaperThanEdgeCases() throws SQLException {
        assertThat(Task02_FilterAndSort.booksCheaperThan(conn, 0)).isEmpty();
        assertThat(Task02_FilterAndSort.booksCheaperThan(conn, 99999)).hasSize(7);
    }

    @Test
    void topNMostExpensive() throws SQLException {
        assertThat(Task02_FilterAndSort.topNMostExpensive(conn, 3))
                .containsExactly(
                        "The Lord of the Rings",
                        "The Silmarillion",
                        "The Hobbit");
    }

    @Test
    void topNMostExpensiveEdgeCases() throws SQLException {
        assertThat(Task02_FilterAndSort.topNMostExpensive(conn, 0)).isEmpty();
        assertThat(Task02_FilterAndSort.topNMostExpensive(conn, -1)).isEmpty();
        assertThat(Task02_FilterAndSort.topNMostExpensive(conn, 100)).hasSize(7);
    }

    @Test
    void booksInYearRange() throws SQLException {
        // 1950..2000 → LotR (1954), Silmarillion (1977), HP1 (1997), HP2 (1998), AGoT (1996)
        assertThat(Task02_FilterAndSort.booksInYearRange(conn, 1950, 2000))
                .containsExactly(
                        "The Lord of the Rings",
                        "The Silmarillion",
                        "A Game of Thrones",
                        "Harry Potter and the Philosopher's Stone",
                        "Harry Potter and the Chamber of Secrets");
    }

    @Test
    void booksInYearRangeInvertedRange() throws SQLException {
        assertThat(Task02_FilterAndSort.booksInYearRange(conn, 2000, 1900)).isEmpty();
    }
}