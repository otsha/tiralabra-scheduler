# Description

**Topic:** Task scheduling analyzer

**Implementation:** Java

## The Problem
A freelancer has n work-related tasks or projects to complete. Each task has a deadline, is estimated to take a certain amount of time and has a some kind of monetary value attached to it. In which order should the freelancer complete the tasks?

## The Solution
There is no single optimal solution for the problem, as it can be observed with multiple different objectives in mind. It can, however, be modeled as a case of single-machine scheduling. Thus, we can either:

- Minimize the lateness of the single most overdue project
- Minimize the overall lateness of all projects in total
- Complete the highest-value tasks first
- Minimize the number of projects that will be overdue

## The Software
The application should allow the user to:
	
- [ ] Add and remove tasks
- [ ] Schedule tasks using one of the optimization methods
- [ ] View and compare schedules produced by different optimization methods, with a focus on comparing the Moore-Hodgson Algorithm to the simpler heuristics.

## The Algorithm(s)
As mentioned, the problem can be examined as a case of single-machine scheduling. To optimize for each of the four different perspectives above, we will use:

- Earliest Due Date (EDD)
	- Sort the tasks in an ascending order from the earliest deadline to the last deadline
- Shortest Processing Time (SPT)
	- Sort the tasks in an ascending order from the quickest to the slowest in terms of completion time
- Weighed Shortest Processing Time
	- Similar to SPT: First, each task is assigned a standardized value (hourly rate). Then, the tasks are picked in a descending order from the highest value to the lowest.
- Moore-Hodgson Algorithm
	- Sort the tasks in an ascending order (like in EDD), then go through them, and whenever a new task is encountered that is likely to be left unfinished, 'discard' the most time-demanding already scheduled task.

As is evident from these short explanations, the first three are simple (but optimal) *heuristics* - **the Moore-Hodgson algorithm is the star of the show**. For EDD and SPT, the time complexities will be those of the sorting algorithm used. If we decide to use priority queues, the time complexity will be O(```n * heap-insert```) = O(nlogn).

As Moore-Hodgson's algorithm requires the use of a priority queue, its worst-case time complexity is also O(nlogn).

## Data Structures
The list of tasks used by EDD and SPT can be implemented with either priority queues or lists sorted by a sorting algorithm. However, the Moore-Hodgson Algorithm explicitly requires a priority queue. Therefore the wisest solution would be to only implement a priority queue. As the project progresses, this might change. **Especially, if the project needs additional complexity, using sorted lists for EDD and SPT would add exactly that.**

The worst-case scenario time complexities for a priority queue implemented with a binary heap:

- ```find-min/max``` -> O(1)
- ```delete-min/max``` -> O(logn)
- ```insert``` -> O(logn)

There should be no need for the ```decrease-key``` or ```merge``` operations.

## References
- https://en.wikipedia.org/wiki/Single-machine_scheduling
- Christian, B. & Griffiths, T. : *Algorithms to Live By - The Computer Science of Human Decisions*, pp. 105-113, Harper Collins (2016)
- [Lawler, E. L. : *Knapsack-Like Scheduling Problems, the Moore-Hodgson Algorithm and the 'Tower of Sets' Property*, Mathematical and Computer Modelling, vol. 20, issue 2, pp. 91-106, Elsevier (1994)](https://www.sciencedirect.com/science/article/pii/0895717794902097)
- https://en.wikipedia.org/wiki/Priority_queue
- https://en.wikipedia.org/wiki/Binary_heap
