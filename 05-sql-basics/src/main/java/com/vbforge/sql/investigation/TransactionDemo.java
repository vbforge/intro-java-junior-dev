package com.vbforge.sql.investigation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * INVESTIGATION — transactions and rollback.
 *
 * <p>Pattern for a "unit of work" in JDBC:
 * <ol>
 *   <li>{@code conn.setAutoCommit(false)} — begin.</li>
 *   <li>Do the work with the same {@code conn}.</li>
 *   <li>{@code conn.commit()} on success, {@code conn.rollback()} on failure.</li>
 *   <li>{@code conn.setAutoCommit(true)} in a {@code finally} — restore for the caller.</li>
 * </ol>
 */
public final class TransactionDemo {

    private TransactionDemo() {}

    /**
     * Transfer {@code cents} from {@code fromId} to {@code toId}.
     *
     * <p>If the FROM account has insufficient funds, throws {@link IllegalStateException}
     * and rolls back — NEITHER balance changes. On success, both debits and credits apply atomically.
     *
     * <p>Note that the method RESTORES auto-commit on the connection before returning
     * or throwing — otherwise the caller would be left in a half-managed transaction state.
     */
    public static void transfer(Connection conn, int fromId, int toId, long cents) throws SQLException {
        boolean originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            long fromBalance = balanceCents(conn, fromId);
            if (fromBalance < cents) {
                throw new IllegalStateException(
                        "Insufficient funds: " + fromBalance + " < " + cents);
            }
            updateBalance(conn, fromId, fromBalance - cents);
            updateBalance(conn, toId,   balanceCents(conn, toId) + cents);
            conn.commit();
        } catch (RuntimeException | SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(originalAutoCommit);
        }
    }

    /** Read the current balance. Uses a PreparedStatement. */
    public static long balanceCents(Connection conn, int accountId) throws SQLException {
        String sql = "SELECT balance_cents FROM accounts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalStateException("No account with id " + accountId);
                }
                return rs.getLong("balance_cents");
            }
        }
    }

    private static void updateBalance(Connection conn, int accountId, long newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance_cents = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, newBalance);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }
}