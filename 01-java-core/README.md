# 01 — Java Core

Foundations before OOP: syntax, primitives, control flow, methods, strings, arrays, exceptions.

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `PrimitivesAndOperatorsDemo` | 8 primitive types, literals, casting, overflow, operators |
| `ControlFlowDemo` | `if/else`, classic `switch`, arrow `switch` (Java 14+), all loop forms, labeled `break`/`continue` |
| `MethodsDemo` | overloading, `varargs`, **pass-by-value** (the classic gotcha) |
| `StringsDemo` | immutability, `String` pool, common methods, `StringBuilder`, text blocks |
| `ArraysDemo` | 1D/2D arrays, `java.util.Arrays` utilities |
| `ExceptionsDemo` | try/catch/finally, multi-catch, checked vs unchecked, custom exceptions |

Each investigation class has static methods that return a value; tests next to them **verify** the behavior. That makes the investigation classes executable documentation.

Run investigation only:

```bash
mvn -pl 01-java-core test -Dtest='com.vbforge.javacore.investigation.*Test'
```

### `THEORY_EXAM.md`

20 questions across the topic. Fill in the answers yourself.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_FizzBuzz` | control flow |
| `Task02_StringUtils` | strings |
| `Task03_ArrayUtils` | arrays |
| `Task04_MathUtils` | methods + loops |
| `Task05_SafeDivider` | custom exceptions |

Each method body is `throw new UnsupportedOperationException("TODO: implement")`.
Each task has a matching test class. **The tests are the spec** — read them if the Javadoc is unclear, but don't change them.

Run practical only:

```bash
mvn -pl 01-java-core test -Dtest='com.vbforge.javacore.practical.*Test'
```

When everything is green → you're done with topic 01.