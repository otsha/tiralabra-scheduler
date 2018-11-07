package logic;

import data.Task;
import java.util.Comparator;

public class EDDComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        // Return:
        // 1 if o1 has a later due date than o2
        // -1 if o1 has an earlier due date than o2
        // 0 if the tasks have the same due date

        if (o1.daysRemaining() > o2.daysRemaining()) {
            return 1;
        } else if (o1.daysRemaining() < o2.daysRemaining()) {
            return -1;
        } else {
            return 0;
        }
    }
}
