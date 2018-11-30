package access;

import data.Task;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ParserTest {
    
    private Parser p;
    
    public ParserTest() {
        p = new Parser(new Scanner("helloWorld"));
    }
    
    @Test
    public void nextReturnsTheReadString() {
        assertEquals("helloWorld", p.next());
    }
    
    @Test
    public void buildTaskReturnsATaskWithValidParams() {
        Task t = p.buildTask("hello", "2555.2", "21.12.2019", "56");
        
        assertEquals("hello", t.getName());
        assertEquals(2555.2, t.getPayment(), 0.0001);
        assertEquals("21.12.2019", t.getDeadline());
        assertEquals(56, t.getTimeEstimate());
    }
    
    @Test
    public void buildTaskReturnsNullIfParamsAreInvalid() {
        Task t = p.buildTask("", "aaaabcd", "99.99.9999", "j");
        assertTrue(t == null);
    }
    
    
}
