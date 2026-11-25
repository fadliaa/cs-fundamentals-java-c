# CS Fundamentals Showcase (Java 17 & C11)

A clean, self-contained repository of original implementations covering core computer science concepts studied in **B.Sc. Information Engineering (TUM Campus Heilbronn)**: object-oriented design, generic data structures, divide-and-conquer algorithms, exception handling, and manual memory management in C.

> **Academic Integrity Notice:** All files in this repository are **100% original implementations** written independently for portfolio demonstration. No university exercise templates, grading harnesses, or course solutions are included.

## Contents

### Java 17 (`java/src/com/fadlia/fundamentals/`)
- `ds/DynamicArray.java` — Generic resizable array with capacity doubling, bounds validation, and `Iterable<T>`.
- `ds/DoublyLinkedList.java` — Generic doubly linked list with `O(1)` head and tail operations.
- `algo/SortingAlgorithms.java` — Stable Merge Sort, median-of-three Quick Sort, and iterative Binary Search.
- `domain/ResourceRegistry.java` & `CapacityExceededException.java` — OOP encapsulation, Java `record`, defensive copying, and checked exception contracts.
- `TestRunner.java` — Zero-dependency automated verification suite.

### C11 (`c/`)
- `include/int_list.h` & `src/int_list.c` — Dynamic heap-allocated singly linked list (`malloc`/`free`), in-place pointer reversal, and leak-free teardown.
- `tests/test_int_list.c` & `Makefile` — Strict `-std=c11 -Wall -Wextra -Werror` test harness.

## Build & Verify

```bash
# Java 17
mkdir -p java/out
javac -d java/out java/src/com/fadlia/fundamentals/ds/*.java java/src/com/fadlia/fundamentals/algo/*.java java/src/com/fadlia/fundamentals/domain/*.java java/src/com/fadlia/fundamentals/TestRunner.java
java -cp java/out com.fadlia.fundamentals.TestRunner

# C11 (Linux / macOS / MSYS2)
cd c && make test
```
