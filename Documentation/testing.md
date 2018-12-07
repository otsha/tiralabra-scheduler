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

- Create a ```Main.java``` that creates **n** tasks and saves them to a file.

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
		r.nextInt(300))
	);
}

FileAccess access = new FileAccess("tasks.json");
access.write(tasks);
```

- Replace the ```Main.java``` with one that reads the generated tasks from the file and adds them to a TaskList.

```java
FileAccess access = new FileAccess("tasks.json");
TaskList tasks = access.read();
```

- Build once, then run 10 times and form an average initialization time used as a zero value for the actual tests.

#### Tests

- Now, *edit* the Main so that after the TaskList is built, one of the scheduling algorithms is ran.

```java
FileAccess access = new FileAccess("tasks.json");
TaskList tasks = access.read();
Scheduler s = new Scheduler();
s.ALGORITHM_HERE(tasks);
```

- Build once (after cleaning), then run 10 times, each time deducing the zero value from the running time, and form an average running time. This is the performance of the algorithm.
- Repeat for all 4 scheduling methods.

### Results

- ***NOTE:** For the n = 100 000 test the tasks were not saved into a permanent file, as it did not seem to be possible. Instead, the zero value is the average time taken to generate a hundred thousand tasks. Thus, the 'init' row does not represent a unified initialization technique, but rather a method of zeroing for each column.*

| Algorithm | n = 10 | n = 100 | n = 1000 | n = 10 000 | n = 100 000 |
|-----------|--------|---------|----------|------------|-------------|
| Init | 0 | 0 | 0 | 0 | 0 |
| EED | 0 | 0 | 0 | 1.5s | 14s |
| SPT | 0 | 0 | 0 | 0 | 4s |
| wSPT| 0 | 0 | 0 | 0 | 4s | 
| Moore-Hodgson| 0 | 0 | 0.81s | 2s | 14.1s |

Even though the run times up to n = 1000 were still below 0 seconds, the run time did slow down to the point where it was noticeably slower than for smaller values of n. Unfortunately, Gradle only reports running times in seconds. A millisecond comparison would have been much more enlightening.

Another interesting phenomenon is that even though all the heuristics (EDD, SPT and wSPT) use the same Merge Sort algorithm (no multiple implementations), EDD scheduling is slower. This might be due to Java's native Date class's comparisons being slower, or due to not enough variation in the test data in terms of processing times. Of these two the latter does seem more likely - after all, we're setting the due dates to (almost) any day in the next 10 years, but the processing times are at most 300 hours.
