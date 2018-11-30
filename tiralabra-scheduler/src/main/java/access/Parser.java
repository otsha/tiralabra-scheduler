package access;

import data.Task;
import java.util.Scanner;

public class Parser {
    
    private Scanner input;
    
    public Parser(Scanner input) {
        this.input = input;
    }
    
    /**
     * Reads the next line that the Scanner is offering
     * @return 
     * @see java.util.Scanner#nextLine() 
     */
    public String next() {
        return input.nextLine();
    }
    
    /**
     * Attempts to construct a Task object from the given parameters.
     * @param name - The name of the Task
     * @param payment - The payment for the Task
     * @param deadline - The deadline of the task in DD.MM.YYYY format
     * @param timeEstimate - The estimated amount of time to complete the task
     * @return Task if successful, null otherwise
     */
    public Task buildTask(String name, String payment, String deadline, String timeEstimate) {
        try {
            return new Task(name, Double.parseDouble(payment), deadline, Integer.parseInt(timeEstimate));
        } catch (Exception e) {
            return null;
        }
    }
    
}
