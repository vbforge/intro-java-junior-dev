-- Schema for the 05-sql-basics module.
-- Every query in investigation/ and practical/ targets these tables.

CREATE TABLE authors (
                         id   INT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

CREATE TABLE books (
                       id             INT PRIMARY KEY,
                       title          VARCHAR(200) NOT NULL,
                       author_id      INT NOT NULL,
                       price_cents    INT NOT NULL,
                       published_year INT NOT NULL,
                       CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE accounts (
                          id            INT PRIMARY KEY,
                          owner         VARCHAR(100) NOT NULL,
                          balance_cents BIGINT NOT NULL
)