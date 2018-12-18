package data;

import java.util.Date;
import logic.DateHandler;

public class Task {

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
     * Parses a date out of the String-format deadline.
     *
     * @see logic.DateHandler
     *
     * @return The deadline of the project as Date.
     */
    public Date deadlineAsDate() {
        DateHandler dh = new DateHandler();
        return dh.parse(deadline);
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
     * @see logic.DateHandler
     *
     * @return The number of days until the task's deadline.
     */
    public long daysRemaining() {
        DateHandler dh = new DateHandler();
        return dh.daysFromToday(deadlineAsDate());
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
}
