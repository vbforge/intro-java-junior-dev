# Branching Strategy

> **TL;DR**
> - `main` holds the exercise (stubs + `@Disabled` tests + blank theory exams).
> - Every solution lives on its own `solution/<module>` branch.
> - Any learner solving it their own way works on a personal `attempt/<name>/<module>` branch.
> - Nothing merges back to `main`.

---

## Table of contents

1. [The model at a glance](#1-the-model-at-a-glance)
2. [`main` — the exercise](#2-main--the-exercise)
3. [`solution/*` — reference answers](#3-solution--reference-answers)
4. [`attempt/*` — anyone's own journey](#4-attempt--anyones-own-journey)
5. [Repo owner workflow — publishing solutions](#5-repo-owner-workflow--publishing-solutions)
6. [Learner workflow — solving on your own branch](#6-learner-workflow--solving-on-your-own-branch)
7. [The `@Disabled` discipline](#7-the-disabled-discipline)
8. [Per-module walkthrough](#8-per-module-walkthrough)
9. [Keeping your branch in sync](#9-keeping-your-branch-in-sync)
10. [Resetting a task to its stubbed state](#10-resetting-a-task-to-its-stubbed-state)
11. [Collaboration patterns](#11-collaboration-patterns)
12. [Quick reference — commands](#12-quick-reference--commands)

---

## 1. The model at a glance

```
                         ┌──────────────────────────────────────┐
                         │  main                                │
                         │  - stubs everywhere                  │
                         │  - tests @Disabled                   │
                         │  - THEORY_EXAM.md templates          │
                         │  - never merged into; always pristine│
                         └──────────────────────────────────────┘
                                       │
              ┌────────────────────────┴────────────────────────┐
              │                                                 │
    ┌─────────▼──────────┐                          ┌───────────▼──────────┐
    │ solution/01-...    │                          │ attempt/<name>/01-...│
    │ solution/02-...    │  ← reference answers     │ attempt/<name>/02-...│
    │ ...                │    published by the      │ ...                  │
    │ solution/07-...    │    repo owner            │ (anyone's own work,  │
    └────────────────────┘                          │  never merged)       │
                                                    └──────────────────────┘
```

Three families of branches:

| Family                    | Who creates it | Purpose                         | Merged back to `main`? |
|---------------------------|----------------|---------------------------------|------------------------|
| `main`                    | The owner      | The exercise itself             | —                      |
| `solution/<module>`       | The owner      | Reference answer for one module | **No**                 |
| `attempt/<name>/<module>` | Any learner    | Personal solution attempt       | **No**                 |

The rule that ties it all together:

> **`main` is never modified by solving.** Solving happens on a branch.
> `main` may only change to fix bugs in the *stubs*, Javadoc, or test
> expectations — never to add solutions.

---

## 2. `main` — the exercise

`main` should always be a clean, "day one" version of the course:

- Every method in `practical/` throws `UnsupportedOperationException("TODO: implement")`.
- Every test in `practical/` is annotated `@Disabled`.
- Every `THEORY_EXAM.md` has the questions and blank `Answer:` lines.
- Every `investigation/` file is fully written and its tests **are** enabled
  (they must be green out of the box — they're the executable docs).

Running `mvn test` on `main` therefore **passes cleanly**. That's intentional —
a newcomer clones and immediately sees a green build. The "todo" isn't failing
tests; it's the disabled ones.

> If a newcomer's first action is `git clone && mvn test`, they should see
> "BUILD SUCCESS", not a wall of red. The journey starts with reading, then
> enabling, then solving.

---

## 3. `solution/*` — reference answers

One branch per module: `solution/01-java-core`, `solution/02-oop`, …,
`solution/07-examination-testing`.

A `solution/<module>` branch differs from `main` in exactly two ways:

1. `practical/*.java` methods are **implemented** (no more `UnsupportedOperationException`).
2. `practical/*Test.java` files have their `@Disabled` annotations **removed**.

Nothing else changes. `investigation/`, `THEORY_EXAM.md`, `pom.xml`, and the
module `README.md` are identical to `main`. This is what makes it easy for
someone to view a solution without also having to filter out unrelated
differences — the following command shows *only* the solving changes, on both
the main and test sides:

```bash
git diff main..solution/01-java-core -- \
    01-java-core/src/main/java/com/vbforge/javacore/practical/ \
    01-java-core/src/test/java/com/vbforge/javacore/practical/
```

---

## 4. `attempt/*` — anyone's own journey

Any learner can create `attempt/<their-name>/<module>`. Same shape as a
`solution/*` branch: implementations added, `@Disabled` removed. The difference
is intent:

- A `solution/*` branch is the *canonical* answer, published by the owner.
- An `attempt/*` branch is one person's *opinion* on the answer.

Attempt branches are never merged back to `main` or to `solution/*`. They exist
for the learner's own git history, and (optionally) so others can review them.

The `attempt/*` naming isn't enforced by git — it's a convention. But the
convention matters because it lets a reader of the repo tell at a glance which
branches are authoritative.

---

## 5. Repo owner workflow — publishing solutions

The owner's job is to publish `solution/<module>` branches **as each module is
completed**. Do not publish all seven at once — the learning value of the repo
depends on its solutions looking like *fresh work*, and the git history
preserves that naturally if you do them one at a time.

### 5.1 One-time setup

```bash
git clone <your-repo-url> intro-java-junior-dev
cd intro-java-junior-dev
git switch main
git pull
```

### 5.2 Creating a solution branch for a module

```bash
# Always branch off the latest main
git switch main
git pull
git switch -c solution/01-java-core
```

(`git switch -c` is the modern equivalent of `git checkout -b`. Either works.)

### 5.3 Working through the module

The recommended loop:

```
1. Read the investigation code (no test changes needed — those tests are already green).
2. Fill in THEORY_EXAM.md from memory, then self-check against the code.
3. For each practical task, in order:
     a. Open the task file; read the Javadoc.
     b. Open the corresponding test file; read the tests.
     c. Remove @Disabled from ONE test method. (§7.3 explains why we don't
        enable the whole class at once.)
     d. Implement the method in the task file.
     e. Run only that task's tests:
          mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest'
     f. When that test is green, move to the next test method in the same task.
     g. When the entire task is green, move to the next task.
4. When all tasks are green, verify the whole module:
     mvn -pl 01-java-core test
```

Commit at any pace you like. Two conventions that keep history useful:

- **One commit per task** — e.g. `solve(01-java-core): Task01 FizzBuzz`.
  This makes `git log --oneline main..solution/01-java-core` a readable
  progress story.
- **The last commit re-enables all remaining tests** so the branch
  self-verifies in one shot.

### 5.4 Verifying before push

Before pushing, run the whole module:

```bash
mvn -pl 01-java-core test
```

Expected: **everything green**, including the formerly-`@Disabled` tests.
If anything is red, do not push — the branch is supposed to be a worked answer.

Also confirm the diff against `main` is *only* what you expect:

```bash
git diff main..HEAD --stat
```

You should see:

- Modified: files under `practical/` (both main and test side).
- Nothing else.

If `THEORY_EXAM.md`, `README.md`, or `pom.xml` show up here, something slipped.
Usually it's easier to reset the offending file:

```bash
git checkout main -- 01-java-core/THEORY_EXAM.md
```

> **Note on `git checkout <ref> -- <path>`:** this is the only way to restore a
> single *file* from another branch. `git switch` changes branches but cannot
> restore files, so `git checkout` stays in the toolbox for this specific idiom.
> You'll see it again in §7.4, §10, and §12.

### 5.5 Push

```bash
git push -u origin solution/01-java-core
```

The `-u` sets the upstream so subsequent pushes are just `git push`.

### 5.6 Repeat

```bash
git switch main
git pull
git switch -c solution/02-oop
# ... same loop ...
```

Seven times. Each `solution/<module>` branch is fully independent of the others
— you can publish them in any order and at any pace.

---

## 6. Learner workflow — solving on your own branch

Anyone who clones the repo can solve it. Forking is *optional* — you can work
locally with no fork and simply never push. Fork if you want to share or keep a
backup.

### 6.1 Clone (or fork + clone)

```bash
# Direct clone (local-only work)
git clone <repo-url>
cd intro-java-junior-dev

# OR fork on GitHub first, then:
git clone <your-fork-url>
cd intro-java-junior-dev
git remote add upstream <original-repo-url>   # optional but useful
```

### 6.2 Create your attempt branch

```bash
git switch main
git pull
git switch -c attempt/your-name/01-java-core
```

Naming: `attempt/<github-handle>/<module>`. Using your GitHub handle (not your
full name) makes it easy to correlate branches to profiles if you push the
branch publicly.

### 6.3 The per-task loop

Same loop as the owner's, but do this **per test method** rather than per task
if you're new:

```
1. Read investigation code (green tests are your reference).
2. Write THEORY_EXAM answers from memory — do NOT peek at solution/* branches yet.
3. For each practical task:
     a. Read Javadoc.
     b. Read test cases.
     c. Un-@Disable ONE test method.
     d. Run it: mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest#divisibleBy15IsFizzBuzz'
     e. Red → edit code → re-run → green.
     f. Move to the next test method.
4. When the task is done, run the whole task's tests to confirm.
5. When the module is done, run the whole module.
```

### 6.4 Push and share (optional)

```bash
git add -A
git commit -m "attempt(01-java-core): solve FizzBuzz, StringUtils"
git push -u origin attempt/your-name/01-java-core
```

If you forked, this pushes to **your** fork. If you have write access to the
original repo, push carefully — coordinate with the owner first, because
`attempt/*` branches are supposed to be personal. If in doubt, push to your
fork.

### 6.5 Comparing against the reference (only after you're done)

```bash
# Only once your whole module is green, look at the owner's solution
# (both main and test side):
git diff main..solution/01-java-core -- \
    01-java-core/src/main/java/com/vbforge/javacore/practical/ \
    01-java-core/src/test/java/com/vbforge/javacore/practical/

# Or diff your own attempt against the reference:
git diff solution/01-java-core..attempt/your-name/01-java-core \
        -- 01-java-core/src/main/java/com/vbforge/javacore/practical/ \
           01-java-core/src/test/java/com/vbforge/javacore/practical/
```

The second diff is more interesting: it shows **where your answer differs from
the reference**. Sometimes the reference is cleaner; sometimes yours is. Both
observations are valuable.

---

## 7. The `@Disabled` discipline

### 7.1 Why `@Disabled` on `main`

Two reasons:

1. **`mvn test` on `main` must be green.** That's a promise to newcomers: "you
   cloned cleanly, nothing is broken, nothing is failing — the work is to
   *enable*, not to *fix*."
2. **Skipping ≠ passing.** A skipped test doesn't verify anything. A solution
   branch without un-`@Disabled` tests would look like a *proof*, but prove
   nothing. Forcing the un-disable step keeps the branch honest.

### 7.2 The un-disabling pattern

Wherever you see:

```java
@Disabled("TODO: remove this line when you start implementing")
@Test
void divisibleBy15IsFizzBuzz() {
    assertThat(Task01_FizzBuzz.fizzBuzz(15)).isEqualTo("FizzBuzz");
}
```

Change to:

```java
@Test
void divisibleBy15IsFizzBuzz() {
    assertThat(Task01_FizzBuzz.fizzBuzz(15)).isEqualTo("FizzBuzz");
}
```

That's the entire change: delete the `@Disabled(...)` line. The `@Test`
annotation stays. Do NOT comment out `@Disabled` — either remove it, or leave
the test disabled.

### 7.3 Iterative activation

When you work on a task, **enable one test method at a time**. Reasons:

- The Maven report shows exactly one red test, so you know precisely what to fix.
- If you enable the whole class at once, you get N red tests and the
  signal-to-noise ratio is awful.
- It forces the "read the test → design the method" loop instead of "make all
  errors go away".

```bash
# Focused run: only one method
mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest#divisibleBy15IsFizzBuzz'
```

### 7.4 What if I un-disable a whole class by accident?

```bash
# Restore just that test file to its original stubbed/disabled state
git checkout main -- 01-java-core/src/test/java/com/vbforge/javacore/practical/Task01_FizzBuzzTest.java
```

Then re-apply un-disabling one method at a time.

---

## 8. Per-module walkthrough

Suggested order and rough time budgets. Use them as a guide, not a schedule.

| Module | Read first | Task order | Rough time |
|---|---|---|---|
| `01-java-core` | All investigation files in order | 01 → 02 → 03 → 04 → 05 | 6–10 h |
| `02-oop` | All investigation files in order | 01 → 02 → 03 → 04 → 05 → 06 | 8–14 h |
| `03-collections` | All investigation, re-read `HashMapInternalsDemo` twice | 01 → 02 → 03 → 04 → 05 → 06 | 10–16 h |
| `04-streams-api` | Read 01–04, take a break, then 05–08 | 01 → 02 → 03 → 04 → 05 → 06 | 12–20 h |
| `05-sql-basics` | `schema.sql` + `seed.sql` FIRST, then investigation | 01 → 02 → 03 → 04 → 05 → 06 | 10–16 h |
| `06-algorithms-ds` | Big-O, then Searching, then Sorting, then the rest | 03 → 01 → 02 → 06 → 04 → 05 | 15–25 h |
| `07-examination-testing` | Nothing — this is the exam | 01 → 02 → 03 → 04 → 05 → 06 | 15–25 h |

### Notes on each

**01-java-core.** Do not skip investigation even if it "feels basic" —
`PrimitivesAndOperatorsDemo` and `ControlFlowDemo` contain the
`Integer`-cache and `switch`-expression material that a lot of self-taught
developers get wrong for years.

**02-oop.** `Task04_Money` (equals/hashCode/toString/Comparable) is the pivotal
task. If it takes an entire afternoon, that's normal — it's the single most
consequential concept in OOP fluency.

**03-collections.** `HashMapInternalsDemo` is the file to internalize. The
`IteratorDemo` "silently skips" example is a common interview trap. Re-read both
before starting `Task05_StudentRegistry`.

**04-streams-api.** Streams feel magical until they don't. Read
`StreamLifecycleDemo` twice — lazy evaluation and one-shot semantics are what
trip people up in production. `OptionalDemo`'s eager-vs-lazy distinction is
another common interview question.

**05-sql-basics.** Read `schema.sql` and `seed.sql` before touching the
investigation code. Every query in the module targets those exact tables. Do
`Task06_SqlInjectionSafety` *last*, after you've seen `PreparedStatementDemo`
side-by-side with the vulnerable version.

**06-algorithms-ds.** Start with `Task03_BinarySearch` — it's the smallest task
and warms up the "read the spec, write the loop, verify the invariant" muscle.
Do `Task02_MergeSort` **before** `Task06_Recursion` if recursion is new — merge
sort is concrete recursion; the recursion task is abstract recursion.

**07-examination-testing.** Do this on a **fresh branch from `main`** — do NOT
reuse your per-module attempt branches. The exam's point is that it's isolated.
Do it **open-book** first (peek at per-module code if needed), then
**closed-book** a week later on a new branch.

---

## 9. Keeping your branch in sync

If `main` gets a bug fix (say, a Javadoc typo or a wrong expected value in a
test), your solution/attempt branch is now out of date. Two safe ways to sync:

### 9.1 Rebase (preferred for solution/attempt branches)

```bash
git switch solution/01-java-core
git fetch origin
git rebase origin/main
```

Rebase replays your commits on top of the updated `main`. Result: your branch
looks like it was always based on the latest `main`, with your solving commits
on top. Clean history, but **do not rebase a branch anyone else is working on**.

If `main` only changed a test's expected value that you also changed (e.g. via
un-disabling), rebase may conflict. Resolve by keeping YOUR test change and
re-applying `main`'s fix on top:

```bash
# During conflict resolution:
# take main's expected value, keep your @Disabled removal
git checkout --theirs path/to/Test.java    # take main's version
$EDITOR path/to/Test.java                  # re-remove @Disabled, accept new expected
git add path/to/Test.java
git rebase --continue
```

> **Note:** In a *rebase*, "ours" and "theirs" are swapped relative to a merge.
> Here "theirs" means `main` — the branch you're rebasing onto. In a merge,
> "theirs" would mean the branch you're merging *in*.

### 9.2 Merge (safer but noisier)

```bash
git switch solution/01-java-core
git merge origin/main
```

Creates a merge commit. History is more cluttered but no commit is rewritten.
Fine for personal attempt branches.

### 9.3 Rule of thumb

- **Solution branches** → rebase. You own them, no one else commits to them.
- **Attempt branches** → rebase if local only; merge if pushed and shared.

---

## 10. Resetting a task to its stubbed state

The classic case: you finished `Task01_FizzBuzz` but want to redo it from
scratch as a self-test.

```bash
# Restore both the implementation and the test to main's pristine state
git checkout main -- 01-java-core/src/main/java/com/vbforge/javacore/practical/Task01_FizzBuzz.java
git checkout main -- 01-java-core/src/test/java/com/vbforge/javacore/practical/Task01_FizzBuzzTest.java
```

The stubs (`throw new UnsupportedOperationException(...)`) and the `@Disabled`
annotations come back. Your other tasks are unaffected.

If you want to reset the **entire module** back to stubs:

```bash
git checkout main -- 01-java-core/src/main/java/com/vbforge/javacore/practical/
git checkout main -- 01-java-core/src/test/java/com/vbforge/javacore/practical/
```

That's a full "redo this module" reset. `investigation/` and `THEORY_EXAM.md`
stay as-is (they're the same on `main` anyway).

---

## 11. Collaboration patterns

### 11.1 Sharing a personal attempt for review

A learner who wants feedback on their `attempt/<name>/<module>` branch can open
a **draft PR from their attempt branch to the corresponding solution branch**
in the original repo. The PR is not meant to be merged — the branch diff *is*
the review material.

(If their attempt branch lives on a fork — see §6.1 — this is a cross-repo PR;
GitHub handles that transparently.)

Suggested PR title:

```
Review request: attempt/alice/01-java-core vs solution/01-java-core
```

The reviewer compares via the GitHub diff view and leaves inline comments.

### 11.2 Proposing an improvement to a reference solution

If someone finds a cleaner implementation for a task, they can:

1. Branch off `solution/<module>` (not `main`).
2. Apply the improvement.
3. Open a PR to `solution/<module>`.

The discussion is about the solution itself. `main` is untouched.

### 11.3 (Optional) Automating test runs

If you set up GitHub Actions, you probably want:

- `main` → `mvn test` runs; must be green (all `@Disabled` → skipped → green).
- `solution/*` and `attempt/*` → `mvn test` runs; must be green (un-disabled).

Minimal workflow file:

```yaml
# .github/workflows/test.yml
name: test
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
      - run: mvn -B test
```

Skip this until you have at least `solution/01-java-core` pushed — otherwise
CI is green from day one and adds no signal.

---

## 12. Quick reference — commands

### Everyday

```bash
# See where you are
git branch --show-current
git status

# Switch to main and pull
git switch main && git pull

# Create a new solution/attempt branch off main
git switch -c solution/01-java-core
git switch -c attempt/alice/01-java-core

# Add and commit
git add -A
git commit -m "solve(01-java-core): Task01 FizzBuzz"

# Push and set upstream
git push -u origin solution/01-java-core
git push -u origin attempt/alice/01-java-core

# List all branches (local + remote)
git branch -a

# Delete a stale local branch.
# Use -D (not -d): solution/attempt branches are never merged to main by
# design, so -d would refuse with "not fully merged".
git branch -D solution/01-java-core

# Also delete the remote copy
git push origin --delete solution/01-java-core
```

### Running tests

```bash
# Whole repo
mvn test

# One module
mvn -pl 01-java-core test

# Only investigation (green on any branch)
mvn -pl 01-java-core test -Dtest='com.vbforge.javacore.investigation.*Test'

# Only practical — skipped (@Disabled) on main; enabled and green on
# solution/attempt branches once solved
mvn -pl 01-java-core test -Dtest='com.vbforge.javacore.practical.*Test'

# A single task
mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest'

# A single method — the tightest feedback loop
mvn -pl 01-java-core test -Dtest='*Task01_FizzBuzzTest#divisibleBy15IsFizzBuzz'
```

### Resetting

```bash
# Reset one task (both implementation and test)
git checkout main -- 01-java-core/src/main/java/com/vbforge/javacore/practical/Task01_FizzBuzz.java
git checkout main -- 01-java-core/src/test/java/com/vbforge/javacore/practical/Task01_FizzBuzzTest.java

# Reset the whole practical folder of a module
git checkout main -- 01-java-core/src/main/java/com/vbforge/javacore/practical/
git checkout main -- 01-java-core/src/test/java/com/vbforge/javacore/practical/
```

### Diffing

```bash
# What does my solution branch change vs main? (both sides)
git diff main..HEAD -- \
    01-java-core/src/main/java/com/vbforge/javacore/practical/ \
    01-java-core/src/test/java/com/vbforge/javacore/practical/

# What does the reference solution change? (view only, don't apply)
git diff main..solution/01-java-core

# How does my attempt differ from the reference?
git diff solution/01-java-core..attempt/alice/01-java-core
```

### Syncing with main

```bash
# Rebase a solution branch onto latest main
git switch solution/01-java-core
git fetch origin
git rebase origin/main

# Abort a rebase gone wrong
git rebase --abort
```

---

**Reminder:** `main` is never modified by solving. Solutions and attempts live
on their own branches. If you find yourself typing `git commit` while on
`main`, stop and create a branch first.