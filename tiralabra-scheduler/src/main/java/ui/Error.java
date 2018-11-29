package ui;

public enum Error {
    INVALIDCOMMANDERROR("ERROR: Invalid command."),
    TASKCREATIONERROR("ERROR: Task creation failed. Please make sure your date format is correct and that you use '.' as your decimal point."),
    TASKSAVINGERROR("ERROR: Saving tasks failed.");
    
    private String message;
    
    private Error(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return message;
    }
}
