package com.vbforge.exam.practical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * PRACTICAL TASK 06 — SQL + JDBC + OOP + transactions.
 *
 * <p>A small banking API. Every method takes a {@link Connection} — the caller
 * owns the connection; you only borrow it.
 *
 * <p><b>Transaction discipline (required for every mutating method):</b>
 * <ol>
 *   <li>Capture the current {@code getAutoCommit()} value at entry.</li>
 *   <li>Set {@code setAutoCommit(false)}.</li>
 *   <li>Do the work with the same connection.</li>
 *   <li>On success → {@code commit()}.</li>
 *   <li>On any failure → {@code rollback()} and rethrow.</li>
 *   <li>In {@code finally} → restore the original auto-commit value.</li>
 * </ol>
 *
 * <p>All SQL must use {@link java.sql.PreparedStatement}.
 *
 * <p>Schema (see {@code src/test/resources/db/schema.sql}):
 * <pre>
 *   accounts(id INT PK, owner VARCHAR, balance_cents BIGINT)
 *   transactions(id BIGINT AUTO-INCREMENT, from_id INT NULL, to_id INT NULL,
 *                amount_cents BIGINT, kind VARCHAR, occurred_at TIMESTAMP)
 * </pre>
 *
 * <p>Seed (see {@code src/test/resources/db/seed.sql}):
 * <pre>
 *   Alice(1) — 100 000 cents
 *   Bob(2)   —  50 000 cents
 *   Cara(3)  —  25 000 cents
 *   transactions table starts empty
 * </pre>
 */
public final class Task06_BankTransactions {

    private Task06_BankTransactions() {}

    /** Immutable view of a single transaction row. */
    public record Transaction(long id, Integer fromId, Integer toId, long amountCents, String kind) {}

    // ---------------------------------------------------------------------
    // Read-only
    // ---------------------------------------------------------------------

    /**
     * Return the balance of {@code accountId} in cents.
     * @throws IllegalArgumentException if the account does not exist.
     */
    public static long balance(Connection conn, int accountId) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return up to {@code limit} transactions involving {@code accountId}
     * (either as the sender OR as the receiver). Order: most recent FIRST,
     * i.e. descending by {@code id}.
     *
     * <ul>
     *   <li>{@code limit <= 0} → empty list.</li>
     *   <li>Account with no transactions → empty list.</li>
     *   <li>Unknown accountId → empty list (not an exception — reading transactions
     *       for a non-existent id just yields no rows).</li>
     * </ul>
     *
     * <p>Mapping to {@link Transaction}:
     * <ul>
     *   <li>{@code from_id} → {@code Integer fromId} (null for deposits)</li>
     *   <li>{@code to_id}   → {@code Integer toId}   (null for withdrawals)</li>
     * </ul>
     * Use {@code rs.getObject("from_id", Integer.class)} to read a nullable INT safely.
     */
    public static List<Transaction> recentTransactions(Connection conn, int accountId, int limit)
            throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Mutations — each is ONE atomic unit
    // ---------------------------------------------------------------------

    /**
     * Deposit {@code cents} into {@code accountId}.
     * <ul>
     *   <li>Update the account's balance.</li>
     *   <li>Insert a row into {@code transactions} with
     *       {@code from_id = null}, {@code to_id = accountId},
     *       {@code amount_cents = cents}, {@code kind = "DEPOSIT"}.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if {@code cents <= 0} or the account does not exist.
     *         Both balances and the transactions table must be unchanged on failure.
     */
    public static void deposit(Connection conn, int accountId, long cents) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Withdraw {@code cents} from {@code accountId}.
     * <ul>
     *   <li>Update the account's balance.</li>
     *   <li>Insert a row into {@code transactions} with
     *       {@code from_id = accountId}, {@code to_id = null},
     *       {@code amount_cents = cents}, {@code kind = "WITHDRAW"}.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if {@code cents <= 0} or the account does not exist.
     * @throws IllegalStateException    if the account has insufficient funds.
     *         Both the balance and the transactions table must be unchanged on failure.
     */
    public static void withdraw(Connection conn, int accountId, long cents) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Transfer {@code cents} from {@code fromId} to {@code toId}.
     * <ul>
     *   <li>Debit the source, credit the destination.</li>
     *   <li>Insert a row into {@code transactions} with
     *       {@code from_id = fromId}, {@code to_id = toId},
     *       {@code amount_cents = cents}, {@code kind = "TRANSFER"}.</li>
     * </ul>
     *
     * <p>If ANY step fails (insufficient funds, unknown account, SQL error),
     * BOTH balances and the transactions table must be unchanged — rollback.
     *
     * @throws IllegalArgumentException if {@code cents <= 0}, {@code fromId == toId},
     *         or either account does not exist.
     *         A self-transfer message must contain the word {@code "self"}.
     * @throws IllegalStateException if the source has insufficient funds.
     */
    public static void transfer(Connection conn, int fromId, int toId, long cents) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}