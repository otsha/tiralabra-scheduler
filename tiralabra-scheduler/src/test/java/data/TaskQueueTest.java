package data;

import logic.SPTComparator;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TaskQueueTest {

    private TaskQueue queue;
    
    @Before
    public void setUp() {
        this.queue = new TaskQueue(9, new SPTComparator());
    }

    @Test
    public void aTaskCanBeAddedToAndPeekedFromTheQueue() {
        Task t = new Task("hello", 2500.52, "31.12.2019", 57);
        queue.add(t);
        assertEquals(t, queue.peek());
    }

    @Test
    public void aTaskCanBeAddedToAndPolledFromTheQueue() {
        Task t = new Task("hello", 2500.52, "31.12.2019", 57);
        queue.add(t);
        assertEquals(t, queue.poll());
    }

    @Test
    public void peekReturnsNullIfQueueIsEmpty() {
        assertEquals(null, queue.peek());
    }

    @Test
    public void pollReturnsNullIfQueueIsEmpty() {
        assertEquals(null, queue.poll());
    }

    @Test
    public void peekReturnsHighestPriority() {
        // In this case we're using the SPTComparator, which means
        // that 'highest priority' means longest processing time.
        Task t = new Task("hello", 2500.52, "31.12.2019", 57);
        Task u = new Task("world", 700.26, "23.5.2019", 129);
        queue.add(t);
        queue.add(u);
        assertEquals(u, queue.peek());
    }

    @Test
    public void pollReturnsHighestPriority() {
        // In this case we're using the SPTComparator, which means
        // that 'highest priority' means longest processing time.
        Task t = new Task("hello", 2500.52, "31.12.2019", 57);
        Task u = new Task("world", 700.26, "23.5.2019", 129);
        queue.add(t);
        queue.add(u);
        assertEquals(u, queue.poll());
    }

    @Test
    public void aSmallListOfTasksCanBeAddedAndRetrievedFromTheQueue() {
        Task t = new Task("hello", 2500.52, "31.12.2019", 57);
        Task u = new Task("world", 700.26, "23.5.2019", 38);
        Task v = new Task("hei", 62, "17.7.2019", 38);
        queue.add(t);
        queue.add(u);
        queue.add(v);

        TaskList tasks = new TaskList();

        while (queue.peek() != null) {
            tasks.add(queue.poll());
        }

        assertTrue(tasks.contains(t));
        assertTrue(tasks.contains(u));
        assertTrue(tasks.contains(v));
    }

    @Test
    public void aLargerListOfTasksCanBeAddedAndRetrievedFromTheQueue() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("hello", 2500, "21.12.2018", 52));
        tasks.add(new Task("world", 2250, "12.12.2018", 360));
        tasks.add(new Task("moi", 1230.25, "26.12.2018", 146));
        tasks.add(new Task("kumpula", 6066, "29.11.2018", 13));
        tasks.add(new Task("wow", 1234, "1.1.2019", 1));
        tasks.add(new Task("early", 2225, "15.11.2018", 3));
        tasks.add(new Task("homework", 0, "11.11.2018", 14));
        tasks.add(new Task("infinity", 150000, "31.6.2019", 1500));
        tasks.add(new Task("tira", 0, "13.11.2018", 200));
        tasks.add(new Task("future", 7500, "24.5.2019", 250));

        TaskQueue newQueue = new TaskQueue(tasks.size(), new SPTComparator());
        for (int i = 0; i < tasks.size(); i++) {
            newQueue.add(tasks.get(i));
        }

        while (newQueue.peek() != null) {
            assertTrue(tasks.contains(newQueue.poll()));
        }
    }
}
