package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EncapsulationDemoTest {

    @Test void depositThenWithdrawKeepsBalanceConsistent() {
        assertThat(EncapsulationDemo.depositThenWithdraw()).isEqualTo(1200);
    }

    @Test void invalidDepositIsRejectedWithMessage() {
        assertThat(EncapsulationDemo.tryInvalidDeposit()).contains("deposit");
    }

    @Test void immutabilityMeansNewInstance() {
        assertThat(EncapsulationDemo.immutabilityDemo()).containsExactly(20, 21);
    }
}