# Report for Week #2

**Hours spent on the project:** ~10

## What I did
This week, I began working on the core functionality of the project, namely, scheduling tasks with the Moore-Hodgson algorithm. First, I implemented the ability to create and delete tasks with a (somewhat crude) command-line interface. Then, I spent some time figuring out the scheduling algorithm itself, in the end implementing it using the PriorityQueue and ArrayList data structures provided by Java. I've been documenting my code with both JavaDoc and regular commentary along the way.

I wrote some jUnit tests for the ```Task```, ```TaskComparator``` and ```Scheduler``` classes. However, some of the tests will have to be changed - if not removed entirely - as I further separate functionality into distinct classes.

The jacoco test coverage is tracked with CodeCov and can be viewed [at the top of the Readme document](https://github.com/otsha/tiralabra-scheduler/blob/master/README.md).

## Difficulties
Understanding the intricacies of the Moore-Hodgson algorithm was likely the most difficult part this week. For example, by default the algorithm does not provide an optimal order for the tasks - it just tells us which tasks we can and can't finish in time. The resulting tasks should be processed in the earliest due date order to get the optimal schedule.

I also realized that unlike originally planned, I **will have to** implement my own versions of a sorting algorithm and an arraylist-like data structure. This should actually, though, be a good thing, as it adds some complexity to the project - something I was concerned by last week.

## What's next?
Finish implementing the core functionality:
- Add separate commands for EED, SPT and weighed SPT orders.
- Separate Date handling/comparison to its own class
- Separate the UI to its own class

***(Maybe)*** begin working on either a sorting algorithm or my own implementation of the arraylist data structure.
