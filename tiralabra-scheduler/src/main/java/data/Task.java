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

    public Date deadlineAsDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date deadlineAsDate = null;

        // Parse a valid Date from the String input
        // TODO: Create a new class to handle this
        try {
            deadlineAsDate = dateFormat.parse(deadline);
        } catch (ParseException e) {
            System.out.println("ERROR: Invalid date format");
        }

        return deadlineAsDate;
    }

    public double getHourlyRate() {
        return payment / timeEstimate;
    }

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
