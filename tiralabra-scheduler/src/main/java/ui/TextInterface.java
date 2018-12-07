package ui;

import access.IOHandler;
import data.Task;
import data.TaskList;
import logic.Scheduler;

public class TextInterface {

    private Scheduler scheduler;
    private TaskList tasks;
    private IOHandler io;
    private Visualizer visualizer;

    public TextInterface(Scheduler scheduler, TaskList tl, IOHandler io, Visualizer v) {
        this.scheduler = scheduler;
        this.tasks = tl;
        this.io = io;
        this.visualizer = v;
    }

    public void start() {

        io.output().println("== SCHEDULING ANALYZER ==");

        while (true) {
            io.output().hLine();
            io.output().println("Input command or [" + io.output().colorString("help", Color.INFO) + "] to see all commands.");

            String cmd = io.input().next();

            if (cmd.equals("x")) {
                // QUIT THE APPLICATION
                io.output().println("Exiting...");
                return;
            }

            if (cmd.equals("1")) {
                // CREATE A NEW TASK
                io.output().println("Task name:");
                String name = io.input().next();

                io.output().println("Payment sum:");
                String payment = io.input().next();

                io.output().println("Deadline (DD.MM.YYYY):");
                String deadline = io.input().next();

                io.output().println("How many hours will this task take? (Integer)");
                String timeEstimate = io.input().next();

                // Create a new Task object with the user-inputted data
                Task t = io.input().buildTask(name, payment, deadline, timeEstimate);

                if (t != null) {
                    // DEBUG: Print out the created task's details
                    io.output().hLine();
                    io.output().printSuccess("CREATED TASK:");
                    io.output().println(t);

                    // Add the task to the list of all tasks
                    tasks.add(t);
                } else {
                    io.output().printError(Message.TASKCREATIONERROR);
                }

            } else if (cmd.equals("2")) {
                // REMOVE A TASK
                io.output().println("Task name:");
                String name = io.input().next();

                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    if (t.getName().equals(name)) {
                        tasks.remove(i);
                    }
                }

                io.output().printSuccess("All tasks with the name " + name + " removed.");

            } else if (cmd.equals("3")) {
                io.output().println("Scheduling to minimize the number of overdue tasks...");
                io.output().hLine();
                io.output().println("SCHEDULE:");
                io.output().hLine();

                TaskList schedule = scheduler.mooreHodgson(tasks);

                // Print out the schedule on a table
                io.output().printMooreHodgsonTable(schedule);

            } else if (cmd.equals("4")) {
                io.output().println("Scheduling by Earliest Due Date...");
                io.output().hLine();
                io.output().println("SCHEDULE:");
                io.output().hLine();

                TaskList schedule = scheduler.edd(tasks);

                // Print out the schedule on a table
                io.output().printEEDTable(schedule);

            } else if (cmd.equals("5")) {
                io.output().println("Scheduling by Shortest Processing Time...");
                io.output().hLine();
                io.output().println("SCHEDULE:");
                io.output().hLine();

                TaskList schedule = scheduler.spt(tasks);

                // Print out the schedule on a table
                io.output().printSPTTable(schedule);

            } else if (cmd.equals("6")) {
                io.output().println("Scheduling by greatest hourly rate first...");
                io.output().hLine();
                io.output().println("SCHEDULE:");
                io.output().hLine();

                TaskList schedule = scheduler.wspt(tasks);

                // Print out the schedule on a table
                io.output().printWSPTTable(schedule);

            } else if (cmd.equals("v")) {
                // List all Tasks
                for (int i = 0; i < tasks.size(); i++) {
                    io.output().println(tasks.get(i) + "\n");
                }
            } else if (cmd.equals("s")) {
                // Save the current list of tasks
                try {
                    io.file().write(tasks);
                } catch (Exception ex) {
                    io.output().printError(Message.TASKSAVINGERROR);
                }
            } else if (cmd.equals("e")) {
                io.output().println("================");
                io.output().println("EXPERIMENTAL FEATURES:");
                io.output().println("[" + io.output().colorString("1", Color.INFO) + "] to visualize EDD scheduling");
                io.output().println("[" + io.output().colorString("2", Color.INFO) + "] to visualize SPT scheduling");
                io.output().println("[" + io.output().colorString("3", Color.INFO) + "] to visualize Payment-weighted SPT scheduling");
                io.output().println("[" + io.output().colorString("4", Color.INFO) + "] to visualize Moore-Hodgson scheduling");
                io.output().println("[" + io.output().colorString("x", Color.INFO) + "] to return to the previous menu");

                cmd = io.input().next();
                if (cmd.equals("1")) {
                    visualizer.edd(tasks);
                } else if (cmd.equals("2")) {
                    visualizer.spt(tasks);
                } else if (cmd.equals("3")) {
                    visualizer.wspt(tasks);
                } else if (cmd.equals("4")) {
                    visualizer.mooreHodgson(tasks);
                } else if (cmd.equals("x")) {
                    continue;
                } else {
                    io.output().printError(Message.INVALIDCOMMANDERROR);
                }

            } else if (cmd.equals("help")) {
                io.output().printHelp();
            } else {
                // ERROR FOR INVALID COMMAND
                io.output().printError(Message.INVALIDCOMMANDERROR);
            }
        }

    }

}
