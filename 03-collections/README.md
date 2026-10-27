# 03 — Collections Framework

List, Set, Map, Queue/Deque, `HashMap` internals, iterators, `Comparable`/`Comparator`, immutable collections.

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `ListDemo` | `ArrayList` vs `LinkedList`, common operations, complexity trade-offs |
| `SetDemo` | `HashSet`, `LinkedHashSet`, `TreeSet`, uniqueness semantics |
| `MapDemo` | `HashMap`, `LinkedHashMap`, `TreeMap`, iteration styles, `merge`/`compute` |
| `QueueDequeDemo` | `ArrayDeque`, `PriorityQueue`, when to prefer `Deque` over `Stack` |
| `HashMapInternalsDemo` | hash collisions, `equals`+`hashCode` in practice, load factor, treeification |
| `IteratorDemo` | fail-fast iterators, `ConcurrentModificationException`, safe removal, `removeIf` |
| `ComparableComparatorDemo` | natural ordering, custom `Comparator`s, `Comparator.comparing` chains |
| `ImmutableCollectionsDemo` | `List.of`, `Set.of`, `Map.of`, `Collections.unmodifiableX`, copyOf |

### `THEORY_EXAM.md`

25 questions. Fill in yourself.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_WordCounter` | `Map` + counting |
| `Task02_ListOperations` | `List` manipulation |
| `Task03_SetOperations` | `Set` math (union, intersection, difference) |
| `Task04_BracketValidator` | `Deque` used as a stack |
| `Task05_StudentRegistry` | `Map` + `Comparable`/`Comparator` |
| `Task06_PriorityTaskQueue` | `PriorityQueue` + custom `Comparator` |

Run practical only:

```bash
mvn -pl 03-collections test -Dtest='com.vbforge.collections.practical.*Test'
```

When everything is green → you're done with topic 03.