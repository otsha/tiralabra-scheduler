package data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task {

    private String name;
    private double payment;
    private Date deadline;
    private int timeEstimate;

    public Task(String name, double payment, Date deadline, int timeEstimate) {
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

    public Date getDeadline() {
        return deadline;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }
    
    public double getHourlyRate() {
        return payment / timeEstimate;
    }
    
    public long daysRemaining() {
        Date currentDate =  new Date();
        long difference = deadline.getTime() - currentDate.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }
}
