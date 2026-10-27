# Final Exam — Theory

30 questions across all six topics. No time limit. Answer in the blank line after each question.

You should have seen every concept covered here during modules 01–06.
If a question stumps you, that's the signal to revisit that module's `THEORY_EXAM.md` and `investigation/` folder.

---

## Java Core

### 1. What is the difference between a primitive type and a reference type? Give one practical consequence of the difference.

Answer:

---

### 2. What does it mean that `String` is immutable? Name one bug this prevents and one performance pitfall it causes.

Answer:

---

### 3. Java is strictly "pass-by-value". Explain what that means when you pass an object to a method — including why the callee can still mutate the object's fields.

Answer:

---

### 4. When must you use `.equals()` instead of `==`? Which one does `HashMap.get()` use?

Answer:

---

### 5. You have `try { return 1; } finally { return 2; }` inside a method returning `int`. What does the method return, and why?

Answer:

---

## OOP

### 6. Name the four pillars of OOP and give one sentence for each.

Answer:

---

### 7. Abstract class vs. interface — when would you pick each? Can a class extend one abstract class AND implement several interfaces at the same time?

Answer:

---

### 8. State the `equals`/`hashCode` contract. What goes wrong in a `HashSet` if you override one but not the other?

Answer:

---

### 9. What is a `record`? List everything the compiler generates for a one-liner `record Point(int x, int y) {}`.

Answer:

---

### 10. "Favor composition over inheritance." Explain the reasoning and give one concrete example where inheritance would be a mistake.

Answer:

---

## Collections

### 11. Compare `ArrayList` and `LinkedList` in terms of: `get(i)`, `add(0, x)`, `add(end, x)`, memory overhead per element. When would you actually pick `LinkedList`?

Answer:

---

### 12. `HashMap` vs. `TreeMap` vs. `LinkedHashMap` — what does each guarantee about iteration order, what is the complexity of `get`/`put`, and can they accept `null` keys?

Answer:

---

### 13. Describe how `HashMap` resolves collisions in Java 8+. What is "treeification", when does it trigger, and what does it change about the worst-case complexity?

Answer:

---

### 14. What is a fail-fast iterator? Explain one scenario where modifying a collection during for-each does NOT throw `ConcurrentModificationException`, and say why.

Answer:

---

### 15. What is the difference between `List.of(a, b, c)`, `Collections.unmodifiableList(list)`, and `new ArrayList<>(list)`? For each, describe the mutability of the result and its relationship to the source.

Answer:

---

## Streams API

### 16. What is the fundamental difference between a `Collection` and a `Stream`? Name two practical consequences.

Answer:

---

### 17. Distinguish intermediate from terminal operations. Give two examples of each. What happens if you call a terminal operation twice on the same stream instance?

Answer:

---

### 18. What is the difference between `map` and `flatMap`? Give a case where `flatMap` is required — and say what would happen if you used `map` instead.

Answer:

---

### 19. Explain the difference between `Optional.orElse(x)` and `Optional.orElseGet(supplier)`. Give a case where the distinction matters — and say which one is safer by default.

Answer:

---

### 20. `findFirst()` vs. `findAny()` — what is the difference, and when does it matter? What does `forEach` do differently from `forEachOrdered` on a parallel stream?

Answer:

---

## SQL (JDBC / Relational)

### 21. What is the difference between `WHERE` and `HAVING`? Which clauses can appear in each that the other cannot?

Answer:

---

### 22. What is the difference between `INNER JOIN` and `LEFT JOIN`? Write (in English, not SQL) how you would find all authors who have no books — starting from the `authors` table.

Answer:

---

### 23. What is SQL injection? Explain at the wire level how a `PreparedStatement` prevents it, not just at the API level.

Answer:

---

### 24. What does "ACID" stand for? Give one example where ignoring each property would cause a real bug.

Answer:

---

### 25. What is the difference between SQL `NULL` and the string `"NULL"`? What does `NULL = NULL` evaluate to, and why does SQL use three-valued logic?

Answer:

---

## Algorithms & Data Structures

### 26. Rank these growth classes from slowest to fastest: O(n²), O(1), O(n log n), O(log n), O(n), O(2ⁿ). For each, name one algorithm or operation from this course that has that complexity.

Answer:

---

### 27. What prerequisites must hold for binary search to work? What is its time complexity, and why does violating the prerequisites produce wrong answers silently (not an exception)?

Answer:

---

### 28. What does it mean for a sort to be "stable"? Which of (bubble sort, insertion sort, merge sort, quick sort) are stable? Why does stability matter in practice?

Answer:

---

### 29. Describe the three-pointer technique for reversing a singly linked list in place. What are the time and space complexities?

Answer:

---

### 30. What is memoization? Give one concrete example from this course where it changes the algorithm from exponential to linear time — and explain the mechanism.

Answer: