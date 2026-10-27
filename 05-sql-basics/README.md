# 05 — SQL Basics (with embedded H2 + JDBC)

SQL fundamentals exercised through JDBC on an embedded H2 in-memory database. No database server to install — `mvn test` downloads H2 and runs everything in-process.

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `JdbcBasicsDemo` | DriverManager, Connection, Statement, ResultSet, `try-with-resources`, one scalar / one list result |
| `PreparedStatementDemo` | `?` placeholders, string/date parameters, batch inserts, **SQL injection side-by-side** |
| `ResultSetDemo` | `getInt`/`getString`, by-name vs by-index, `ResultSetMetaData` for column names |
| `JoinQueriesDemo` | INNER JOIN, LEFT JOIN (`IS NULL` for "rows without a match") |
| `AggregateQueriesDemo` | `COUNT`/`SUM`/`AVG`/`MIN`/`MAX`, `GROUP BY`, `HAVING`, empty-result aggregates |
| `TransactionDemo` | `setAutoCommit(false)`, `commit` on success, `rollback` on failure |

### Test database — `src/test/resources/db/`

The schema and seed data live in `schema.sql` and `seed.sql`. **Read them first** — every query in this module targets those tables.

Tables:
- `authors(id INT PK, name VARCHAR)`
- `books(id INT PK, title VARCHAR, author_id INT FK, price_cents INT, published_year INT)`
- `accounts(id INT PK, owner VARCHAR, balance_cents BIGINT)`

Seed highlights:
- Authors: Tolkien (3 books), Rowling (2), Martin (1), Orwell (1), **Herbert (0 books — for the LEFT JOIN demo)**
- Accounts: Alice (100 000 cents), Bob (50 000 cents)

### `THEORY_EXAM.md`

25 questions. Fill in yourself.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_BasicCrud` | INSERT / UPDATE / DELETE / SELECT with `PreparedStatement` |
| `Task02_FilterAndSort` | `WHERE`, `ORDER BY`, `LIMIT` |
| `Task03_JoinQueries` | INNER JOIN + WHERE, LEFT JOIN for "no match" |
| `Task04_Aggregates` | `COUNT`/`AVG`, `GROUP BY`, `HAVING`, empty-result handling |
| `Task05_Transactions` | atomic transfer, rollback on insufficient funds |
| `Task06_SqlInjectionSafety` | write the SAFE `PreparedStatement` version |

Run practical only:

```bash
mvn -pl 05-sql-basics test -Dtest='com.vbforge.sql.practical.*Test'
```

When everything is green → you're done with topic 05.