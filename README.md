# Java Algorithms, Simulations, and LeetCode Practice

A comprehensive repository containing Java implementations of core data structures, algorithms, advanced gaming/engineering simulations, and solutions to LeetCode problems. 

This repository serves as a sandbox for exploring algorithm complexity, architectural simulation patterns, and technical problem-solving.

---

## 🛠️ Repository Structure

The codebase is divided into two primary sections: **Core Algorithm & System Simulations** (comprising custom business logic, games, and interview prototypes) and **LeetCode Problem Tracking**.

### 1. Algorithms & Simulations (`/src/others`)

| # | Topic / Project | Component / File | Key Concepts |
| :--- | :--- | :--- | :--- |
| 1 | **Quick Sort** | `Sorting.java` | Divide-and-conquer, pivot strategy, $O(N \log N)$ complexity. |
| 2 | **Depth-First Search (DFS)** | `Graph.java` | Graph theory, vertex traversal, stack backtracking. |
| 3 | **Top 3 Stocks Filter** | `TopStockSums.java` | Min-Heaps / Priority Queue tracking for high-frequency pricing data. |
| 4 | **The Winner's Growth** | `CellSimulation.java` | "Cell-eating" behavior, spatial simulations, dynamic boundary matrices. |
| 5 | **Grid & Cascade Engines** | `NeighborLogic.java`<br>`CandyCrushEngine.java` | 2D Array traversal, Direction Arrays, "Match-3" cascade math, "Corner-L" validation rules. |
| 6 | **State-Machine Traversal** | `GloriaQuest.java` | Deterministic Finite Automata (DFA), turn-based state updates. |
| 7 | **Java Bank Simulator** | `BankSystem.java` | Concurrent balance management, transaction routing, ledger consistency. |
| 8 | **FizzBuzz Optimization** | `FizzBuzz.java` | Standard conditional parsing benchmark. |
| 9 | **Request Rate Limiter** | `RateLimiterDemo.java` | Sliding window / Token bucket design patterns for microservices. |
| 10 | **Circular Sequence Reconstruct**| `ReconstructCircularSymbolSequenceDemo.java` | Array cyclic shifts, index wrapping modulo arithmetic. |
| 11 | **Race Elimination Logic** | `StockCarRaceSimulation.java` | Dynamic sorting queues, runtime filter metrics per lap. |
| 12 | **Knapsack Problem** | `Knapsack.java` | Dynamic Programming (DP), bounded combinatorial optimization. |
| 13 | **Multi-Attribute Sorting** | `MultiSortExample.java` | Custom Java `Comparator` chaining for entity matrices. |

---

### 2. LeetCode Progress Tracking (`/src/leetcode`)

A curated tracking matrix of foundational and advanced LeetCode problems solved in Java.

| # | Problem Name | Difficulty / Notes |
| :---: | :--- | :--- |
| 1 | **Two Sum** | 🟢 Easy |
| 2 | **Add Two Numbers** | 🟡 Medium (Linked List) |
| 3 | **Longest Substring Without Repeating Characters** | 🟡 Medium (Sliding Window) |
| 4 | **Median of Two Sorted Arrays** | 🔴 Hard (Binary Search Partitioning) |
| 5 | **Longest Palindromic Substring** | 🟡 Medium |
| 6 | **Zigzag Conversion** | 🟡 Medium |
| 7 | **Reverse Integer** | 🟢 Easy |
| 8 | **String to Integer (atoi)** | 🟡 Medium |
| 9 | **Palindrome Number** | 🟢 Easy |
| 10 | **Regular Expression Matching** | 🔴 Hard (⚠️ **Needs Review / Re-do**) |
| 11 | **Container With Most Water** | 🟡 Medium (Two-Pointer Method) |
| 12 | **Integer to Roman** | 🟡 Medium |
| 13 | **Roman to Integer** | 🟢 Easy |
| 14 | **Longest Common Prefix** | 🟢 Easy |
| 15 | **3Sum** | 🟡 Medium |
| 16 | **3Sum Closest** | 🟡 Medium |
| 17 | **Letter Combinations of a Phone Number** | 🟡 Medium (Backtracking) |
| 18 | **4Sum** | 🟡 Medium |
| 19 | **Remove Nth Node From End of List** | 🟡 Medium |
| 20 | **Valid Parentheses** | 🟢 Easy (Stack) |
| 21 | **Merge Two Sorted Lists** | 🟢 Easy |
| 22 | **Generate Parentheses** | 🟡 Medium |
| 23 | **Merge k Sorted Lists** | 🔴 Hard (Priority Queue) |
| 24 | **Swap Nodes in Pairs** | 🟡 Medium |
| 25 | **Reverse Nodes in k-Group** | 🔴 Hard |
| 26 | **Remove Duplicates from Sorted Array** | 🟢 Easy |
| 27 | **Remove Element** | 🟢 Easy |
| 28 | **Find the Index of the First Occurrence in a String** | 🟢 Easy |
| 29 | **Divide Two Integers** | 🟡 Medium |
| 30 | **Substring with Concatenation of All Words** | 🔴 Hard |
| 31 | **Next Permutation** | 🟡 Medium |

---

## 🚀 Getting Started

### Prerequisites
*   **Java Development Kit (JDK 17 or higher)**
*   An IDE of choice (IntelliJ IDEA, Eclipse, or VS Code) or terminal execution tools.

### Compilation & Execution
To compile and run any simulation manually from the command line:

```bash
# Clone the repository
git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
cd your-repo-name

# Compile a specific class
javac src/others/TopStockSums.java

# Run the execution wrapper
java -cp src others.TopStockSums