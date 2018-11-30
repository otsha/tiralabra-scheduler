package access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class IOHandlerTest {

    private Parser input;
    private Printer output;
    private FileAccess file;

    @Before
    public void setUp() throws FileNotFoundException, UnsupportedEncodingException {
        input = new Parser(new Scanner(System.in));
        output = new Printer();
        file = new FileAccess("test.json");
    }
    
    @After
    public void tearDown() {
        File testFile = new File("test.json");
        testFile.delete();
    }

    @Test
    public void handlerIsInitializedCorrectly() throws FileNotFoundException, UnsupportedEncodingException {
        IOHandler io = new IOHandler(input, output, file);

        assertEquals(input, io.input());
        assertEquals(output, io.output());
        assertEquals(file, io.file());
    }
}
