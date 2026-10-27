# intro-java-junior-dev

A hands-on, topic-by-topic repository for the **Road to Junior Java Developer**.

> **Working in this repo?** Read [BRANCHING.md](BRANCHING.md) first.
>
> **Branch model:** `main` = exercise (green out of the box) · `solution/*` = reference answers · `attempt/*` = anyone's own work.

Each topic is a self-contained Maven module with three parts:

1. **`investigation/`** — worked, heavily commented code with **enabled** tests
   that act as executable documentation. Everything is green out of the box —
   read top-to-bottom, then run the tests to confirm what you just read.
2. **`THEORY_EXAM.md`** — theory questions with blank `Answer:` lines.
   Fill them in from memory, then self-check against `investigation/`.
3. **`practical/`** — coding tasks with `UnsupportedOperationException` stubs
   and **`@Disabled`** tests. Enable tests one at a time as you implement —
   the goal is to make each formerly-skipped test pass, not to fix a failing one.

At the end, `07-examination-testing` mixes every topic into a single exam.

## Branches — read this first

The repo has three families of branches:

| Branch family | Purpose |
|---|---|
| `main` | The exercise — stubs + `@Disabled` tests + blank theory exams. `mvn test` **passes out of the box** (disabled tests are skipped, not failed). |
| `solution/<module>` | Reference answers, one branch per module, published by the repo owner. |
| `attempt/<name>/<module>` | Anyone's personal solution attempt. Never merged back. |

**`main` is never modified by solving.** Every line of solving lives on a
`solution/*` or `attempt/*` branch. See [BRANCHING.md](BRANCHING.md) for the
full workflow — how to create a branch, work through a module, and compare
your attempt against the reference.

## Modules

| Module                   | Topic                                                                                  |
|--------------------------|----------------------------------------------------------------------------------------|
| `01-java-core`           | Syntax, primitives, control flow, methods, strings, arrays, exceptions                 |
| `02-oop`                 | OOP: classes, interfaces, inheritance, polymorphism, records, enums, `Object` contract |
| `03-collections`         | Collections framework, `HashMap` internals                                             |
| `04-streams-api`         | Streams, lambdas, functional interfaces, `Optional`                                    |
| `05-sql-basics`          | SQL with embedded H2 (JDBC)                                                            |
| `06-algorithms-ds`       | Big-O, sorting, searching, linked list, stack/queue, recursion                         |
| `07-examination-testing` | Self-contained final exam (theory + practical, all topics)                             |

## Requirements

- JDK 21
- Maven 3.9+

## Workflow

```bash
# 1. Clone and create your attempt branch off main
git clone <repo-url>
cd intro-java-junior-dev
git switch -c attempt/<your-name>/01-java-core

# 2. Read the investigation code
$EDITOR 01-java-core/src/main/java/com/vbforge/javacore/investigation

# 3. Confirm investigation tests are green (they should be — executable docs)
mvn -pl 01-java-core test -Dtest='com.vbforge.javacore.investigation.*Test'

# 4. Answer 01-java-core/THEORY_EXAM.md from memory

# 5. For each practical task: read the Javadoc + tests, delete @Disabled
#    from ONE test method, implement, verify
mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest#divisibleBy15IsFizzBuzz'

# 6. When a task is green end-to-end, move to the next; when all tasks are green:
mvn -pl 01-java-core test
```

On `main`, `mvn test` at the root already passes — investigation tests run
and pass, practical tests are `@Disabled` and skipped. That's the "day one"
state. The work is to **enable** and **implement**, not to repair a red build.

## Quick commands

```bash
# Whole repo
mvn test

# One module
mvn -pl 03-collections test

# Investigation only (green on any branch)
mvn -pl 03-collections test -Dtest='com.vbforge.collections.investigation.*Test'

# A single test method — the tightest feedback loop
mvn -pl 03-collections test -Dtest='*Task03_SetOperationsTest#union'
```

## Suggestions

- Run `mvn wrapper:wrapper` to pin the Maven version for the repo (recommended once you push).
- Add `.mvn/wrapper/` and `mvnw` / `mvnw.cmd` to git after generating.
- Read [BRANCHING.md](BRANCHING.md) — it covers the three branch families,
  the `@Disabled` discipline, per-module ordering, and how to compare your
  attempt against a reference solution.

## License

MIT — see [LICENSE](LICENSE).