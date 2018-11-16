# Report for Week #3

**Hours spent on the project:** ~9

## What I did
This week, I finished the core functionality of the application. This meant implementing the Earliest Due Date, Shortest Processing time and hourly rate -weighed SPT heuristics. To make the code cleaner, more readable and generally 'better', I separated date handling (parsing, comparisons) and the command line text interface to their own classes.

For EDD and SPT sorting, I decided to already implement Merge Sort as a sorting algorithm. The ```mergeSort``` and ```merge``` methods of the ```Scheduler``` class expect a list of Tasks and a comparator as their parameters, which simplifies the code, as there is no need for multiple similar methods for each heuristic.

As I still had time left, I also implemented an ArrayList-like data structure for Tasks, ```TaskList```. Essentially, it offers the basic functions of Java's ArrayList, such as adding and removing tasks, determining whether a task is present on the list, and (somewhat) dynamically increasing and decreasing the capacity of the list when required (the capacity increasing/decreasing currently happens at 1/2 capacity, but I still have to do research on what is a more optimal solution).

Checkstyle monitoring is handled by Codacy and can be accessed with the 'code quality' badge [at the top of the README document](https://github.com/otsha/tiralabra-scheduler/blob/master/README.md).

## Difficulties
I spent a couple of hours trying to configure checkstyle, following instructions from both the 'Ohjelmistotekniikka' and 'Ohjelmistotuotanto' courses, without success - getting checkstyle itself working was not an issue, but adding suppressions always resulted in errors. In the end, I ended up configuring Codacy to do the desired checks - which I fetched from the ['Ohjelmistotekniikka' course materials](https://github.com/mluukkai/Ohjelmistotekniikka2018/blob/master/web/checkstyle.md). 

## What's next?
- Implement a PriorityQueue -like data structure for Moore-Hodgson's use
- Create a separate input validator class
- Allow for comparisons of I.E. lateness and total value between schedules

***(Maybe)*** begin planning a visual interface or at least determine what would be a decent approach - if one is simple enough to implement within the scope of this course to begin with.
