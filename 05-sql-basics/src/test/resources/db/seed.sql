-- Seed data. The exact values here are referenced by tests — do NOT change them.

INSERT INTO authors (id, name) VALUES
                                   (1, 'Tolkien'),
                                   (2, 'Rowling'),
                                   (3, 'Martin'),
                                   (4, 'Orwell'),
                                   (5, 'Herbert');       -- no books — used by the LEFT JOIN demo

INSERT INTO books (id, title, author_id, price_cents, published_year) VALUES
                                                                          (1, 'The Hobbit',                                     1, 1999, 1937),
                                                                          (2, 'The Lord of the Rings',                          1, 2999, 1954),
                                                                          (3, 'The Silmarillion',                               1, 2499, 1977),
                                                                          (4, 'Harry Potter and the Philosopher''s Stone',      2, 1599, 1997),
                                                                          (5, 'Harry Potter and the Chamber of Secrets',        2, 1699, 1998),
                                                                          (6, 'A Game of Thrones',                              3, 1799, 1996),
                                                                          (7, '1984',                                           4, 1299, 1949);

INSERT INTO accounts (id, owner, balance_cents) VALUES
                                                    (1, 'Alice', 100000),
                                                    (2, 'Bob',    50000);