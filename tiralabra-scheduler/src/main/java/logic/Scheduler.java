package logic;

import data.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {

    private List<Task> tasks;
    private Queue<Task> queue;

    public Scheduler(List<Task> tasks) {
        this.tasks = tasks;
        this.queue = new PriorityQueue<>();
    }

    /**
     * Minimizes the number of tasks that will be overdue using the
     * Moore-Hodgson Algorithm.
     *
     * @todo Implementation using own data structures 
     * 
     * @return The Scheduled list of tasks.
     */
    
    public List<Task> mooreHodgson() {
        System.out.println("MOORE-HODGSON:");
        // Moore-Hodgson
        // TO-DO: Implement using own data structures
        int totalTime = 0;
        List<Task> overdue = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            queue.add(t);
            System.out.println("Task " + t.getName() + " scheduled."); // Log scheduled tasks
            totalTime += t.getTimeEstimate();

            // 8-hour work days
            // Weekends and holidays could be taken into account, but that would likely
            // be very complicated.
            // Ideally, this application should be used to schedule a 5-day work week.
            if (totalTime > t.daysRemaining() * 8) {
                Task removed = queue.poll();
                totalTime -= removed.getTimeEstimate();

                System.out.println("Task " + removed.getName() + " removed."); // Log removed tasks (will be overdue)
                overdue.add(removed);
            }
        }

        // Print out the data
        // By default, the Moore-Hodgson algorithm does not take into account anything else
        // than that the tasks can be completed within their deadlines.
        // It does not provide assistance with the order in which they should be done.
        // However, if we sort the scheduled tasks again with in the EDD order,
        // none should be overdue.
        List<Task> schedule = new ArrayList<>();
        while (queue.peek() != null) {
            schedule.add(queue.poll());
        }
        Collections.sort(schedule, new EDDComparator());

        System.out.println("----------------");
        System.out.println("SCHEDULING COMPLETE");
        System.out.println("----------------");
        System.out.println("OVERDUE TASKS:");
        for (int i = 0; i < overdue.size(); i++) {
            Task o = overdue.get(i);
            System.out.println(o.getName() + " // " + o.getDeadline());
        }
        System.out.println("----------------");
        System.out.println("SCHEDULE:");
        for (int i = 0; i < schedule.size(); i++) {
            Task t = schedule.get(i);
            System.out.println(t.getName() + " // " + t.getDeadline());
        }
        return schedule;
    }
}
