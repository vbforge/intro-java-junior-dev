# 07 — Examination & Testing

**Final exam.** Self-contained — does NOT depend on modules 01–06. Covers every topic from the course, mixed.

> **This module is delivered in two parts.** Part 1 (this drop): scaffolding, theory exam, practical Tasks 01–03. Part 2: practical Tasks 04–06 (including the SQL task and its test resources). `mvn test` will run whatever exists — Tasks 04–06 simply aren't present yet.

## What's inside

### `THEORY_EXAM.md`

**30 questions**, 5 per topic:

| Topic | Qs |
|---|---|
| Java Core | 1–5 |
| OOP | 6–10 |
| Collections | 11–15 |
| Streams | 16–20 |
| SQL | 21–25 |
| Algorithms & DS | 26–30 |

Each question is followed by a blank `Answer:` line. Fill them in, then self-check against your investigation notes from modules 01–06.

### `practical/` — six tasks

Each task **integrates multiple topics**. Every method body is `throw new UnsupportedOperationException("TODO: implement")`. Tests are red until you implement.

| Task | Topics covered |
|---|---|
| `Task01_LibrarySystem` | OOP + Collections + Streams + Optional |
| `Task02_TextAnalysis` | Strings + Collections + Streams + Basic algorithms |
| `Task03_StudentGrades` | OOP (records) + Collections + Streams + Optional + Comparators |
| `Task04_Algorithms` *(part 2)* | Big-O + Sorting + Searching + Arrays |
| `Task05_RecursionAndDS` *(part 2)* | Recursion + Linked list + Stack/Queue |
| `Task06_BankTransactions` *(part 2)* | SQL + JDBC + OOP |

### Suggested workflow

1. Answer all 30 questions in `THEORY_EXAM.md`.
2. Do the practical tasks **in order** — each builds on the mental muscles of the previous one.
3. When everything is green, you've completed the course.

Run the exam module only:

```bash
mvn -pl 07-examination-testing test
```

Run the whole repo (all modules):

```bash
mvn test
```

## A note on scope

The exam is intentionally **harder** than any single module's practical section:
- Multiple topics in the same task
- Edge cases matter (empty inputs, ties, nulls)
- Real design choices (where to validate, when to return `Optional`, what to copy vs. share)

But nothing here requires knowledge outside the six modules. Everything is achievable with what you've already practiced.