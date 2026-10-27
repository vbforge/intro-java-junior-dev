package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 01 — Encapsulation.
 *
 * <p>Implement a bank account with STRICT encapsulation:
 * <ul>
 *   <li>{@code owner} and {@code balanceCents} are private final/private.</li>
 *   <li>No public setters.</li>
 *   <li>Every mutation is validated.</li>
 * </ul>
 *
 * <p>Amounts are stored as {@code long} cents — never as {@code double} for money.
 */
public final class Task01_BankAccount {

    // TODO: declare fields
    //   - private final String owner
    //   - private long balanceCents

    /**
     * @throws IllegalArgumentException if {@code owner} is null/blank or
     *         {@code initialBalanceCents < 0}.
     */
    public Task01_BankAccount(String owner, long initialBalanceCents) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    public String owner() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    public long balanceCents() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * @throws IllegalArgumentException if {@code cents <= 0}.
     */
    public void deposit(long cents) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * @throws IllegalArgumentException if {@code cents <= 0}.
     * @throws IllegalStateException    if {@code cents > balanceCents}.
     */
    public void withdraw(long cents) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Transfer money to another account.
     * @throws IllegalArgumentException if {@code cents <= 0} or {@code target == this}
     *         (message must contain "self").
     * @throws IllegalStateException    if insufficient funds.
     * @throws IllegalArgumentException if {@code target == null}.
     *
     * <p>On any exception, BOTH balances must be unchanged (atomicity).
     */
    public void transferTo(Task01_BankAccount target, long cents) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}