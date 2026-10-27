package com.vbforge.oop.practical;

/**
 * PRACTICAL TASK 03 — Interfaces + default methods + polymorphism.
 *
 * <p>This is the interface. Implement the two nested gateway classes and
 * a small static factory/helper.
 */
public interface Task03_PaymentGateway {

    /** Short provider name, e.g. {@code "Stripe"}, {@code "Cash"}. */
    String providerName();

    /** Charge the given amount. Return {@code true} on success. */
    boolean charge(double amount);

    /**
     * Refund. The DEFAULT returns {@code false} (refunds not supported).
     * Override when a provider supports refunds.
     */
    default boolean refund(double amount) {
        return false;
    }

    /** A provider string suitable for a receipt. Override if you want a different format. */
    default String receiptLabel() {
        return "Paid via " + providerName();
    }

    // ---------------------------------------------------------------------
    // Concrete providers
    // ---------------------------------------------------------------------

    /**
     * Charges if {@code 0 < amount <= 10000}. Supports refunds (returns true iff amount > 0).
     * Provider name: {@code "Stripe"}. Receipt label: default.
     */
    final class StripeGateway implements Task03_PaymentGateway {
        @Override public String providerName() { throw new UnsupportedOperationException("TODO"); }
        @Override public boolean charge(double amount) { throw new UnsupportedOperationException("TODO"); }
        @Override public boolean refund(double amount) { throw new UnsupportedOperationException("TODO"); }
    }

    /**
     * Charges if {@code 0 < amount <= 1000}. Does NOT support refunds
     * (i.e., uses the default refund). Provider name: {@code "Cash"}.
     * Override {@code receiptLabel()} to return {@code "Cash payment"}.
     */
    final class CashGateway implements Task03_PaymentGateway {
        @Override public String providerName() { throw new UnsupportedOperationException("TODO"); }
        @Override public boolean charge(double amount) { throw new UnsupportedOperationException("TODO"); }
        @Override public String receiptLabel() { throw new UnsupportedOperationException("TODO"); }
    }

    // ---------------------------------------------------------------------
    // Polymorphic helpers
    // ---------------------------------------------------------------------

    /**
     * Return the sum of amounts charged by each gateway via {@link #charge(double)},
     * where each gateway tries to charge the SAME {@code amount}.
     * Successful charge → add the amount to the total; failure → skip.
     */
    static double chargeAll(Task03_PaymentGateway[] gateways, double amount) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the provider names, each joined by {@code ", "}.
     * Null or empty → empty string.
     */
    static String providerNames(Task03_PaymentGateway[] gateways) {
        throw new UnsupportedOperationException("TODO: implement");
    }
}