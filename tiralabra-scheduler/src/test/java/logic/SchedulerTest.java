package logic;

import data.Task;
import data.TaskList;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SchedulerTest {

    private TaskList tasks;
    private Scheduler scheduler;

    public SchedulerTest() {
        this.scheduler = new Scheduler();
    }

    @Before
    public void setUp() {
        this.tasks = new TaskList();
        tasks.add(new Task("testTask1", 2500, "21.11.2019", 32)); // 72.13
        tasks.add(new Task("testTask2", 1230, "30.11.2019", 72)); // 17.08
        tasks.add(new Task("testTask3", 2750, "17.12.2019", 15)); // 183.33
        tasks.add(new Task("testTask4", 3000, "2.1.2020", 50)); // 60
        tasks.add(new Task("testTask5", 3500, "20.11.2019", 10000)); // 2.33
    }

    @Test
    public void mooreHodgsonReturnsASchedule() {
        TaskList schedule = scheduler.mooreHodgson(tasks);
        // Test that the unfinishable task (5) will be thrown out
        assertEquals(tasks.get(0), schedule.get(0));
        assertEquals(tasks.get(1), schedule.get(1));
        assertEquals(tasks.get(2), schedule.get(2));
        assertEquals(tasks.get(3), schedule.get(3));
        assertEquals(4, schedule.size());
    }

    @Test
    public void eddReturnsTheCorrectOrder() {
        TaskList schedule = scheduler.edd(tasks);
        // The correct EDD order would be: 5, 1, 2, 3, 4
        assertEquals(tasks.get(4), schedule.get(0));
        assertEquals(tasks.get(0), schedule.get(1));
        assertEquals(tasks.get(1), schedule.get(2));
        assertEquals(tasks.get(2), schedule.get(3));
        assertEquals(tasks.get(3), schedule.get(4));
    }

    @Test
    public void sptReturnsTheCorrectOrder() {
        TaskList schedule = scheduler.spt(tasks);
        // The correct SPT order would be: 3, 1, 4, 2, 5
        assertEquals(tasks.get(2), schedule.get(0));
        assertEquals(tasks.get(0), schedule.get(1));
        assertEquals(tasks.get(3), schedule.get(2));
        assertEquals(tasks.get(1), schedule.get(3));
        assertEquals(tasks.get(4), schedule.get(4));
    }

    @Test
    public void wsptReturnsTheCorrectOrder() {
        TaskList schedule = scheduler.wspt(tasks);
        // The correct wSPT order would be: 3, 1, 4, 2, 5
        assertEquals(tasks.get(2), schedule.get(0));
        assertEquals(tasks.get(0), schedule.get(1));
        assertEquals(tasks.get(3), schedule.get(2));
        assertEquals(tasks.get(1), schedule.get(3));
        assertEquals(tasks.get(4), schedule.get(4));
    }
}
