# SQL / JDBC — Theory Exam

Answer in the blank line(s) after each question.

---

### 1. What is JDBC? What does it abstract away from the application code?

Answer:

---

### 2. What is the difference between `Statement` and `PreparedStatement`? Give two concrete advantages of the latter.

Answer:

---

### 3. What is SQL injection? How does `PreparedStatement` prevent it at the wire level (not just the API level)?

Answer:

---

### 4. Walk through the steps to run a query in JDBC — connection, statement, result set, close. What does `try-with-resources` do for you here?

Answer:

---

### 5. What is a `Connection`? Is it thread-safe? Should you share one across requests?

Answer:

---

### 6. What is a `ResultSet`? What does `rs.next()` return, and what does it mean?

Answer:

---

### 7. How do you read a `NULL` value from a `ResultSet`? What does `getInt` return for a NULL column, and how do you detect it?

Answer:

---

### 8. What is the difference between `rs.getXxx("colname")` and `rs.getXxx(index)`? Which is faster? Which is safer against column reordering?

Answer:

---

### 9. What is `ResultSetMetaData`? Give one use case.

Answer:

---

### 10. What is the logical order in which the SQL clauses are evaluated: `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`, `LIMIT`?

Answer:

---

### 11. What is the difference between `WHERE` and `HAVING`?

Answer:

---

### 12. Explain the difference between `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, and `FULL OUTER JOIN`. Which does MySQL not support natively?

Answer:

---

### 13. What does `LEFT JOIN ... WHERE right_table.id IS NULL` accomplish? Give one practical use case.

Answer:

---

### 14. What are the rules for selected columns under `GROUP BY`? Which columns must appear in `GROUP BY`?

Answer:

---

### 15. What does `DISTINCT` do? Does `SELECT DISTINCT a, b` deduplicate on `a` or on `(a, b)`?

Answer:

---

### 16. What is the difference between `ORDER BY` and `GROUP BY`?

Answer:

---

### 17. What is a transaction? State the four ACID properties.

Answer:

---

### 18. What is the difference between `COMMIT` and `ROLLBACK`?

Answer:

---

### 19. What does `Connection.setAutoCommit(false)` do? What happens to the *current* transaction when it's set, and how do you restore it?

Answer:

---

### 20. What is a primary key? What is a foreign key? What does a foreign key enforce?

Answer:

---

### 21. What is an index? When does it help query performance? When can it hurt?

Answer:

---

### 22. What is the difference between `UNION` and `UNION ALL`?

Answer:

---

### 23. What does `LIMIT` do? Is it part of the SQL standard? What is the standard equivalent?

Answer:

---

### 24. What is the difference between a NULL value and the string `"NULL"`? Explain SQL's three-valued logic: what does `NULL = NULL` return, and why?

Answer:

---

### 25. What is `SQLException`? When would you catch `SQLTimeoutException` or `BatchUpdateException` specifically instead of the parent type?

Answer: