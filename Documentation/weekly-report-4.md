# Report for Week #4

**Hours spent on the project:** ~10

## What I did
This week I added the finishing touches to the core functionality of the software by finally implementing shortest processing time scheduling weighted with payment. Essentially this simply means sorting tasks by their hourly rate, highest to lowest.

Since last week, I realized that I had completely forgotten saving information locally. After all, it would be useful if the user could somehow store their tasks permanently, instead of having to input them every time the application is opened. I decided to approach this by storing tasks in jSon format to a locally created text file. While not necessarily the optimal solution, especially compared to using an SQL database, I chose this approach mostly due to a) not having done it before with a project and b) already having done a few DAO-oriented applications before. Figuring the ins and outs of this did take some time.

The second large task this week was replacing Java's PriorityQueue with an implementation of my own. Revising my knowledge of the heap data structure did take some time, but in the end I feel like I now understand the topic far better than I did initially during the course last spring.

Finally, I began writing the implementation and testing documentation.

## Difficulties
Both the data storing functionality and the priority queue implementation caused some issues - that were mostly solved in the end. The data storage remains fairly inefficient, considering that the entire list of tasks has to be written at once (PrintWriter overwrites everything).

## What's next?
- Improve the testing of both TaskList and TaskQueue

The following tasks are carried over from last week's list:
- Create a separate input validator class
- Allow for comparisons of I.E. lateness and total value between schedules
- ***(Maybe)*** begin planning a visual interface or at least determine what would be a decent approach - if one is simple enough to implement within the scope of this course to begin with.
