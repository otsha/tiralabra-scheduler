package access;

import data.Task;
import java.util.Scanner;

public class Parser {
    
    private Scanner input;
    
    public Parser(Scanner input) {
        this.input = input;
    }
    
    public String next() {
        return input.nextLine();
    }
    
    public Task buildTask(String name, String payment, String deadline, String timeEstimate) {
        try {
            return new Task(name, Double.parseDouble(payment), deadline, Integer.parseInt(timeEstimate));
        } catch (Exception e) {
            return null;
        }
    }
    
}
