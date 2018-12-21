# Testing

## Index
- [Unit Testing](#unit-testing)
	- [Instructions](#instructions)
	- [What is being tested?](#what-is-being-tested)
		- [Data](#data)
		- [Logic](#logic)
		- [Access](#access)
- [Performance Testing](#performance-testing)
	- [The Procedure](#the-procedure)
		- [Preparation](#preparation)
		- [Tests](#tests)
	- [Results](#results)

## Unit Testing

[![Build Status](https://travis-ci.org/otsha/tiralabra-scheduler.svg?branch=master)](https://travis-ci.org/otsha/tiralabra-scheduler)

[![codecov](https://codecov.io/gh/otsha/tiralabra-scheduler/branch/master/graph/badge.svg)](https://codecov.io/gh/otsha/tiralabra-scheduler)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d0ab491418a2477d99c32a23c1d9e566)](https://www.codacy.com/app/otsha/tiralabra-scheduler?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=otsha/tiralabra-scheduler&amp;utm_campaign=Badge_Grade)

### Instructions
***CI testing is performed by Travis, CodeCov and Codacy. To examine the test results without downloading anything, click any of the badges above.***

If you do not have Gradle installed, the application comes with the necessary wrappers. In this case, replace ```gradle``` with ```./gradlew```.

**jUnit** tests can be performed in the Gradle project's root folder ```tiralabra-scheduler/tiralabra-scheduler``` with the command:
```
gradle test
```
To generate the jacoco reports:
```
gradle test jacocoTestReport
```
The report can then be found at:
```
build/reports/jacoco/test/index.html
```

### What is being tested?
The unit tests test the contets of the ```data```, ```logic``` and ```access``` packages (an exception being the ```Printer``` class within the last - more explanation below). The user interface is not being tested.

#### Data
The data structured used by the software are tested by ```TaskTest```, ```TaskListTest``` and ```TaskQueueTest```. Testing concerning the ```Task``` class is fairly straightforward. ```TaskListTest``` tests the basic operations of adding, removing and fetching from the list, in addition to validating that the the capacity increasing/decreasing capability works as expected. ```TaskQueueTest``` verifies that adding and removing works correctly, and that peeking and polling return null if the queue is empty.

#### Logic
```EDDComparatorTest```, ```SPTComparatorTest``` and ```WeightedSPTComparatorTest``` verify that comparisons are done correctly. ```DateHandlerTest``` confirms that date validations are correct (and return ```null``` when applicable), that the date format is correct and that the differences between two dates are calculated correctly.

```SchedulerTest``` inputs a list of predefined tasks to the different scheduling methods, and confirms that the scheduling results are as expected.

#### Access
The ```IOHandler``` class is a very simple parent class for accessing the ```FileAccess```, ```Parser``` and ```Printer``` I/O classes - thus its tests are simple getter tests. The ```FileAccess``` class is tested by ```FileAccessTest```, which verifies that the permanently stored information is written and read correctly. The ```Parser``` class is tested by ```ParserTest```, that confirms that user inputs are read correctly and that tasks are built correctly when the user inputs valid parameters, or nulled when the parameters are invalid.

The ```Printer``` class is not tested and is excluded from the Jacoco test report, as all but one of its methods are ```void```, and directly related to printing data to the console. The functioning of this class can be directly seen from something not being displayed as wanted in the app, and it has thus been tested **manually**.

## Performance Testing

The performance tests were performed by creating a custom ```Main.java``` and using the total run times repored by Gradle.

### The Procedure

#### Preparation

- Firstly, create a ```Main.java``` that generates **n** tasks and saves them to a file.

```java
TaskList tasks = new TaskList();

for (int i = 0; i < n; i++) {
	Random r = new Random();
	
	/*
	- Create a new pseudorandomly generated Task.
	- The name of the task is irrelevant for performance testing, as tasks are never sorted by name.
	- Deadline dates are between 1.1.2019 and the end of 2028.
	- The 29th-31st of each month are excluded to avoid excessive spaghetti code to accommodate for February.
	*/
	tasks.add(new Task(
		"test",
		Math.abs(r.nextDouble() * r.nextInt(10000)),
		(r.nextInt(27) + 1) + "." + (r.nextInt(11) + 1) + "." + (r.nextInt(10) + 2019),
		r.nextInt(3000))
	);
}

FileAccess access = new FileAccess("tasks.json");
access.write(tasks);
```
- This is repeated for each value of n (10, 100, 1000, 10000). The same n-sized list is used for each algorithm for fair comparison.

#### Tests

- Replace the ```Main``` so that the previously generated list of tasks is read from the file, after which one of the scheduling algorithms is ran.
- The running time is logged using Java's ```System.nanoTime()```
	- At the start of the run
	- After the TaskList is read from the file
	- After the algorithm is finished

```java
long start = System.nanoTime();

FileAccess access = new FileAccess("tasks.json");
TaskList tasks = access.read();

long loaded = System.nanoTime();

Scheduler s = new Scheduler();
s.ALGORITHM_HERE(tasks);

long done = System.nanoTime();
System.out.println("Algorithm runtime: " + (done - loaded) + " nanoseconds");
```

- Clean and build once, then run 10 times and collect the reported values.
- Repeat for all 4 scheduling methods and each value of n (10, 100, 1000, 10000).

### Results

***[Spreadsheet of the raw test data](https://docs.google.com/spreadsheets/d/1lkpz7VGbgFzrs4uXh5jAJO8rxlQXugSaENhyu46kJRs/edit?usp=sharing)***

***Note:** The collected data has been converted from nanoseconds to milliseconds for better readability. The following graph and table are based on average reported runtimes.*

| Algorithm | n = 10 | n = 100 | n = 1000 | n = 10 000 |
|-----------|--------|---------|----------|------------|
| EDD | 42.34 ms | 220.46 ms | 457.49 ms | 1415.76 ms |
| SPT | 1.62 ms | 2.41 ms | 11.87 ms | 104.47 ms |
| wSPT| 1.81 ms | 2.52 ms | 12.59 ms | 88.65 ms |
| Moore-Hodgson| 54.06 ms | 193.31 ms | 405.09 ms | 1345.89 ms |

![Performance test results as a bar chart](https://raw.githubusercontent.com/otsha/tiralabra-scheduler/master/Documentation/Performance.png)

An interesting phenomenon is that even though all the heuristics (EDD, SPT and wSPT) use the exact same Merge Sort methods (inside the ```logic.Scheduler``` class, EDD scheduling is slower. This might be due to Java's native Date class's comparisons being slower, or due to not enough variation in the test data in terms of processing times. However, the latter seems less likely as I tried using several different limits for processing limit generation with similar results each time.

Another detail to note is that my implementation of the Moore-Hodgson Algorithm uses EDD sort twice, once as a prerequisite to the actual algorithm (the tasks must be inputted in the EDD order) and again after the optimal list of tasks is generated (to decide the final schedule). Interestingly, it still seems to take less time than simply just EDD sorting with every sample size.
