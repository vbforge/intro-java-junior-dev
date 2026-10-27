# 02 — Object-Oriented Programming

Classes, encapsulation, inheritance, polymorphism, interfaces, the `Object` contract, records, enums, nested classes.

## What's inside

### `investigation/` — read top to bottom

| File | Covers |
|---|---|
| `ClassesAndObjectsDemo` | class vs object, instance vs static, constructors, `this`, constructor chaining |
| `EncapsulationDemo` | private fields, getters/setters, validation, immutability |
| `InheritanceDemo` | `extends`, `super`, `protected`, method overriding, `@Override` |
| `PolymorphismDemo` | dynamic dispatch, up/down casting, `instanceof` + pattern matching |
| `AbstractAndInterfacesDemo` | abstract classes, interfaces, `default`/`static` methods, when to use each |
| `ObjectContractDemo` | `equals`/`hashCode`/`toString` contracts, the classic "equals without hashCode" bug |
| `RecordsAndEnumsDemo` | record syntax, compact constructor, enums with fields and methods |
| `NestedClassesDemo` | static nested vs inner, local, anonymous classes |

Each investigation class contains static helper methods and small nested/helper types. The tests next to them verify the behavior. That makes them executable documentation.

### `THEORY_EXAM.md`

25 questions. Fill in the answers yourself.

### `practical/` — your turn

| Task | Topic |
|---|---|
| `Task01_BankAccount` | encapsulation + validation |
| `Task02_Shape` | abstract class + inheritance + polymorphism |
| `Task03_PaymentGateway` | interfaces + default methods + polymorphism |
| `Task04_Money` | `equals` / `hashCode` / `toString` / `Comparable` |
| `Task05_Operation` | enum with fields and methods |
| `Task06_Range` | record with compact constructor + validation |

Every method body is `throw new UnsupportedOperationException("TODO: implement")`.
Read the Javadoc; read the tests. Do NOT change signatures or tests.

Run practical only:

```bash
mvn -pl 02-oop test -Dtest='com.vbforge.oop.practical.*Test'
```

When everything is green → you're done with topic 02.