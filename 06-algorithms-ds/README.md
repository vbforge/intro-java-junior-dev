# 06 — Algorithms & Data Structures

Big-O, classic sorting algorithms, searching, a hand-rolled `HashMap`, custom `Stack`/`Queue`, singly linked list, recursion.

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `BigODemo` | growth classes measured by operation count — O(1), O(log n), O(n), O(n log n), O(n²) |
| `SortingDemo` | bubble sort, insertion sort, merge sort — each with a stability note |
| `SearchingDemo` | linear search, binary search — requirements and edge cases |
| `SimpleHashMapDemo` | a from-scratch `HashMap` (array of linked-list buckets, resize on load factor) |
| `StackQueueDemo` | custom `IntStack` (array), custom `IntQueue` (circular buffer) |
| `LinkedListDemo` | singly linked list: add, remove, contains, reverse, size |
| `RecursionDemo` | factorial, fibonacci (naive + memoized), Hanoi, permutations, gcd |

### `THEORY_EXAM.md`

25 questions — Big-O, sorting, searching, hash maps, linked structures, recursion.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_BubbleAndInsertionSort` | two O(n²) sorts |
| `Task02_MergeSort` | divide-and-conquer sort |
| `Task03_BinarySearch` | binary search + first-occurrence variant |
| `Task04_LinkedList` | a full singly linked list |
| `Task05_QueueWithTwoStacks` | classic LIFO → FIFO trick |
| `Task06_Recursion` | several classic recursive problems |

Run practical only:

```bash
mvn -pl 06-algorithms-ds test -Dtest='com.vbforge.algorithms.practical.*Test'
```

When everything is green → you're done with topic 06.