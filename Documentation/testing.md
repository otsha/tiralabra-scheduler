# Testing

[![Build Status](https://travis-ci.org/otsha/tiralabra-scheduler.svg?branch=master)](https://travis-ci.org/otsha/tiralabra-scheduler)

[![codecov](https://codecov.io/gh/otsha/tiralabra-scheduler/branch/master/graph/badge.svg)](https://codecov.io/gh/otsha/tiralabra-scheduler)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d0ab491418a2477d99c32a23c1d9e566)](https://www.codacy.com/app/otsha/tiralabra-scheduler?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=otsha/tiralabra-scheduler&amp;utm_campaign=Badge_Grade)

## Instructions
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

## What is being tested?
The unit tests test the contets of the ```data```, ```logic``` and ```access``` packages (an exception being the ```Printer``` class within the last - more explanation below). The user interface is not being tested.

### Data
The data structured used by the software are tested by ```TaskTest```, ```TaskListTest``` and ```TaskQueueTest```. Testing concerning the ```Task``` class is fairly straightforward. ```TaskListTest``` tests the basic operations of adding, removing and fetching from the list, in addition to validating that the the capacity increasing/decreasing capability works as expected. ```TaskQueueTest``` verifies that adding and removing works correctly, and that peeking and polling return null if the queue is empty.

### Logic
```EDDComparatorTest```, ```SPTComparatorTest``` and ```WeightedSPTComparatorTest``` verify that comparisons are done correctly. ```DateHandlerTest``` confirms that date validations are correct (and return ```null``` when applicable), that the date format is correct and that the differences between two dates are calculated correctly.

```SchedulerTest``` inputs a list of predefined tasks to the different scheduling methods, and confirms that the scheduling results are as expected.

### Access
The ```IOHandler``` class is a very simple parent class for accessing the ```FileAccess```, ```Parser``` and ```Printer``` I/O classes - thus its tests are simple getter tests. The ```FileAccess``` class is tested by ```FileAccessTest```, which verifies that the permanently stored information is written and read correctly. The ```Parser``` class is tested by ```ParserTest```, that confirms that user inputs are read correctly and that tasks are built correctly when the user inputs valid parameters, or nulled when the parameters are invalid.

The ```Printer``` class is not tested and is excluded from the Jacoco test report, as all but one of its methods are ```void```, and directly related to printing data to the console. The functioning of this class can be directly seen from something not being displayed as wanted in the app, and it has thus been tested **manually**.
