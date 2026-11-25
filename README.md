# CS Fundamentals Showcase (Java 17 & C11)

## What It Does

A clean, self-contained repository of original implementations covering core computer science concepts studied in **B.Sc. Information Engineering (TUM Campus Heilbronn)**: object-oriented design, generic data structures, divide-and-conquer algorithms, checked exception contracts, and manual heap memory management in C11.

> **Academic Integrity Notice:** All files in this repository are **100% original implementations** written independently for portfolio demonstration. No university exercise templates, grading harnesses, or course solutions are included.

## Tech Stack

- **Java:** Java 17+ (`javac`, zero external runtime dependencies)
- **C:** C11 (`gcc -std=c11 -Wall -Wextra -Werror`, `Makefile`)
- **CI/CD:** GitHub Actions automated build & test matrix (`.github/workflows/ci.yml`)

## Project Structure

```text
├── java/src/com/fadlia/fundamentals/
│   ├── ds/
│   │   ├── DynamicArray.java             # Generic resizable array (2x growth, Iterable<T>)
│   │   └── DoublyLinkedList.java         # Generic doubly linked list (O(1) endpoints)
│   ├── algo/
│   │   └── SortingAlgorithms.java        # Stable Merge Sort, median-of-3 Quick Sort, Binary Search
│   ├── domain/
│   │   ├── ResourceRegistry.java         # Encapsulation, Java record, defensive copying
│   │   └── CapacityExceededException.java# Custom checked exception contract
│   └── TestRunner.java                   # Zero-dependency automated verification suite
├── c/
│   ├── include/int_list.h                # Public singly linked list API
│   ├── src/int_list.c                    # Heap-allocated malloc/free & in-place reversal
│   ├── tests/test_int_list.c             # Pointer & memory assertions
│   └── Makefile                          # Strict -std=c11 -Wall -Wextra -Werror build
└── .github/workflows/ci.yml              # GitHub Actions Java + C11 CI pipeline
```

## How to Run

```bash
# Java 17+
mkdir -p java/out
javac -d java/out java/src/com/fadlia/fundamentals/ds/*.java java/src/com/fadlia/fundamentals/algo/*.java java/src/com/fadlia/fundamentals/domain/*.java java/src/com/fadlia/fundamentals/TestRunner.java
java -cp java/out com.fadlia.fundamentals.TestRunner

# C11 (Linux / macOS / MSYS2 / MinGW)
cd c && make test
```
