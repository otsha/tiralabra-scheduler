package app;

import ui.TextInterface;
import data.Task;
import data.TaskList;
import java.util.Scanner;
import logic.Scheduler;

class Main {

    public static void main(String[] args) {
        
        Scheduler scheduler = new Scheduler();
        TaskList tasks = new TaskList();
        Scanner input = new Scanner(System.in);

        //List<Task> tasks = new ArrayList<>();

        // TEST/DEMO DATA
        // Fastest: wow
        // Slowest: infinity
        // EDD: homework
        // LDD: infinity
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

        TextInterface ui = new TextInterface(scheduler, tasks, input);
        ui.start();
        
    }
}
