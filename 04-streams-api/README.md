# 04 — Streams API

Lambdas, functional interfaces, stream creation, lazy evaluation, intermediate/terminal operations, `Optional`, `Collectors`, parallel streams (and their pitfalls).

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `LambdaBasicsDemo` | lambda syntax, effectively-final capture, method references (4 kinds) |
| `FunctionalInterfacesDemo` | `Function`, `Predicate`, `Supplier`, `Consumer`, `BiFunction`, `UnaryOperator`, `BinaryOperator`, primitive variants |
| `StreamCreationDemo` | from collections, arrays, `Stream.of`, `Stream.iterate`/`generate`, `IntStream.range`, `Stream.builder` |
| `StreamLifecycleDemo` | lazy vs eager, intermediate vs terminal, stream reuse throws, `peek` timing |
| `StreamOperationsDemo` | `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit`/`skip`, `takeWhile`/`dropWhile`, `reduce`, `mapToInt` |
| `OptionalDemo` | `of`/`ofNullable`, `orElse` vs `orElseGet`, `map`/`flatMap`, `ifPresentOrElse`, `orElseThrow` |
| `CollectorsDemo` | `toList`/`toSet`/`toMap`, `groupingBy`, `partitioningBy`, `joining`, `counting`, `summingInt`, `teeing` |
| `ParallelStreamsDemo` | parallel sum, `collect(toList())` preserves order, `forEach` vs `forEachOrdered`, when NOT to parallelize |

### `THEORY_EXAM.md`

25 questions. Fill in yourself.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_LambdaBasics` | write lambdas and method references for common functional types |
| `Task02_FilterMap` | `filter` + `map` + `distinct` pipelines |
| `Task03_FlatMapAndSort` | `flatMap`, `sorted(Comparator)` |
| `Task04_OptionalChain` | `Optional` chaining |
| `Task05_Collectors` | `groupingBy` / `toMap` / `partitioningBy` / `joining` |
| `Task06_TextAnalyzer` | end-to-end text analysis with streams |

Run practical only:

```bash
mvn -pl 04-streams-api test -Dtest='com.vbforge.streams.practical.*Test'
```

When everything is green → you're done with topic 04.