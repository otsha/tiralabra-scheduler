package ui;

import data.Task;
import data.TaskList;
import java.util.Scanner;
import logic.Scheduler;

public class TextInterface {

    private Scheduler scheduler;
    private TaskList tasks;
    private Scanner input;

    public TextInterface(Scheduler scheduler, TaskList tl, Scanner scanner) {
        this.scheduler = scheduler;
        this.tasks = tl;
        this.input = scanner;
    }

    public void start() {

        System.out.println("== SCHEDULING ANALYZER ==");

        while (true) {
            System.out.println("----------------");
            System.out.println("[1] Add a task");
            System.out.println("[2] Remove a task");
            System.out.println("[3] Moore-Hodgson Scheduling");
            System.out.println("[4] Earliest Due Date Scheduling");
            System.out.println("[5] Shortest Processing Time Scheduling");
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
                // REMOVE A TASK
                System.out.println("Task name:");
                String name = input.nextLine();

                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    if (t.getName().equals(name)) {
                        tasks.remove(i);
                    }
                }

                System.out.println("All tasks with the name " + name + " removed.");

            } else if (cmd.equals("3")) {
                System.out.println("This functionality is still under production.");
                System.out.println("Currently, it is implemented using some of the data structures already provided by Java.");
                System.out.println("----------------");

                scheduler.mooreHodgson(tasks);

            } else if (cmd.equals("4")) {
                System.out.println("Scheduling by Earliest Due Date...");
                System.out.println("----------------");
                System.out.println("SCHEDULE:");

                TaskList schedule = scheduler.edd(tasks);

                for (int i = 0; i < schedule.size(); i++) {
                    Task t = schedule.get(i);
                    System.out.println(t.getName() + " // " + t.getDeadline());
                }
            } else if (cmd.equals("5")) {
                System.out.println("Scheduling by Shortest Processing Time...");
                System.out.println("----------------");
                System.out.println("SCHEDULE:");
                
                TaskList schedule = scheduler.spt(tasks);
                
                for (int i = 0; i < schedule.size(); i++) {
                    Task t = schedule.get(i);
                    System.out.println(t.getName() + " // Estimated Processing Time: " + t.getTimeEstimate() + " hours");
                }
            } else {
                // ERROR FOR INVALID COMMAND
                System.out.println("ERROR: Invalid command");
            }
        }

    }

}
