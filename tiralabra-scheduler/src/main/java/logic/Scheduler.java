package logic;

import data.Task;
import data.TaskList;
import data.TaskQueue;
import java.util.Comparator;

public class Scheduler {

    private TaskQueue queue;
    private EDDComparator eddComp;
    private SPTComparator sptComp;
    private WeightedSPTComparator wsptComp;

    public Scheduler() {
        this.eddComp = new EDDComparator();
        this.sptComp = new SPTComparator();
        this.wsptComp = new WeightedSPTComparator();
    }

    /**
     * Minimizes the number of tasks that will be overdue using the
     * Moore-Hodgson Algorithm.
     *
     * @return The Scheduled list of tasks.
     */
    public TaskList mooreHodgson(TaskList tasks) {
        tasks = mergeSort(tasks, eddComp);
        queue = new TaskQueue(tasks.size(), sptComp);

        // Moore-Hodgson
        int totalTime = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            queue.add(t);
            totalTime += t.getTimeEstimate();

            // 8-hour work days
            // Weekends and holidays could be taken into account, but that would likely
            // be very complicated.
            // Ideally, this application should be used to schedule a 5-day work week on the starting day
            if (totalTime > t.daysRemaining() * 8) {
                Task removed = queue.poll();
                totalTime -= removed.getTimeEstimate();
            }
        }

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

        return schedule;
    }

    /**
     * Sorts the inputted list of Tasks to follow the Earliest Due Date
     * scheduling heuristic using MergeSort.
     *
     * @param list - The list of Tasks to be sorted
     * @return The inputted list in the EDD order
     */
    public TaskList edd(TaskList list) {
        TaskList schedule = mergeSort(list, eddComp);
        return schedule;
    }

    /**
     * Sorts the inputted list of Tasks to follow the Shortest Processing Time
     * scheduling heuristic using MergeSort.
     *
     * @param list - The list of Tasks to be sorted
     * @return The inputted list in the SPT order
     */
    public TaskList spt(TaskList list) {
        TaskList schedule = mergeSort(list, sptComp);
        return schedule;
    }

    /**
     * Sorts the inputted list of Tasks to follow the Shortest Processing Time
     * scheduling heuristic weighted with their payment - in other words, the
     * greatest hourly rate gets the highest priority, while the lowest hourly
     * rate gets the lowest priority.
     *
     * @param list
     * @return The inputted list in order of hourly rate, greatest first.
     */
    public TaskList wspt(TaskList list) {
        TaskList schedule = mergeSort(list, wsptComp);
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
            if (comp.compare(list1.get(0), list2.get(0)) <= 0) {
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
