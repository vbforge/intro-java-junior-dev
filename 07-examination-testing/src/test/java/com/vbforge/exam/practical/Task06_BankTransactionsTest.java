package com.vbforge.exam.practical;

import com.vbforge.exam.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.vbforge.exam.practical.Task06_BankTransactions.Transaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task06_BankTransactionsTest {

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

    // ---------------------------------------------------------------------
    // balance
    // ---------------------------------------------------------------------

    @Test void balanceOfExistingAccounts() throws SQLException {
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
        assertThat(Task06_BankTransactions.balance(conn, 2)).isEqualTo(50_000L);
        assertThat(Task06_BankTransactions.balance(conn, 3)).isEqualTo(25_000L);
    }

    @Test void balanceOfUnknownAccountThrows() {
        assertThatThrownBy(() -> Task06_BankTransactions.balance(conn, 999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ---------------------------------------------------------------------
    // deposit
    // ---------------------------------------------------------------------

    @Test void depositIncreasesBalance() throws SQLException {
        Task06_BankTransactions.deposit(conn, 1, 25_000);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(125_000L);
    }

    @Test void depositLogsTransaction() throws SQLException {
        Task06_BankTransactions.deposit(conn, 1, 25_000);
        List<Transaction> txs = Task06_BankTransactions.recentTransactions(conn, 1, 10);
        assertThat(txs).hasSize(1);
        Transaction tx = txs.get(0);
        assertThat(tx.fromId()).isNull();
        assertThat(tx.toId()).isEqualTo(1);
        assertThat(tx.amountCents()).isEqualTo(25_000L);
        assertThat(tx.kind()).isEqualTo("DEPOSIT");
    }

    @Test void depositRejectsNonPositive() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.deposit(conn, 1, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task06_BankTransactions.deposit(conn, 1, -100))
                .isInstanceOf(IllegalArgumentException.class);
        // balance unchanged, no transaction logged
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, 10)).isEmpty();
    }

    @Test void depositToUnknownAccountThrows() {
        assertThatThrownBy(() -> Task06_BankTransactions.deposit(conn, 999, 100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void depositRestoresAutoCommitOnSuccess() throws SQLException {
        Task06_BankTransactions.deposit(conn, 1, 100);
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test void depositRestoresAutoCommitOnFailure() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.deposit(conn, 1, -1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(conn.getAutoCommit()).isTrue();
    }

    // ---------------------------------------------------------------------
    // withdraw
    // ---------------------------------------------------------------------

    @Test void withdrawDecreasesBalance() throws SQLException {
        Task06_BankTransactions.withdraw(conn, 1, 30_000);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(70_000L);
    }

    @Test void withdrawLogsTransaction() throws SQLException {
        Task06_BankTransactions.withdraw(conn, 1, 30_000);
        List<Transaction> txs = Task06_BankTransactions.recentTransactions(conn, 1, 10);
        assertThat(txs).hasSize(1);
        Transaction tx = txs.get(0);
        assertThat(tx.fromId()).isEqualTo(1);
        assertThat(tx.toId()).isNull();
        assertThat(tx.amountCents()).isEqualTo(30_000L);
        assertThat(tx.kind()).isEqualTo("WITHDRAW");
    }

    @Test void withdrawInsufficientFundsRollsBack() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.withdraw(conn, 3, 999_999))
                .isInstanceOf(IllegalStateException.class);
        assertThat(Task06_BankTransactions.balance(conn, 3)).isEqualTo(25_000L);
        assertThat(Task06_BankTransactions.recentTransactions(conn, 3, 10)).isEmpty();
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test void withdrawRejectsNonPositive() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.withdraw(conn, 1, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
    }

    // ---------------------------------------------------------------------
    // transfer
    // ---------------------------------------------------------------------

    @Test void transferMovesMoneyAndLogsTransaction() throws SQLException {
        Task06_BankTransactions.transfer(conn, 1, 2, 25_000);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(75_000L);
        assertThat(Task06_BankTransactions.balance(conn, 2)).isEqualTo(75_000L);

        List<Transaction> txs = Task06_BankTransactions.recentTransactions(conn, 1, 10);
        assertThat(txs).hasSize(1);
        Transaction tx = txs.get(0);
        assertThat(tx.fromId()).isEqualTo(1);
        assertThat(tx.toId()).isEqualTo(2);
        assertThat(tx.amountCents()).isEqualTo(25_000L);
        assertThat(tx.kind()).isEqualTo("TRANSFER");
    }

    @Test void transferInsufficientFundsRollsBackAtomically() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.transfer(conn, 3, 1, 999_999))
                .isInstanceOf(IllegalStateException.class);
        assertThat(Task06_BankTransactions.balance(conn, 3)).isEqualTo(25_000L);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, 10)).isEmpty();
        assertThat(Task06_BankTransactions.recentTransactions(conn, 3, 10)).isEmpty();
        assertThat(conn.getAutoCommit()).isTrue();
    }

    @Test void transferToUnknownAccountRollsBack() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.transfer(conn, 1, 999, 100))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, 10)).isEmpty();
    }

    @Test void transferToSelfRejected() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.transfer(conn, 1, 1, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("self");
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
    }

    @Test void transferRejectsNonPositive() throws SQLException {
        assertThatThrownBy(() -> Task06_BankTransactions.transfer(conn, 1, 2, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(Task06_BankTransactions.balance(conn, 1)).isEqualTo(100_000L);
        assertThat(Task06_BankTransactions.balance(conn, 2)).isEqualTo(50_000L);
    }

    // ---------------------------------------------------------------------
    // recentTransactions
    // ---------------------------------------------------------------------

    @Test void recentTransactionsReturnsMostRecentFirst() throws SQLException {
        Task06_BankTransactions.deposit(conn, 1, 100);
        Task06_BankTransactions.withdraw(conn, 1, 200);
        Task06_BankTransactions.deposit(conn, 1, 300);

        List<Transaction> txs = Task06_BankTransactions.recentTransactions(conn, 1, 10);
        assertThat(txs).hasSize(3);
        assertThat(txs.get(0).amountCents()).isEqualTo(300L);
        assertThat(txs.get(0).kind()).isEqualTo("DEPOSIT");
        assertThat(txs.get(1).amountCents()).isEqualTo(200L);
        assertThat(txs.get(1).kind()).isEqualTo("WITHDRAW");
        assertThat(txs.get(2).amountCents()).isEqualTo(100L);
        assertThat(txs.get(2).kind()).isEqualTo("DEPOSIT");
    }

    @Test void recentTransactionsRespectsLimit() throws SQLException {
        for (int i = 1; i <= 5; i++) {
            Task06_BankTransactions.deposit(conn, 1, i * 100);
        }
        List<Transaction> txs = Task06_BankTransactions.recentTransactions(conn, 1, 2);
        assertThat(txs).hasSize(2);
        assertThat(txs.get(0).amountCents()).isEqualTo(500L);
        assertThat(txs.get(1).amountCents()).isEqualTo(400L);
    }

    @Test void recentTransactionsIncludesBothSidesOfTransfer() throws SQLException {
        Task06_BankTransactions.transfer(conn, 1, 2, 500);

        // Alice (sender) should see it
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, 10)).hasSize(1);
        // Bob (receiver) should see it too
        assertThat(Task06_BankTransactions.recentTransactions(conn, 2, 10)).hasSize(1);
        // Cara (neither side) should NOT see it
        assertThat(Task06_BankTransactions.recentTransactions(conn, 3, 10)).isEmpty();
    }

    @Test void recentTransactionsEdgeCases() throws SQLException {
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, 0)).isEmpty();
        assertThat(Task06_BankTransactions.recentTransactions(conn, 1, -1)).isEmpty();
        assertThat(Task06_BankTransactions.recentTransactions(conn, 999, 10)).isEmpty();
    }

    // ---------------------------------------------------------------------
    // Sanity check
    // ---------------------------------------------------------------------

    @Test void transactionsTableStillExists() throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM transactions")) {
            rs.next();
            assertThat(rs.getInt(1)).isZero();
        }
    }
}