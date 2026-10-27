# Collections — Theory Exam

Answer in the blank line(s) after each question.

---

### 1. What is the top-level contract of each core interface: `List`, `Set`, `Map`, `Queue`? Give one sentence for each.

Answer:

---

### 2. `ArrayList` vs `LinkedList` — when would you pick each? Be specific about the operations where each wins.

Answer:

---

### 3. What is `ArrayList`'s internal data structure, its default initial capacity, and its growth policy?

Answer:

---

### 4. `HashSet` vs `LinkedHashSet` vs `TreeSet` — what does each guarantee about iteration order, and what's the runtime cost of `add`/`contains` in each?

Answer:

---

### 5. `HashMap` vs `LinkedHashMap` vs `TreeMap` — same as above for maps.

Answer:

---

### 6. What is a hash collision, and how does `HashMap` handle it?

Answer:

---

### 7. Explain `HashMap`'s load factor and threshold. What are the defaults, and what happens when threshold is exceeded?

Answer:

---

### 8. What is "treeification" in `HashMap` (Java 8+)? What triggers it, and what triggers reversion to a linked list?

Answer:

---

### 9. State the `equals`/`hashCode` contract and explain why `HashMap` breaks if you violate it.

Answer:

---

### 10. What is a fail-fast iterator? Which exception does it throw?

Answer:

---

### 11. What is the difference between fail-fast and fail-safe iterators? Give one example collection for each.

Answer:

---

### 12. Why does `list.remove(x)` inside a `for (T x : list)` loop sometimes throw `ConcurrentModificationException` and sometimes silently skip? What is the correct way to remove elements while iterating?

Answer:

---

### 13. What does `Iterator.remove()` do? Why is it safe while `list.remove()` inside `for-each` is not?

Answer:

---

### 14. What does `Collection.removeIf(predicate)` do? Why is it often better than a manual loop?

Answer:

---

### 15. `Comparable` vs `Comparator` — what is each for? Which interface does `Collections.sort(list)` use?

Answer:

---

### 16. What does it mean that `Collections.sort` and `Arrays.sort(Object[])` are "stable"? Why does stability matter?

Answer:

---

### 17. What is the difference between a `Queue` and a `Deque`? Why is `ArrayDeque` recommended over the legacy `Stack` class?

Answer:

---

### 18. What is `PriorityQueue`? Is it fully sorted when iterated? What is its `poll()` order?

Answer:

---

### 19. Can a `PriorityQueue` or `TreeMap` accept `null` elements? Why (or why not)?

Answer:

---

### 20. What is the difference between `List.of(...)` / `Collections.unmodifiableList(...)` / `new ArrayList<>(...)` copy?

Answer:

---

### 21. What does `Map.merge(key, value, remappingFunction)` do? Give a short, idiomatic example.

Answer:

---

### 22. What does `Map.computeIfAbsent(key, mappingFunction)` do? Why is it preferred over `if (map.get(k) == null) map.put(k, v)`?

Answer:

---

### 23. Are the standard collections (`ArrayList`, `HashMap`, `TreeMap`, ...) thread-safe? How do you obtain thread-safe alternatives?

Answer:

---

### 24. What is `LinkedHashMap`'s "access-order mode"? What well-known LRU-cache implementation does it enable?

Answer:

---

### 25. Give the Big-O of `get` / `put` / `contains` for: `ArrayList`, `LinkedList`, `HashMap`, `TreeMap`, `HashSet`, `TreeSet`. State the average case; note when the worst case differs.

Answer: