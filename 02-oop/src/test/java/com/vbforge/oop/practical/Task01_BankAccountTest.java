package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task01_BankAccountTest {

    @Test void constructorValidation() {
        assertThatThrownBy(() -> new Task01_BankAccount(null, 100))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Task01_BankAccount("", 100))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Task01_BankAccount("Alice", -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void depositAndWithdraw() {
        Task01_BankAccount acc = new Task01_BankAccount("Alice", 1000);
        acc.deposit(500);
        acc.withdraw(300);
        assertThat(acc.balanceCents()).isEqualTo(1200);
        assertThat(acc.owner()).isEqualTo("Alice");
    }

    @Test void depositRejectsNonPositive() {
        Task01_BankAccount acc = new Task01_BankAccount("Alice", 100);
        assertThatThrownBy(() -> acc.deposit(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> acc.deposit(-5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test void withdrawRejectsOverdraftAndNonPositive() {
        Task01_BankAccount acc = new Task01_BankAccount("Alice", 100);
        assertThatThrownBy(() -> acc.withdraw(200)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> acc.withdraw(0)).isInstanceOf(IllegalArgumentException.class);
        assertThat(acc.balanceCents()).isEqualTo(100);   // unchanged
    }

    @Test void transferHappyPath() {
        Task01_BankAccount a = new Task01_BankAccount("A", 1000);
        Task01_BankAccount b = new Task01_BankAccount("B", 0);
        a.transferTo(b, 400);
        assertThat(a.balanceCents()).isEqualTo(600);
        assertThat(b.balanceCents()).isEqualTo(400);
    }

    @Test void transferToSelfRejected() {
        Task01_BankAccount a = new Task01_BankAccount("A", 1000);
        assertThatThrownBy(() -> a.transferTo(a, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("self");
        assertThat(a.balanceCents()).isEqualTo(1000);
    }

    @Test void transferInsufficientFundsIsAtomic() {
        Task01_BankAccount a = new Task01_BankAccount("A", 100);
        Task01_BankAccount b = new Task01_BankAccount("B", 0);
        assertThatThrownBy(() -> a.transferTo(b, 500))
                .isInstanceOf(IllegalStateException.class);
        assertThat(a.balanceCents()).isEqualTo(100);     // unchanged
        assertThat(b.balanceCents()).isEqualTo(0);       // unchanged
    }

    @Test void transferToNullRejected() {
        Task01_BankAccount a = new Task01_BankAccount("A", 100);
        assertThatThrownBy(() -> a.transferTo(null, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}