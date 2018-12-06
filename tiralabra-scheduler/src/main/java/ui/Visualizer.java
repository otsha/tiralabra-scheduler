package ui;

import access.IOHandler;
import data.Task;
import data.TaskList;
import data.TaskQueue;
import static java.lang.Thread.sleep;
import java.util.Comparator;
import logic.EDDComparator;
import logic.SPTComparator;
import logic.WeightedSPTComparator;

public class Visualizer {

    private IOHandler io;
    private TaskQueue queue;
    private EDDComparator eddComp;
    private SPTComparator sptComp;
    private WeightedSPTComparator wsptComp;

    public Visualizer(IOHandler io) {
        this.io = io;
        this.eddComp = new EDDComparator();
        this.sptComp = new SPTComparator();
        this.wsptComp = new WeightedSPTComparator();
    }

    private void pause(long amount) {
        try {
            sleep(amount);
        } catch (Exception e) {
            io.output().printError(Message.SLEEPINGERROR);
        }
    }

    /**
     * Visualize the operations taken by the merge sort algorithm in order to
     * sort the Tasks in the EDD order
     *
     * @param list - The TaskList to be scheduled
     * @see logic.Scheduler#edd(data.TaskList)
     */
    public void edd(TaskList list) {
        mergeSort(list, eddComp);
        pause(1000);
    }

    /**
     * Visualize the operations taken by the merge sort algorithm in order to
     * sort the Tasks in the SPT order
     *
     * @param list - The TaskList to be scheduled
     * @see logic.Scheduler#spt(data.TaskList)
     */
    public void spt(TaskList list) {
        mergeSort(list, sptComp);
        pause(1000);
    }

    /**
     * Visualize the operations taken by the merge sort algorithm in order to
     * sort the Tasks in the wSPT order
     *
     * @param list - The TaskList to be scheduled
     * @see logic.Scheduler#wspt(data.TaskList)
     */
    public void wspt(TaskList list) {
        mergeSort(list, wsptComp);
        pause(1000);
    }

    /**
     * Visualize the operations taken by the Moore-Hodgson algorithm.
     *
     * @param tasks - The TaskList to be scheduled
     * @see logic.Scheduler#mooreHodgson(data.TaskList)
     */
    public void mooreHodgson(TaskList tasks) {
        io.output().println(io.output().colorString("PREPARING: SORTING BY EARLIEST DUE DATE", Color.INFO));
        pause(1000);

        tasks = mergeSort(tasks, eddComp);

        io.output().println("----------------");
        io.output().println(io.output().colorString("MOORE-HODGSON:", Color.INFO));
        pause(1000);
        queue = new TaskQueue(tasks.size(), sptComp);

        // Moore-Hodgson
        int totalTime = 0;
        TaskList overdue = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            queue.add(t);
            io.output().println(io.output().colorString("TASK " + t.getName() + " scheduled.", Color.SUCCESS)); // Log scheduled tasks
            pause(500);
            totalTime += t.getTimeEstimate();

            if (totalTime > t.daysRemaining() * 8) {
                Task removed = queue.poll();
                totalTime -= removed.getTimeEstimate();

                io.output().println(io.output().colorString("Task " + removed.getName() + " removed.", Color.ERROR)); // Log removed tasks
                overdue.add(removed);
                pause(500);
            }
        }

        pause(1000);

        // Print out the data
        io.output().println(io.output().colorString("PREPARING: SORTING AGAIN BY EARLIEST DUE DATE", Color.INFO));
        pause(1000);

        TaskList schedule = new TaskList();
        while (queue.peek() != null) {
            schedule.add(queue.poll());
        }

        schedule = mergeSort(schedule, eddComp);

        io.output().hLine();
        io.output().println(io.output().colorString("SCHEDULING COMPLETE", Color.SUCCESS));
        io.output().hLine();
        pause(1000);

        io.output().println(io.output().colorString("OVERDUE TASKS:", Color.ERROR));
        for (int i = 0; i < overdue.size(); i++) {
            Task o = overdue.get(i);
            if (o.getName().length() >= 8) {
                io.output().println(o.getName().substring(0, 7) + "...\t\t" + o.getDeadline() + "\t\t\t" + o.getTimeEstimate());
            } else {
                io.output().println(o.getName() + "\t\t\t" + o.getDeadline() + "\t\t\t" + o.getTimeEstimate());
            }
            pause(500);
        }
        io.output().hLine();

        io.output().println(io.output().colorString("SCHEDULE:", Color.SUCCESS));
        for (int i = 0; i < schedule.size(); i++) {
            Task t = schedule.get(i);
            if (t.getName().length() >= 8) {
                io.output().println(t.getName().substring(0, 7) + "...\t\t" + t.getDeadline() + "\t\t\t" + t.getTimeEstimate());
            } else {
                io.output().println(t.getName() + "\t\t\t" + t.getDeadline() + "\t\t\t" + t.getTimeEstimate());
            }
            pause(500);
        }
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

        io.output().println("========");
        io.output().println(io.output().colorString("MERGING", Color.INFO));
        io.output().println("========");
        io.output().println("Left:");
        io.output().printList(left, Color.SUCCESS);
        io.output().println("Right:");
        io.output().printList(right, Color.WARNING);
        io.output().println("Merged:");
        TaskList merged = merge(left, right, comp);
        pause(1000);

        return merged;
    }

    private TaskList merge(TaskList list1, TaskList list2, Comparator<Task> comp) {
        TaskList merged = new TaskList();

        while (list1.size() > 0 && list2.size() > 0) {
            if (comp.compare(list1.get(0), list2.get(0)) <= 0) {
                merged.add(list1.get(0));
                io.output().print("[" + io.output().colorString(list1.get(0).getName(), Color.SUCCESS) + "]");
                list1.remove(0);
            } else {
                merged.add(list2.get(0));
                io.output().print("[" + io.output().colorString(list2.get(0).getName(), Color.WARNING) + "]");
                list2.remove(0);
            }
        }

        while (list1.size() > 0) {
            merged.add(list1.get(0));
            io.output().print("[" + io.output().colorString(list1.get(0).getName(), Color.SUCCESS) + "]");
            list1.remove(0);
        }

        while (list2.size() > 0) {
            merged.add(list2.get(0));
            io.output().print("[" + io.output().colorString(list2.get(0).getName(), Color.WARNING) + "]");
            list2.remove(0);
        }

        io.output().println("\n");
        return merged;
    }

}
