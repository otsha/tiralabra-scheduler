package access;

import data.Task;
import data.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileAccessTest {

    private FileAccess access;

    public FileAccessTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException, UnsupportedEncodingException {
        this.access = new FileAccess("test.json");
    }

    @After
    public void tearDown() {
        File f = new File("test.json");
        f.delete();
    }

    @Test
    public void tasksAreWrittenToAndReadFromTheFile() throws FileNotFoundException, UnsupportedEncodingException, IOException {
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

        access.write(tasks);

        TaskList retrieved = access.read();

        assertEquals(tasks.size(), retrieved.size());

        for (int i = 0; i < retrieved.size(); i++) {
            Task expected = tasks.get(i);
            Task found = retrieved.get(i);

            assertEquals(expected.getName(), found.getName());
            assertEquals(expected.getPayment(), found.getPayment(), 0.0001);
            assertEquals(expected.getDeadline(), found.getDeadline());
            assertEquals(expected.getTimeEstimate(), found.getTimeEstimate());
        }
    }

}
