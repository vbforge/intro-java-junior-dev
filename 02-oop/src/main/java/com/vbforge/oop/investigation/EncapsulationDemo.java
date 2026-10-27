package com.vbforge.oop.investigation;

/**
 * INVESTIGATION — Encapsulation.
 *
 * <p>Encapsulation means: hide internal state (private fields) and expose only the
 * behavior you want (public methods). Every method that mutates state can validate.
 */
public final class EncapsulationDemo {

    private EncapsulationDemo() {}

    /**
     * A well-encapsulated bank account.
     * <ul>
     *   <li>Fields are {@code private final} where possible.</li>
     *   <li>Every mutation goes through a method that validates.</li>
     *   <li>There is NO public setter — the balance can only change via {@code deposit}/{@code withdraw}.</li>
     * </ul>
     */
    public static class BankAccount {
        private final String owner;
        private long balanceCents;                      // store money in cents — never use double for money

        public BankAccount(String owner, long initialBalanceCents) {
            if (owner == null || owner.isBlank()) {
                throw new IllegalArgumentException("owner must not be blank");
            }
            if (initialBalanceCents < 0) {
                throw new IllegalArgumentException("initial balance must be >= 0");
            }
            this.owner = owner;
            this.balanceCents = initialBalanceCents;
        }

        public String owner()          { return owner; }
        public long balanceCents()     { return balanceCents; }

        public void deposit(long cents) {
            if (cents <= 0) throw new IllegalArgumentException("deposit must be > 0");
            balanceCents += cents;
        }

        public void withdraw(long cents) {
            if (cents <= 0)                throw new IllegalArgumentException("withdraw must be > 0");
            if (cents > balanceCents)      throw new IllegalStateException("insufficient funds");
            balanceCents -= cents;
        }
    }

    public static long depositThenWithdraw() {
        BankAccount acc = new BankAccount("Alice", 1000);
        acc.deposit(500);
        acc.withdraw(300);
        return acc.balanceCents();                      // 1200
    }

    public static String tryInvalidDeposit() {
        try {
            new BankAccount("Bob", 100).deposit(-50);
            return "no exception";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // ---------------------------------------------------------------------
    // Immutability is the strongest form of encapsulation.
    // ---------------------------------------------------------------------

    /**
     * An immutable value object. No setters, all fields {@code final},
     * no way for the outside world to observe a partially-constructed object.
     */
    public static final class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
            if (age < 0 || age > 150)           throw new IllegalArgumentException("age");
            this.name = name;
            this.age  = age;
        }

        public String name() { return name; }
        public int age()     { return age; }

        /** Returns a NEW Person — the original is never mutated. */
        public Person withAge(int newAge) { return new Person(name, newAge); }
    }

    public static int[] immutabilityDemo() {
        Person p1 = new Person("Eve", 20);
        Person p2 = p1.withAge(21);
        return new int[]{ p1.age(), p2.age() };         // {20, 21}
    }
}