package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {

    private String name;
    private Double payment;
    private String deadline;
    private int timeEstimate;
    private Task t;

    public TaskTest() {
        name = "testTask";
        payment = 2550.27;
        deadline = "21.12.2018";
        timeEstimate = 52;
    }

    @Before
    public void setUp() {
        t = new Task(name, payment, deadline, timeEstimate);
    }

    @Test
    public void taskIsInitializedCorrectly() {
        assertEquals(name, t.getName());
        assertEquals(payment, t.getPayment(), 0);
        assertEquals(deadline, t.getDeadline());
        assertEquals(timeEstimate, t.getTimeEstimate());
    }

    @Test
    public void deadlineAsDateReturnsTheCorrectDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date deadlineAsDate = null;

        try {
            deadlineAsDate = dateFormat.parse(deadline);
        } catch (ParseException e) {
        }

        assertEquals(deadlineAsDate, t.deadlineAsDate());
    }

    @Test
    public void deadlineAsDateReturnsNullIfInputWasInvalid() {
        Task u = new Task(name, payment, "ab.cd.efgh", timeEstimate);
        assertEquals(null, u.deadlineAsDate());
    }

    @Test
    public void getHourlyRateReturnsTheCorrectRate() {
        assertEquals(payment / timeEstimate, t.getHourlyRate(), 0);
    }

    @Test
    public void daysReamainingReturnsTheCorrectNumberOfDays() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Date currentDate = new Date();
        Date deadlineAsDate = null;
        long difference = 0l;

        try {
            deadlineAsDate = dateFormat.parse(deadline);
            difference = deadlineAsDate.getTime() - currentDate.getTime();
        } catch (ParseException e) {
        }

        long remaining = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        
        assertEquals(remaining, t.daysRemaining());
    }
    
    @Test
    public void toStringReturnsACorrectRepresentation() {
        String taskString = "Name: " + name + "\n"
                + "Payment: " + payment + "\n"
                + "Deadline: " + deadline + "\n"
                + "Days remaining: " + t.daysRemaining() + "\n"
                + "Estimated time taken: " + timeEstimate + "\n"
                + "Hourly rate: " + t.getHourlyRate();
        
        assertEquals(taskString, t.toString());
    }
}
