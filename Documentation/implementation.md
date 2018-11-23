# Implementation

*This document describes the actual features of the software, and how they are implemented. The original design document can be found [here](https://github.com/otsha/tiralabra-scheduler/blob/master/Documentation/description.md).*

## Index
- [Architecture](#architecture)
- [Data Structures](#data-structures)
	- [TaskList](#tasklist)
		- [Methods](#methods-offered-by-tasklist)
		- [Time Complexity](#time-complexity-of-tasklist)
	- [TaskQueue](#taskqueue)
		- [Methods](#methods-offered-by-taskqueue)
		- [Time Complexity](#time-complexity-of-taskqueue)
- [Algorithms](#algorithms)
	- [Merge Sort](#merge-sort)
	- [Moore-Hodgson](#moore-hodgson)
		- [How it works](#how-it-works)
- [References](#references)

## Architecture

The software follows a four-tier architecture. The top layer consists of the user interface and the applications startup functionality, which are located in packages ```ui``` and ```app``` respectively.

The application's main functionality - scheduling - happens at the logic layer, which the user interface calls when necessary. All logical functionality is located in the ```logic``` package.

The ```Task``` object and the custom data structures used by the application (```TaskList```, ```TaskQueue```) are located in the ```data``` package.

Finally, the bottom layer of the application consists of the ```FileAccess``` class inside the package ```access```, which is responsible for reading and saving data to an external file. The data is stored in jSon format using the GSon library.

## Data Structures

The application implements two custom data structures.

### TaskList
```TaskList``` is an ArrayList-like data structure for storing tasks.

#### Methods offered by TaskList

- ```size()``` - Returns the number of tasks currently on the list
- ```capacity()``` - Returns the current capacity of the list
- ```add(Task t)``` - Adds a task on the list, doubling the capacity if the number of tasks is more than 1/2 of the current capacity
- ```remove(int index)``` - Removes a task from the given index, halving the capacity if the number of tasks is less than 1/2 of the current capacity
- ```get(int index)``` - Returns the task at the given index
- ```contains(Task t)``` - Checks whether the given task is on the list
- ```swap(int index1, int index2)``` - Swaps the contents of the given indexes

#### Time Complexity of TaskList

| size | capacity | add | remove | get | contains | swap |
|-----|-----|-----|-----|-----|-----|-----|
| O(1) | O(1) | O(3n) => O(n) | O(n) | O(1) | O(n) | O(1) |

The TaskList's ***space complexity*** is O(2n) => O(n).

### TaskQueue
```TaskQueue``` is a PriorityQueue-like data structure implemented as a binary maximum heap. Priorities are calculated using a comparator given as a parameter.

#### Methods offered by TaskQueue

- ```add(Task t)``` - Adds the given task to the queue
- ```peek()``` - Returns the highest-priority task currently in the queue
- ```poll()``` - Returns and dequeues the highest-priority task in the queue, then restructures the heap if necessary

#### Time Complexity of TaskQueue

| add | peek | poll |
|-----|-----|-----|
| O(logn) | O(1) | O(logn) |

## Algorithms

The application implements two algorithms. Merge Sort is used to sort TaskLists to follow the Earliest Due Date and Shortest Processing Time heuristics, while minimizing the number of overdue tasks is implemented using the Moore-Hodgson Algorithm.

### Merge Sort

The Merge Sort algorithm used by the application is implemented with private methods inside the ```Scheduler``` class.

**Time Complexity:** *O(nlogn)*

**Space Complexity:** *O(n)*

### Moore-Hodgson

The Moore-Hodgson algorithm is implemented as a public method inside the ```Scheduler``` class.

#### How it works
- Sort the given list of tasks in the EDD order
- For each task on the list:
	- Add it to the TaskQueue
	- If the total number of hours required to complete every task already in the queue exceeds the number of working hours left to complete this task, **remove** the single most time consuming task from the queue (simply using ```poll()``` in this case)
- Finally, sort the tasks left in the queue again in the EDD order.

**Time Complexity:** EDD sort + Adding and removing each task from the queue + EDD sorting again = nlogn + n*(logn+logn) + nlogn = *O(nlogn)*

## References
- Lecture materials for 'Tietorakenteet ja algoritmit', spring 2018
- Christian, B. & Griffiths, T. : *Algorithms to Live By - The Computer Science of Human Decisions*, pp. 105-113, Harper Collins (2016)
- [Lawler, E. L. : *Knapsack-Like Scheduling Problems, the Moore-Hodgson Algorithm and the 'Tower of Sets' Property*, Mathematical and Computer Modelling, vol. 20, issue 2, pp. 91-106, Elsevier (1994)](https://www.sciencedirect.com/science/article/pii/0895717794902097)
- https://en.wikipedia.org/wiki/Priority_queue
- https://en.wikipedia.org/wiki/Binary_heap
