package logic;

import data.Task;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EDDComparatorTest {

    private Task t1;
    private Task t2;
    private Task t3;
    private Task t4;
    private EDDComparator edd;

    public EDDComparatorTest() {
        edd = new EDDComparator();
        t1 = new Task("taskOne", 2500, "1.1.2019", 56); // Latest due date
        t2 = new Task("taskTwo", 2350, "17.12.2018", 32);
        t3 = new Task("taskThree", 1700, "31.11.2018", 25); // Earliest due date

        t4 = new Task("taskFour", 2664, "31.11.2018", 13);
    }

    @Test
    public void returnsOneIfTheFirstTaskHasALaterDueDate() {
        assertEquals(1, edd.compare(t1, t2));
    }

    @Test
    public void returnsMinusOneIfTheFirstTaskHasAnEarlierDueDate() {
        assertEquals(-1, edd.compare(t3, t2));
    }

    @Test
    public void returnsZeroIfTheTasksHaveTheSameDueDate() {
        assertEquals(0, edd.compare(t3, t4));
    }
}
