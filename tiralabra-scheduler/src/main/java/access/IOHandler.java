package access;

public class IOHandler {
    
    private Parser input;
    private Printer output;
    private FileAccess file;
    
    public IOHandler(Parser input, Printer output, FileAccess file) {
        this.input = input;
        this.output = output;
        this.file = file;
    }
    
    public Parser input() {
        return input;
    }
    
    public Printer output() {
        return output;
    }
    
    public FileAccess file() {
        return file;
    }
}
