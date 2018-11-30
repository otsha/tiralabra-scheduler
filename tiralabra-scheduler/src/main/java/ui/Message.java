package ui;

public enum Message {
    INVALIDCOMMANDERROR("ERROR: Invalid command."),
    TASKCREATIONERROR("ERROR: Task creation failed. Please make sure your date format is correct and that you use '.' as your decimal point."),
    TASKSAVINGERROR("ERROR: Saving tasks failed."),
    SLEEPINGERROR("ERROR: Pausing the output failed.");
    
    private String message;
    
    private Message(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return message;
    }
}
