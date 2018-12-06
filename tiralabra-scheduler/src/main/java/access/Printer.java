package access;

import data.Task;
import data.TaskList;
import ui.Color;

public class Printer {

    /**
     * Prints the given object using System.out.println
     *
     * @param o
     */
    public void println(Object o) {
        System.out.println(o.toString());
    }

    /**
     * Prints the given object using System.out.print
     *
     * @param o
     */
    public void print(Object o) {
        System.out.print(o.toString());
    }

    /**
     * Constructs a colored string representation of the given object.
     *
     * @param o
     * @param c - The desired color
     * @return A colored representation of the given object in String format
     * @see ui.Color
     */
    public String colorString(Object o, Color c) {
        return c.getCode() + o.toString() + "\u001B[0m";
    }

    /**
     * Prints out the names of Tasks on a given TaskList so that each name is
     * surrounded by brackets and the name itself is colored.
     *
     * @param list - The TaskList to be printed
     * @param c - The color to be used for the names of the tasks
     * @see ui.Color
     */
    public void printList(TaskList list, Color c) {

        for (int i = 0; i < list.size(); i++) {
            print("[" + colorString(list.get(i).getName(), c) + "]");
        }

        println("\n");
    }

    public void printEEDTable(TaskList list) {
        println("Name" + "\t\t\t" + "Deadline");
        println("----" + "\t\t\t" + "--------");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getName().length() >= 8) {
                println(t.getName().substring(0, 7) + "...\t\t" + t.getDeadline());
            } else {
                println(t.getName() + "\t\t\t" + t.getDeadline());
            }
        }
    }

    public void printSPTTable(TaskList list) {
        println("Name" + "\t\t\t" + "Time");
        println("----" + "\t\t\t" + "----");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getName().length() >= 8) {
                println(t.getName().substring(0, 7) + "...\t\t" + t.getTimeEstimate());
            } else {
                println(t.getName() + "\t\t\t" + t.getTimeEstimate());
            }
        }
    }

    public void printWSPTTable(TaskList list) {
        println("Name" + "\t\t\t" + "Rate" + "\t\t\t" + "Deadline");
        println("----" + "\t\t\t" + "----" + "\t\t\t" + "--------");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getName().length() >= 8) {
                println(t.getName().substring(0, 7) + "...\t\t" + "~" + Math.round(t.getHourlyRate()) + "\t\t\t" + t.getDeadline());
            } else {
                println(t.getName() + "\t\t\t" + "~" + Math.round(t.getHourlyRate()) + "\t\t\t" + t.getDeadline());
            }
        }
    }

    public void printMooreHodgsonTable(TaskList list) {
        println("Name" + "\t\t\t" + "Deadline" + "\t\t\t" + "Time");
        println("----" + "\t\t\t" + "--------" + "\t\t\t" + "----");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getName().length() >= 8) {
                println(t.getName().substring(0, 7) + "...\t\t" + t.getDeadline() + "\t\t\t" + t.getTimeEstimate());
            } else {
                println(t.getName() + "\t\t\t" + t.getDeadline() + "\t\t\t" + t.getTimeEstimate());
            }
        }
    }

    /**
     * Prints out an error using the default error color.
     *
     * @param o
     * @see ui.Color#ERROR
     */
    public void printError(Object o) {
        println(colorString(o, Color.ERROR));
    }

    /**
     * Prints out a success message using the default success color.
     *
     * @param o
     * @see ui.Color#SUCCESS
     */
    public void printSuccess(Object o) {
        println(colorString(o, Color.SUCCESS));
    }

    /**
     * Prints a 16-char long horizontal dashed line
     */
    public void hLine() {
        println("----------------");
    }

    /**
     * Prints out the list of commands available in the application.
     */
    public void printHelp() {
        String help = "----------------" + "\n"
                + "[" + colorString(1, Color.INFO) + "] Add a task" + "\n"
                + "[" + colorString(2, Color.INFO) + "] Remove a task" + "\n"
                + "[" + colorString(3, Color.INFO) + "] Moore-Hodgson Scheduling" + "\n"
                + "[" + colorString(4, Color.INFO) + "] Earliest Due Date Scheduling" + "\n"
                + "[" + colorString(5, Color.INFO) + "] Shortest Processing Time Scheduling" + "\n"
                + "[" + colorString(6, Color.INFO) + "] Payment-weighted Shortest Processing Time Scheduling" + "\n"
                + "[" + colorString("v", Color.INFO) + "] View the Current Tasks" + "\n"
                + "[" + colorString("s", Color.INFO) + "] Save the Current List of Tasks" + "\n"
                + "[" + colorString("e", Color.INFO) + "] Experimental: Visualize EDD scheduling" + "\n"
                + "[" + colorString("x", Color.INFO) + "] Exit" + "\n";
        print(help);
    }

}
