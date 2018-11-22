package logic;

import data.Task;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WeightedSPTComparatorTest {

    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private WeightedSPTComparator wspt;

    public WeightedSPTComparatorTest() {
        wspt = new WeightedSPTComparator();
        t1 = new Task("taskOne", 2500, "1.1.2019", 56); // Lowest hourly rate @ ~44.64 / h
        t2 = new Task("taskTwo", 2350, "17.12.2018", 32); // Highest hourly rate @ ~73.44 / h
        t3 = new Task("taskThree", 1700, "31.11.2018", 25); // 68 / h
        t4 = new Task("taskFour", 1700, "30.11.2018", 25); // 68 / h
    }

    @Test
    public void returnsMinusOneIfTheFirstTaskHasAHigherHourlyRate() {
        assertEquals(-1, wspt.compare(t2, t1));
    }

    @Test
    public void returnsMinusOneIfTheFirstTaskHasALowerHourlyRate() {
        assertEquals(1, wspt.compare(t3, t2));
    }

    @Test
    public void returnsZeroIfTheTasksHaveTheSameProcessingTime() {
        assertEquals(0, wspt.compare(t3, t4));
    }
}
