package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task03_PaymentGatewayTest {

    @Test void stripeChargesWithinLimit() {
        Task03_PaymentGateway gw = new Task03_PaymentGateway.StripeGateway();
        assertThat(gw.providerName()).isEqualTo("Stripe");
        assertThat(gw.charge(100)).isTrue();
        assertThat(gw.charge(0)).isFalse();
        assertThat(gw.charge(-5)).isFalse();
        assertThat(gw.charge(20_000)).isFalse();
        assertThat(gw.receiptLabel()).isEqualTo("Paid via Stripe");
    }

    @Test void stripeSupportsRefunds() {
        Task03_PaymentGateway gw = new Task03_PaymentGateway.StripeGateway();
        assertThat(gw.refund(10)).isTrue();
        assertThat(gw.refund(0)).isFalse();
        assertThat(gw.refund(-1)).isFalse();
    }

    @Test void cashUsesDefaultRefundAndCustomLabel() {
        Task03_PaymentGateway gw = new Task03_PaymentGateway.CashGateway();
        assertThat(gw.providerName()).isEqualTo("Cash");
        assertThat(gw.charge(100)).isTrue();
        assertThat(gw.charge(5000)).isFalse();
        assertThat(gw.refund(10)).isFalse();                // default
        assertThat(gw.receiptLabel()).isEqualTo("Cash payment");
    }

    @Test void chargeAllPolymorphic() {
        Task03_PaymentGateway[] gws = {
                new Task03_PaymentGateway.StripeGateway(),
                new Task03_PaymentGateway.CashGateway()
        };
        // Both charge 100 successfully → total 200
        assertThat(Task03_PaymentGateway.chargeAll(gws, 100)).isEqualTo(200);
        // Only Stripe can charge 5000 → total 5000
        assertThat(Task03_PaymentGateway.chargeAll(gws, 5000)).isEqualTo(5000);
        // Neither can charge 20000 → total 0
        assertThat(Task03_PaymentGateway.chargeAll(gws, 20000)).isZero();
    }

    @Test void providerNamesJoined() {
        Task03_PaymentGateway[] gws = {
                new Task03_PaymentGateway.StripeGateway(),
                new Task03_PaymentGateway.CashGateway()
        };
        assertThat(Task03_PaymentGateway.providerNames(gws)).isEqualTo("Stripe, Cash");
        assertThat(Task03_PaymentGateway.providerNames(new Task03_PaymentGateway[0])).isEmpty();
    }
}