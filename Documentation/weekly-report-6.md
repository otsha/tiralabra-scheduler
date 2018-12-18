# Report for Week #6

**Hours spent on the project:** ~12

## What I did
I continued working on algorithm visualization, which I began last week. The application now contains a rough implementation for every scheduling method. A sub-menu for these can be entered by inputting ```e``` in the main menu. In addition, I continued improving the readability of the application's user interface.

Next, I began trying to implement comparisons between the different scheduling methods. This proved to be more difficult than expected. While the formula for calculating a task's lateness is simple (```time of reception - deadline```), it becomes more complex when the working hours (in other words, working at most 8 hours a day) are taken into account. It does not help that Java's Date convertions are not the most accurate. I spent eight to nine hours this week on this task alone, and ended up with nothing to show for it.

After deciding my time was better spent doing something else for now, I decided to conduct some performance testing. Further reports regarding what I did, how, and what the results were can be found [in the testing document](https://github.com/otsha/tiralabra-scheduler/blob/master/Documentation/testing.md).

## Difficulties
While my inability to figure out the comparisons was certainly a disappointment, it also gave me a valuable lesson: I should have worked with a custom time/calendar format from the very beginning. It might be too late to implement something like that in this project, but I will undoubtedly keep that in mind in the future. 

## What's next?

- Finishing touches
	- Restructure / refactor the UI class
	- Make sure all documentation is up to date
	- Include JavaDoc
	- Final release
	
- **(If possible)** Figure out how to allow comparisons of I.E. lateness and total value between schedules
