package logic;

import data.Task;
import java.util.Comparator;

public class WeightedSPTComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        // Return:
        // 1 if o1 has a lesser hourly rate than o2
        // -1 if o1 has a greater hourly rate than o2
        // 0 if the tasks have the same hourly rate

        if (o1.getHourlyRate() < o2.getHourlyRate()) {
            return 1;
        } else if (o1.getHourlyRate() > o2.getHourlyRate()) {
            return -1;
        } else {
            return 0;
        }
    }

}
