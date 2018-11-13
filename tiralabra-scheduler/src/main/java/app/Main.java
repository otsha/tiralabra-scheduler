package app;

import data.Task;
import data.TaskList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import logic.EDDComparator;
import logic.Scheduler;

class Main {

    public static void main(String[] args) {

        TaskList tasks = new TaskList();
        tasks.add(new Task("hello", 2500, "21.12.2018", 52));
        tasks.add(new Task("world", 2250, "12.12.2018", 360));
        tasks.add(new Task("moi", 1230.25, "26.12.2018", 146));
        tasks.add(new Task("kumpula", 6066, "29.11.2018", 13));
        tasks.add(new Task("wow", 1234, "1.1.2019", 1));
        tasks.add(new Task("early", 2225, "15.11.2018", 3));
        tasks.add(new Task("homework", 0, "11.11.2018", 14));
        tasks.add(new Task("infinity", 150000, "31.6.2019", 1500));
        tasks.add(new Task("tira", 0, "13.11.2018", 200));
        tasks.add(new Task("future", 7500, "24.5.2019", 250));

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).getName());
        }

        System.out.println("Current capacity: " + tasks.capacity());
        System.out.println("Occupied: " + tasks.size());

        /*
        // TODO: Move the UI to a separate class
        Scanner input = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

        // TEST/DEMO DATA
        // Fastest: wow
        // Slowest: infinity
        // EDD: homework
        // LDD: infinity
        tasks.add(new Task("hello", 2500, "21.12.2018", 52));
        tasks.add(new Task("world", 2250, "12.12.2018", 360));
        tasks.add(new Task("moi", 1230.25, "26.12.2018", 146));
        tasks.add(new Task("kumpula", 6066, "29.11.2018", 13));
        tasks.add(new Task("wow", 1234, "1.1.2019", 1));
        tasks.add(new Task("early", 2225, "15.11.2018", 3));
        tasks.add(new Task("homework", 0, "11.11.2018", 14));
        tasks.add(new Task("infinity", 150000, "31.6.2019", 1500));
        tasks.add(new Task("tira", 0, "13.11.2018", 200));
        tasks.add(new Task("future", 7500, "24.5.2019", 250));

        System.out.println("== SCHEDULING ANALYZER ==");

        while (true) {
            System.out.println("----------------");
            System.out.println("[1] Add a task");
            System.out.println("[2] Remove a task");
            System.out.println("[3] Moore-Hodgson Scheduling");
            System.out.println("[x] Exit");
            System.out.println("----------------");

            String cmd = input.nextLine();

            if (cmd.equals("x")) {
                // QUIT THE APPLICATION
                System.out.println("Exiting...");
                return;
            }

            if (cmd.equals("1")) {
                // CREATE A NEW TASK
                System.out.println("Task name:");
                String name = input.nextLine();

                System.out.println("Payment sum:");
                double payment = Double.parseDouble(input.nextLine());

                System.out.println("Deadline (DD/MM/YYYY):");
                String deadline = input.nextLine();

                System.out.println("How many hours will this task take?");
                int timeEstimate = Integer.parseInt(input.nextLine());

                // Create a new Task object with the user-inputted data
                Task t = new Task(name, payment, deadline, timeEstimate);

                // DEBUG: Print out the created task's details
                System.out.println("----------------");
                System.out.println("CREATED TASK:");
                System.out.println(t);

                // Add the task to the list of all tasks
                tasks.add(t);

            } else if (cmd.equals("2")) {
                System.out.println("Task name:");
                String name = input.nextLine();
                
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    if (t.getName().equals(name)) {
                        tasks.remove(t);
                    }
                }
                
                System.out.println("All tasks with the name " + name + " removed.");
                
            } else if (cmd.equals("3")) {
                System.out.println("This functionality is still under production.");
                System.out.println("Currently, it is implemented using the data structures already provided by Java.");
                System.out.println("----------------");
                System.out.println("PREPARING:");
                
                Collections.sort(tasks, new EDDComparator());
                
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(tasks.get(i).getName() + " // " + tasks.get(i).getDeadline());
                }
                
                System.out.println("----------------");
                Scheduler s = new Scheduler(tasks);
                s.mooreHodgson();
                
            } else {
                // ERROR FOR INVALID COMMAND
                System.out.println("ERROR: Invalid command");
            }
        }
         */
    }
}
