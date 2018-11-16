package logic;

import data.Task;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SPTComparatorTest {

    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private SPTComparator spt;

    public SPTComparatorTest() {
        spt = new SPTComparator();
        t1 = new Task("taskOne", 2500, "1.1.2019", 56); // Longest processing time
        t2 = new Task("taskTwo", 2350, "17.12.2018", 32);
        t3 = new Task("taskThree", 1700, "31.11.2018", 25);
        t4 = new Task("taskFour", 2664, "30.11.2018", 25); // Shortest processing time
    }

    @Test
    public void returnsOneIfTheFirstTaskHasALongerProcessingTime() {
        assertEquals(1, spt.compare(t1, t2));
    }

    @Test
    public void returnsMinusOneIfTheFirstTaskHasAShorterProcessingTime() {
        assertEquals(-1, spt.compare(t3, t2));
    }

    @Test
    public void returnsZeroIfTheTasksHaveTheSameProcessingTime() {
        assertEquals(0, spt.compare(t3, t4));
    }
}
