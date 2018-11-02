# Description

**Topic:** Task scheduler

**Implementation:** Java

## The Problem
A freelancer has x hours of working time with n tasks to complete. Each task is estimated to take a certain amount of time and has a some kind of monetary value attached to it. Which tasks should the freelancer complete to maximize their profits?

## The Algorithm
The problem above can be solved using the optimal known solution for the **0/1 Knapsack problem**, which has a worst-case time and space complexity scenario of **O(nW)**, where n is the total number of possible tasks to be completed, and W is the maximum weight - in this case the maximum working time - that must not be surpassed.

## Data Structures
The project will utilize the basic data structures found in Java.

## Ideas for Further Development (Bonuses)
- Comparison with computationally easier scheduling heuristics, such as:
	- Earliest Due Date
	- Shortest Processing Time
	- Moore-Hodgson Algorithm
	
**Note:** *The preceding would require implementing a sorting algorithm and a deadline system!*
- Multiple objectives: E.g. Try to maximize profits while minimizing stress levels.

## References
- https://en.wikipedia.org/wiki/Knapsack_problem
- http://www.es.ele.tue.nl/education/5MC10/Solutions/knapsack.pdf
- https://www.tutorialspoint.com/design_and_analysis_of_algorithms/design_and_analysis_of_algorithms_01_knapsack.htm
- https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
