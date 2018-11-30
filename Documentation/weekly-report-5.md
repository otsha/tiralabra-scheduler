# Report for Week #5

**Hours spent on the project:** ~8

## What I did
I began this week's work by fixing a few very important deficiencies that I had missed completely. These were including the 'application' task for Gradle - which is *fairly* important if the user wants to, for example, **run** the application. Two other bug reports that I was unaware of came from the peer review issue - the application asked the user to format their dates differently from what the code in fact required, and reading tasks from an empty file resulted in a ```null``` task being fetched, resulting in application crashes, as it is not possible to compare a ```Task``` with a ```null```.

I also began working on separating I/O from the text interface. To do this, I opted to create two new bottom-tier classes: ```Parser``` to read user inputs and ```Printer``` to handle outputs. These and the pre-existing ```FileAccess``` class are now accessed through a simple parent class, ```IOHandler```. 

Now, for example, printing a new line happens by calling:
```java
IOHandler.output().println("Hello world!");
```
Reading user input:
```java
IOHandler.input().next()
```
Writing to a file:
```java
IOHandler.file().write(TaskList list)
```

I began figuring out/working on visualizing how the software works. Currently, the command ```e``` shows the user how merge sort works its way through EDD scheduling by combining the split pieces to form an order.

Finally, I wrote a user manual document which can be found [here](https://github.com/otsha/tiralabra-scheduler/blob/master/Documentation/usermanual.md).

## Difficulties
At the beginning of this week, I was running out of ideas. The core of the application is basically done - I have implemented both the required algorithms and data structures. I decided to start working on visualizing the algorithms, but I'm still not quite sure how this could (and should) be done.

## What's next?
- Continue working on figuring out/implementing algorithm visualization

The following tasks are carried over from last week's list:
- Allow for comparisons of I.E. lateness and total value between schedules
