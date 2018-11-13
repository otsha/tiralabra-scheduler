package logic;

import data.Task;
import data.TaskList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SchedulerTest {

    private TaskList tasks;
    private Scheduler scheduler;

    public SchedulerTest() {
        this.tasks = new TaskList();
        this.scheduler = new Scheduler();
    }

    @Before
    public void setUp() {
        tasks.add(new Task("testTask1", 2500, "21.11.2018", 32));
        tasks.add(new Task("testTask2", 1230, "30.11.2018", 72));
        tasks.add(new Task("testTask3", 2750, "17.12.2018", 15));
        tasks.add(new Task("testTask4", 3000, "2.1.2019", 50));
        tasks.add(new Task("testTaskBig", 3500, "20.11.2018", 1500));
    }

    @Test
    public void mooreHodgsonReturnsASchedule() {
        // The tasks are already in EDD order, so the test is not dependent on sorting.
        TaskList schedule = scheduler.mooreHodgson(tasks);
        // The correct order of the tasks would be: 1, 2, 3, 4 and Big dropped.
        assertEquals(tasks.get(0), schedule.get(0));
        assertEquals(tasks.get(1), schedule.get(1));
        assertEquals(tasks.get(2), schedule.get(2));
        assertEquals(tasks.get(3), schedule.get(3));
        assertEquals(4, schedule.size());
    }
}
