package com.vbforge.sql.practical;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task05_TransactionsTest {

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
        Task05_Transactions.transfer(conn, 1, 2, 25_000);
        assertThat(balance(conn, 1)).isEqualTo(75_000L);
        assertThat(balance(conn, 2)).isEqualTo(75_000L);
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test
    void transferInsufficientFundsRollsBack() throws SQLException {
        assertThatThrownBy(() -> Task05_Transactions.transfer(conn, 1, 2, 999_999))
                .isInstanceOf(IllegalStateException.class);
        assertThat(balance(conn, 1)).isEqualTo(100_000L);
        assertThat(balance(conn, 2)).isEqualTo(50_000L);
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test
    void transferUnknownAccountRollsBack() throws SQLException {
        assertThatThrownBy(() -> Task05_Transactions.transfer(conn, 1, 999, 100))
                .isInstanceOf(IllegalStateException.class);
        assertThat(balance(conn, 1)).isEqualTo(100_000L);
        assertThat(balance(conn, 2)).isEqualTo(50_000L);
    }

    private static long balance(Connection conn, int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT balance_cents FROM accounts WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }
}