package ui;

import access.IOHandler;
import data.Task;
import data.TaskList;
import logic.Scheduler;

public class TextInterface {

    private Scheduler scheduler;
    private TaskList tasks;
    private IOHandler io;

    public TextInterface(Scheduler scheduler, TaskList tl, IOHandler io) {
        this.scheduler = scheduler;
        this.tasks = tl;
        this.io = io;
    }

    public void start() {

        io.output().println("== SCHEDULING ANALYZER ==");

        while (true) {
            io.output().println("----------------");
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

                io.output().println("Deadline (DD/MM/YYYY):");
                String deadline = io.input().next();

                io.output().println("How many hours will this task take? (Integer)");
                String timeEstimate = io.input().next();

                // Create a new Task object with the user-inputted data
                Task t = io.input().buildTask(name, payment, deadline, timeEstimate);
                
                if (t != null) {
                    // DEBUG: Print out the created task's details
                    io.output().println("----------------");
                    io.output().println("CREATED TASK:");
                    io.output().println(t);

                    // Add the task to the list of all tasks
                    tasks.add(t);
                } else {
                    io.output().printError(Error.TASKCREATIONERROR);
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

                io.output().println("All tasks with the name " + name + " removed.");

            } else if (cmd.equals("3")) {
                scheduler.mooreHodgson(tasks);
            } else if (cmd.equals("4")) {
                io.output().println("Scheduling by Earliest Due Date...");
                io.output().println("----------------");
                io.output().println("SCHEDULE:");

                TaskList schedule = scheduler.edd(tasks);

                for (int i = 0; i < schedule.size(); i++) {
                    Task t = schedule.get(i);
                    io.output().println(t.getName() + " // " + t.getDeadline());
                }
            } else if (cmd.equals("5")) {
                io.output().println("Scheduling by Shortest Processing Time...");
                io.output().println("----------------");
                io.output().println("SCHEDULE:");

                TaskList schedule = scheduler.spt(tasks);

                for (int i = 0; i < schedule.size(); i++) {
                    Task t = schedule.get(i);
                    io.output().println(t.getName() + " // Estimated Processing Time: " + t.getTimeEstimate() + " hours");
                }
            } else if (cmd.equals("6")) {
                io.output().println("Scheduling by greatest hourly rate first...");
                io.output().println("----------------");
                io.output().println("SCHEDULE:");

                TaskList schedule = scheduler.wspt(tasks);

                for (int i = 0; i < schedule.size(); i++) {
                    Task t = schedule.get(i);
                    io.output().println(t.getName() + " // Hourly rate: " + t.getHourlyRate() + " // Deadline: " + t.getDeadline());
                }
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
                    io.output().printError(Error.TASKSAVINGERROR);
                }
            } else if (cmd.equals("e")) {
                Visualizer v = new Visualizer(io.output());
                v.edd(tasks);
            } else if (cmd.equals("help")) {
                io.output().printHelp();
            } else {
                // ERROR FOR INVALID COMMAND
                io.output().printError(Error.INVALIDCOMMANDERROR);
            }
        }

    }

}
