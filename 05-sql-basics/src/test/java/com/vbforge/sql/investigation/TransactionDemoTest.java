package com.vbforge.sql.investigation;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TransactionDemoTest {

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
    void transferSucceeds() throws SQLException {
        TransactionDemo.transfer(conn, 1, 2, 25_000);
        assertThat(TransactionDemo.balanceCents(conn, 1)).isEqualTo(75_000L);
        assertThat(TransactionDemo.balanceCents(conn, 2)).isEqualTo(75_000L);
        // auto-commit restored
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test
    void transferRollsBackOnInsufficientFunds() throws SQLException {
        assertThatThrownBy(() -> TransactionDemo.transfer(conn, 1, 2, 999_999))
                .isInstanceOf(IllegalStateException.class);
        assertThat(TransactionDemo.balanceCents(conn, 1)).isEqualTo(100_000L);
        assertThat(TransactionDemo.balanceCents(conn, 2)).isEqualTo(50_000L);
        assertThat(conn.getAutoCommit()).isTrue();
    }
}