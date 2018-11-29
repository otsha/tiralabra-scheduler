package access;

import data.TaskList;
import ui.Color;

public class Printer {

    public void println(Object o) {
        System.out.println(o.toString());
    }

    public void print(Object o) {
        System.out.print(o.toString());
    }

    public String colorString(Object o, Color c) {
        return c.getCode() + o.toString() + "\u001B[0m";
    }

    public void printList(TaskList list, Color c) {

        for (int i = 0; i < list.size(); i++) {
            print("[" + colorString(list.get(i).getName(), c) + "]");
        }

        println("\n");
    }

    public void printError(Object o) {
        println(colorString(o, Color.ERROR));
    }

    public void printHelp() {
        String help = "----------------" + "\n"
                + "[" + colorString(1, Color.INFO) + "] Add a task" + "\n"
                + "[2] Remove a task" + "\n"
                + "[3] Moore-Hodgson Scheduling" + "\n"
                + "[4] Earliest Due Date Scheduling" + "\n"
                + "[5] Shortest Processing Time Scheduling" + "\n"
                + "[6] Payment-weighted Shortest Processing Time Scheduling" + "\n"
                + "[v] View the Current Tasks" + "\n"
                + "[s] Save the Current List of Tasks" + "\n"
                + "[e] Experimental: Visualize EDD scheduling" + "\n"
                + "[x] Exit" + "\n";
        print(help);
    }

}
