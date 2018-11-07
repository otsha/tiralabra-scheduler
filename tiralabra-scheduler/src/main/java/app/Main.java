package app;

import data.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        // TODO: Move the UI to a separate class
        Scanner input = new Scanner(System.in);

        System.out.println("== SCHEDULING ANALYZER ==");

        while (true) {
            System.out.println("----------------");
            System.out.println("[1] Add a task");
            System.out.println("[2] Schedule all tasks");
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

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date deadlineDate = null;

                // Parse a valid Date from the String input
                // TODO: Create a new class to handle this
                try {
                    deadlineDate = dateFormat.parse(deadline);
                } catch (ParseException e) {
                    System.out.println("ERROR: Invalid date format");
                    continue;
                }

                System.out.println("How many hours will this task take?");
                int timeEstimate = Integer.parseInt(input.nextLine());

                // Create a new Task object with the user-inputted data
                Task t = new Task(name, payment, deadlineDate, timeEstimate);

                // DEBUG: Print out the created task's details
                System.out.println("----------------");
                System.out.println(t.getName());
                System.out.println("Payment: " + t.getPayment());
                System.out.println("Deadline: " + t.getDeadline());
                System.out.println("Days remaining: " + t.daysRemaining());
                System.out.println("Estimated time taken: " + t.getTimeEstimate());
                System.out.println("Hourly rate: " + t.getHourlyRate());

            } else if (cmd.equals("2")) {
                System.out.println("This functionality is still under production");
            } else {
                // ERROR FOR INVALID COMMAND
                System.out.println("ERROR: Invalid command");
            }
        }
    }
}
