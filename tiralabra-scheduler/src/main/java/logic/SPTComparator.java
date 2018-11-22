package logic;

import data.Task;
import java.util.Comparator;

public class SPTComparator implements Comparator<Task> {

    /**
     * Compares the processing times of the inputted Tasks
     *
     * @param o1
     * @param o2
     * @return 1 if o1 has a longer processing time, -1 if o1 has a shorter
     * processing time, 0 if equal
     */
    @Override
    public int compare(Task o1, Task o2) {

        if (o1.getTimeEstimate() > o2.getTimeEstimate()) {
            return 1;
        } else if (o1.getTimeEstimate() < o2.getTimeEstimate()) {
            return -1;
        } else {
            return 0;
        }
    }

}
