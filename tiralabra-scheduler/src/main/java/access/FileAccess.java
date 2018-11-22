package access;

import com.google.gson.Gson;
import data.Task;
import data.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccess {

    private String address;
    private Gson mapper;

    public FileAccess(String address) throws FileNotFoundException, UnsupportedEncodingException {
        this.address = address;
        this.mapper = new Gson();
        init();
    }

    private void init() throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(address);
        if (!file.exists()) {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.write("");
            writer.close();
        }
    }

    /**
     * Read the tasks from the text file specified in the constructor
     * @complexity O(n), as all tasks are read
     * @return A TaskList object containing all saved Tasks
     * @throws IOException
     */
    public TaskList read() throws IOException {
        TaskList readTasks = new TaskList();

        String text = new String(Files.readAllBytes(Paths.get(address)));
        String[] split = text.split("\n");

        for (int i = 0; i < split.length; i++) {
            Task t = mapper.fromJson(split[i], Task.class);
            readTasks.add(t);
        }

        return readTasks;
    }

    /**
     * Save a list of tasks to a text file
     * @complexity O(n),  as PrintWriter overwrites the entire file
     * @param list - The TaskList to be saved
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void write(TaskList list) throws FileNotFoundException, UnsupportedEncodingException {
        String tasksAsJson = "";
        for (int i = 0; i < list.size(); i++) {
            tasksAsJson += mapper.toJson(list.get(i)) + "\n";
        }

        PrintWriter writer = new PrintWriter(address, "UTF-8");
        writer.write(tasksAsJson);
        writer.close();
    }

}
