package app;

import access.FileAccess;
import access.IOHandler;
import access.Parser;
import access.Printer;
import data.Task;
import data.TaskList;
import java.io.IOException;
import java.util.Scanner;
import logic.Scheduler;
import ui.TextInterface;
import ui.Visualizer;

class Main {

    public static void main(String[] args) throws IOException {
        
        // Init all the components used by the application
        Scanner input = new Scanner(System.in);

        Parser inputHandler = new Parser(input);
        Printer outputHandler = new Printer();
        FileAccess access = new FileAccess("tasks.json");

        IOHandler io = new IOHandler(inputHandler, outputHandler, access);

        Visualizer v = new Visualizer(io);
        Scheduler scheduler = new Scheduler();
        
        // Load any saved tasks from the file
        TaskList tasks = io.file().read();
        
        // Init and start the UI
        TextInterface ui = new TextInterface(scheduler, tasks, io, v);
        ui.start();

    }
}
