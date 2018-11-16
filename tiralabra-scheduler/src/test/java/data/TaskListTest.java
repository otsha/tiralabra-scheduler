package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TaskListTest {

    private TaskList list;

    @Before
    public void setUp() {
        list = new TaskList();
    }

    @Test
    public void taskListIsInitializedCorrectly() {
        assertEquals(0, list.size());
        assertEquals(8, list.capacity());
    }

    @Test
    public void tasksCanBeAddedAndFetched() {
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        list.add(t);
        assertEquals(t, list.get(0));
    }

    @Test
    public void taskListIncreasesCapacityIfRequired() {
        int initialCapacity = list.capacity();
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        for (int i = 0; i < 5; i++) {
            list.add(t);
        }
        assertEquals(2 * initialCapacity, list.capacity());
    }

    @Test
    public void tasksAreRetainedWhenCapacityIsIncreased() {
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        for (int i = 0; i < 5; i++) {
            list.add(t);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(t, list.get(i));
        }
    }

    @Test
    public void tasksCanBeRemovedFromTheList() {
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        list.add(t);
        list.remove(0);
        assertFalse(list.contains(t));
        assertEquals(0, list.size());
    }

    @Test
    public void taskListDecreasesCapacityIfPossible() {
        int initialCapacity = list.capacity();
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        for (int i = 0; i < 5; i++) {
            list.add(t);
        }
        list.remove(3);
        assertEquals(initialCapacity, list.capacity());
    }

    @Test
    public void getReturnsNullIfIndexIsOutOfBounds() {
        assertEquals(null, list.get(0));
    }

    @Test
    public void containsReturnsTrueIfTaskIsOnTheList() {
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        list.add(t);
        assertTrue(list.contains(t));
    }

    @Test
    public void containsReturnsFalseIfTaskIsNotOnTheList() {
        Task t = new Task("testTask", 2010, "31.12.2018", 32);
        assertFalse(list.contains(t));
    }

    @Test
    public void swapSwapsTwoValidIndexes() {
        Task t1 = new Task("testTask", 2010, "31.12.2018", 32);
        Task t2 = new Task("testTaskTwo", 5200, "1.9.2019", 158);
        list.add(t1);
        list.add(t2);
        list.swap(0, 1);
        assertEquals(t2, list.get(0));
        assertEquals(t1, list.get(1));
    }

    @Test
    public void swapDoesNothingIfIndexesAreEqual() {
        Task t1 = new Task("testTask", 2010, "31.12.2018", 32);
        Task t2 = new Task("testTaskTwo", 5200, "1.9.2019", 158);
        list.add(t1);
        list.add(t2);
        list.swap(0, 0);
        assertEquals(t1, list.get(0));
        assertEquals(t2, list.get(1));
    }

    @Test
    public void swapDoesNotSwapIfOneIndexIsInvalid() {
        Task t1 = new Task("testTask", 2010, "31.12.2018", 32);
        Task t2 = new Task("testTaskTwo", 5200, "1.9.2019", 158);
        list.add(t1);
        list.add(t2);
        list.swap(0, 2);
        list.swap(-1, 0);
        assertEquals(t1, list.get(0));
        assertEquals(t2, list.get(1));

    }
}
