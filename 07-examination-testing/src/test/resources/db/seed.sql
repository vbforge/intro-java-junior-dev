-- Seed data. Tests reference these exact values.

INSERT INTO accounts (id, owner, balance_cents) VALUES
                                                    (1, 'Alice', 100000),
                                                    (2, 'Bob',    50000),
                                                    (3, 'Cara',   25000);

-- Note: the transactions table is intentionally EMPTY.
-- Each test creates the transactions it needs.