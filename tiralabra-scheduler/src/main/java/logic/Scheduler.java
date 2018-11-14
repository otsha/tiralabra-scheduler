package logic;

import data.Task;
import data.TaskList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {

    private Queue<Task> queue;
    private EDDComparator eddComp;
    private SPTComparator sptComp;

    public Scheduler() {
        this.queue = new PriorityQueue<>();
        this.eddComp = new EDDComparator();
        this.sptComp = new SPTComparator();
    }

    /**
     * Minimizes the number of tasks that will be overdue using the
     * Moore-Hodgson Algorithm.
     *
     * @todo Implementation using own data structures
     * @todo Remove the UI printouts
     *
     * @return The Scheduled list of tasks.
     */
    public TaskList mooreHodgson(TaskList tasks) {
        System.out.println("PREPARING:");

        tasks = mergeSort(tasks, eddComp);

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).getName() + " // " + tasks.get(i).getDeadline());
        }

        System.out.println("----------------");
        System.out.println("MOORE-HODGSON:");
        
        // Moore-Hodgson
        int totalTime = 0;
        TaskList overdue = new TaskList();

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
        TaskList schedule = new TaskList();
        while (queue.peek() != null) {
            schedule.add(queue.poll());
        }
        schedule = mergeSort(schedule, eddComp);

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
    
    /**
     * Sorts the inputted list of Tasks to follow the Earliest Due Date scheduling heuristic using MergeSort.
     * 
     * @param list - The list of Tasks to be sorted
     * @return The inputted list in the EDD order
     */
    public TaskList edd(TaskList list) {
        TaskList schedule = mergeSort(list, eddComp);
        return schedule;
    }
    
    /**
     * Sorts the inputted list of Tasks to follow the Shortest Processing Time scheduling heuristic using MergeSort.
     * 
     * @param list - The list of Tasks to be sorted
     * @return The inputted list in the SPT order
     */
    public TaskList spt(TaskList list) {
        TaskList schedule = mergeSort(list, sptComp);
        return schedule;
    }

    private TaskList mergeSort(TaskList list, Comparator<Task> comp) {
        if (list.size() <= 1) {
            return list;
        }

        TaskList left = new TaskList();
        TaskList right = new TaskList();

        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() / 2) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }

        left = mergeSort(left, comp);
        right = mergeSort(right, comp);

        return merge(left, right, comp);
    }

    private TaskList merge(TaskList list1, TaskList list2, Comparator<Task> comp) {
        TaskList merged = new TaskList();

        while (list1.size() > 0 && list2.size() > 0) {
            int comparison = comp.compare(list1.get(0), list2.get(0));
            if (comparison <= 0) {
                merged.add(list1.get(0));
                list1.remove(0);
            } else {
                merged.add(list2.get(0));
                list2.remove(0);
            }
        }

        while (list1.size() > 0) {
            merged.add(list1.get(0));
            list1.remove(0);
        }

        while (list2.size() > 0) {
            merged.add(list2.get(0));
            list2.remove(0);
        }

        return merged;
    }
}
