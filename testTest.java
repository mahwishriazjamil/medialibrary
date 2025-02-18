import org.junit.Test;
import static org.junit.Assert.*;

package medialibrary;


public class testTest {

    @Test
    public void testSystemOut() {
        // Capture the output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the method
        System.out.println("Hello World");

        // Verify the output
        assertEquals("Hello World\n", outContent.toString());
    }
}