package logic;

import data.Task;
import java.util.Comparator;

public class EDDComparator implements Comparator<Task> {

    /**
     * Compares the deadlines of the inputted Tasks
     *
     * @param o1
     * @param o2
     * @return 1 if o1 has a later due date, -1 if o1 has an earlier due date, 0
     * if equal
     */
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.daysRemaining() > o2.daysRemaining()) {
            return 1;
        } else if (o1.daysRemaining() < o2.daysRemaining()) {
            return -1;
        } else {
            return 0;
        }
    }
}
