package logic;

import data.Task;
import java.util.Comparator;

public class WeightedSPTComparator implements Comparator<Task> {

    /**
     * Compares the hourly rates of the inputted tasks
     *
     * @param o1
     * @param o2
     * @return 1 if o1 has a greater rate, -1 if o1 has a lesser rate, 0 if
     * equal
     */
    @Override
    public int compare(Task o1, Task o2) {

        if (o1.getHourlyRate() < o2.getHourlyRate()) {
            return 1;
        } else if (o1.getHourlyRate() > o2.getHourlyRate()) {
            return -1;
        } else {
            return 0;
        }
    }

}
