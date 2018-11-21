package logic;

import data.Task;
import data.TaskList;
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
        tasks.add(new Task("testTask1", 2500, "21.11.2018", 32));
        tasks.add(new Task("testTask2", 1230, "30.11.2018", 72));
        tasks.add(new Task("testTask3", 2750, "17.12.2018", 15));
        tasks.add(new Task("testTask4", 3000, "2.1.2019", 50));
        tasks.add(new Task("testTask5", 3500, "20.11.2018", 1500));
    }

    @Test
    public void mooreHodgsonReturnsASchedule() {
        TaskList schedule = scheduler.mooreHodgson(tasks);
        // TODO FIX THIS SHIT
        assertEquals(tasks.get(2), schedule.get(0));
        assertEquals(tasks.get(3), schedule.get(1));
        assertEquals(2, schedule.size());
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
}
