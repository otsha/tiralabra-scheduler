package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Comparable<Task> {

    private String name;
    private double payment;
    private String deadline;
    private int timeEstimate;

    public Task(String name, double payment, String deadline, int timeEstimate) {
        this.name = name;
        this.payment = payment;
        this.deadline = deadline;
        this.timeEstimate = timeEstimate;
    }

    public String getName() {
        return name;
    }

    public double getPayment() {
        return payment;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    /**
     * Attempts to parse a valid date from the inputted String.
     *
     * @todo Separate date handling to a new class.
     *
     * @return The deadline of the project as Date.
     */
    public Date deadlineAsDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date deadlineAsDate = null;

        try {
            deadlineAsDate = dateFormat.parse(deadline);
        } catch (ParseException e) {
            System.out.println("ERROR: Invalid date format");
        }

        return deadlineAsDate;
    }

    /**
     * Divides the task's payment by the estimation of the time it is going to
     * take, in order to produce a standardised hourly rate for the task.
     *
     * @return The task's hourly rate
     */
    public double getHourlyRate() {
        return payment / timeEstimate;
    }

    /**
     * Compares the current date to the task's deadline.
     *
     * @todo Separate date handling to a new class.
     *
     * @return The number of days until the task's deadline.
     */
    public long daysRemaining() {
        Date currentDate = new Date();
        long difference = deadlineAsDate().getTime() - currentDate.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        String s = "Name: " + name + "\n"
                + "Payment: " + payment + "\n"
                + "Deadline: " + deadline + "\n"
                + "Days remaining: " + daysRemaining() + "\n"
                + "Estimated time taken: " + timeEstimate + "\n"
                + "Hourly rate: " + getHourlyRate();

        return s;
    }
    
    /**
     * Compares the task's time estimation with another task.
     * 
     * @todo Separate this functionality into a SPT comparator class.
     * 
     * @param o
     * @return 1 if task o takes longer, -1 if less, 0 if the tasks are equal.
     */

    @Override
    public int compareTo(Task o) {
        if (timeEstimate > o.getTimeEstimate()) {
            return -1;
        } else if (timeEstimate < o.getTimeEstimate()) {
            return 1;
        } else {
            return 0;
        }
    }
}
