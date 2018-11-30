package ui;

import access.Printer;
import data.Task;
import data.TaskList;
import static java.lang.Thread.sleep;
import java.util.Comparator;
import logic.EDDComparator;

public class Visualizer {

    private Printer printer;

    public Visualizer(Printer p) {
        this.printer = p;
    }

    private void pause(long amount) {
        try {
            sleep(amount);
        } catch (Exception e) {
            printer.printError(Message.SLEEPINGERROR);
        }
    }

    public void edd(TaskList list) {
        mergeSort(list, new EDDComparator());
        pause(1000);
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
        /*
        System.out.println("========");
        System.out.println("SORTING");
        System.out.println("========");
        pause(1000);
         */

        left = mergeSort(left, comp);
        right = mergeSort(right, comp);

        printer.println("========");
        printer.println("MERGING");
        printer.println("========");
        printer.println("Left:");
        printer.printList(left, Color.SUCCESS);
        printer.println("Right:");
        printer.printList(right, Color.WARNING);
        printer.println("Merged:");
        TaskList merged = merge(left, right, comp);
        pause(1000);

        return merged;
    }

    private TaskList merge(TaskList list1, TaskList list2, Comparator<Task> comp) {
        TaskList merged = new TaskList();

        while (list1.size() > 0 && list2.size() > 0) {
            if (comp.compare(list1.get(0), list2.get(0)) <= 0) {
                merged.add(list1.get(0));
                printer.print("[" + printer.colorString(list1.get(0).getName(), Color.SUCCESS) + "]");
                list1.remove(0);
            } else {
                merged.add(list2.get(0));
                printer.print("[" + printer.colorString(list2.get(0).getName(), Color.WARNING) + "]");
                list2.remove(0);
            }
        }

        while (list1.size() > 0) {
            merged.add(list1.get(0));
            printer.print("[" + printer.colorString(list1.get(0).getName(), Color.SUCCESS) + "]");
            list1.remove(0);
        }

        while (list2.size() > 0) {
            merged.add(list2.get(0));
            printer.print("[" + printer.colorString(list2.get(0).getName(), Color.WARNING) + "]");
            list2.remove(0);
        }

        printer.println("\n");
        return merged;
    }

}
