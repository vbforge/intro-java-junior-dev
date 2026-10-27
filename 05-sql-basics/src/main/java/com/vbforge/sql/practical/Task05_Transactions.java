package com.vbforge.sql.practical;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * PRACTICAL TASK 05 — transactions.
 */
public final class Task05_Transactions {

    private Task05_Transactions() {}

    /**
     * Transfer {@code cents} from {@code fromId} to {@code toId} atomically.
     *
     * <p>Requirements:
     * <ul>
     *   <li>Either BOTH balances change, or NEITHER does.</li>
     *   <li>If the FROM account has insufficient funds, throw {@link IllegalStateException}
     *       and roll back.</li>
     *   <li>If either account does not exist, throw {@link IllegalStateException} and roll back.</li>
     *   <li>Restore the connection's AUTO-COMMIT state to what it was before the call
     *       (in a {@code finally}).</li>
     * </ul>
     *
     * <p>You may reuse {@link com.vbforge.sql.investigation.TransactionDemo#transfer}
     * as a reference for the pattern — the practical method is a fresh implementation.
     */
    public static void transfer(Connection conn, int fromId, int toId, long cents) throws SQLException {
        throw new UnsupportedOperationException("TODO: implement");
    }
}