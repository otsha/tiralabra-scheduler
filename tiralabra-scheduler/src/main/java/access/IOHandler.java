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
    
    /**
     * Returns the Parser handling inputs
     * @return 
     * @see access.Parser
     */
    public Parser input() {
        return input;
    }
    
    /**
     * Returns the Parser handling outputs
     * @return 
     * @see access.Printer
     */
    public Printer output() {
        return output;
    }
    
    /**
     * Returns the FileAccess handling external files
     * @return 
     * @see access.FileAccess
     */
    public FileAccess file() {
        return file;
    }
}
