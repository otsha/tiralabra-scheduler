# User Manual

# Index
- [Getting Started](#getting-started)
	- [Running from a .jar](#running-from-a-jar)
	- [Running with Gradle](#running-with-gradle)
- [Using the Application](#using-the-application)
	- [Managing Tasks](#managing-tasks)
		- [Creating a New Task](#creating-a-new-task)
		- [Deleting a Task](#deleting-a-task)
		- [Saving and Loading Tasks](#saving-and-loading-tasks)
	- [Scheduling](#scheduling)
		- [Moore-Hodgson Scheduling](#moore-hodgson-scheduling)
		- [Earliest Due Date](#scheduling-by-earliest-due-date)
		- [Shortest Processing Time](#scheduling-by-shortest-processing-time)
		- [Hourly Rate](#scheduling-by-hourly-rate)
	- [Visualization](#visualization)
		- [EDD, SPT and wSPT Scheduling](#visualizing-edd-spt-and-wspt-scheduling)
		- [Moore-Hodgson Scheduling](#visualizing-moore-hodgson-scheduling)
	

# Getting Started
***To run this application, you must have Java 8 installed on your system. Moreover, the application has only been tested on Linux - you may encounter issues on other platforms***

## Running from a .jar
You can either download the latest version on the ['Releases' page](https://github.com/otsha/tiralabra-scheduler/releases) or generate your own .jar by using the command 
```
gradle shadowJar
```
in the application's root folder.

***Note**: If you do not have Gradle installed on your system, the application files include wrappers. Try using ```./gradlew``` instead of ```gradle```.*

The generated .jar file will be located in
```
build/libs/tiralabra-scheduler-all.jar
```

Once you have a .jar file, you can execute it with
```
java -jar NAME_OF_YOUR_FILE.jar
```

## Running with Gradle
Navigate to the root folder of the Gradle project (```/tiralabra-scheduler/tiralabra-scheduler```). To run the application, use the command
```
gradle run
```
***Note**: If you do not have Gradle installed on your system, the application files include wrappers. Try using ```./gradlew``` instead of ```gradle```.*

# Using the Application

*You can view a list of all available commands by writing ```help``` once the application has launched*

## Managing Tasks

### Creating a New Task

Use the command ```1```. You will be prompted to add the details of a task:
- Name
- Payment (The payment received upon completion of the task)
- Deadline (When the task is due in DD.MM.YYYY format)
- Time Estimate (Approximately how many hours will this task take)

If all the values entered were valid, a new task is created - but not yet permanently saved! See [Saving](#saving) for information on how to prevent your tasks from being lost once you close the application.

If a value vas invalid (incorrect date format, payment or time estimate not a number), you will see an error message. No tasks are created in this case - please try again with correct values.

### Deleting a Task

Use the command ```2```. You will be asked the name of the task you wish to delete. If you enter a valid name, all tasks with that specific name will be deleted. Another way could be opening the automatically generated ```tasks.json``` file in a text editor and deleting the line containing the task you wish to delete. *You must have saved your tasks for them to be shown in the file.*

### Viewing All Tasks

Use the command ```v```. The details of all tasks will be printed out.

### Saving and Loading Tasks

Tasks are loaded from the automatically generated ```tasks.json``` file on startup. New tasks and deletions must be saved manually by using the command ```s```.

## Scheduling

*For more technical information regarding how the application handles scheduling, please refer to the [implementation document](https://github.com/otsha/tiralabra-scheduler/blob/master/Documentation/implementation.md).*

### Moore-Hodgson Scheduling

Use the command ```3```. Your tasks will be scheduled so that the number of tasks that will be overdue is minimized. It does not, however, tell how late those overdue tasks will be.

### Scheduling by Earliest Due Date

Use the command ```4```. Your tasks will be scheduled so that the task with the earliest deadline will be first. This method minimizes how late the single most overdue task will be.

### Scheduling by Shortest Processing Time

Use the command ```5```. Your tasks will be scheduled so that the task with the lowest time estimate is scheduled first. This method minimizes the overall lateness of all tasks combined.

### Scheduling by Hourly Rate

Use the command ```6```. Your tasks will be scheduled so that the task with the highest hourly rate is scheduled first. This method prioritizes financial gain over lateness.

## Visualization

Use the command ```e```. You will be shown a list of commands for visualizing the different scheduling methods offered by the application. Please note that the visualization methods artificially slow down the algorithm output in order to make them easier to follow.

### Visualizing EDD, SPT and wSPT Scheduling

Use the command corresponding to the scheduling method. This will visualize the merge sort algorithm that is used for sorting tasks by earliest due date by showing the broken down pieces of the list of tasks and how they are merged together to form a sorted list.

### Visualizing Moore-Hodgson Scheduling

Use the command ```4```. This will visualize the steps taken by the Moore-Hodgson algorithm:

- Merge sorting by earliest due date
- Adding tasks to the schedule
- Removing certainly overdue tasks from the schedule
- Sorting again by earliest due date to form the final schedule
- Printing out the overdue tasks then the final schedule
