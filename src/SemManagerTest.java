import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
import student.TestCase;

public class SemManagerTest extends TestCase {
    private SemManager manager;

    /**
     * Before annotation runs before each test
     * 
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
        manager = new SemManager();
    }


    /**
     * 
     * Full parser test
     * 
     * @throws IOException
     * 
     */

    public void testparserfull() throws IOException {

        String[] args = new String[3];

        args[0] = "10";

        args[1] = "4";

        args[2] = "P1Sample_input.txt";

        manager.main(args);

        String output = systemOut().getHistory();

        String referenceOutput = SemManager.readFile("P1Sample_output.txt");

        assertFuzzyEquals(referenceOutput, output);

    }

    /**
     * Test SemManager and Parser
     * 
     * Checking to see if it reads a file that exists
     */

// public void testSemManagerInit() {
// try {
// new String(Files.readAllBytes(Paths.get("output.txt")));
//
// }
// catch (IOException e) {
// e.printStackTrace();
// }
//
// String[] args = new String[3];
// args[0] = "10";
// args[1] = "4";
// args[2] = "P1Sample_input.txt";
// manager.main(args);
// assertFalse(systemOut().getHistory().contains("Error"));
// systemOut().clearHistory();
// }


    /**
     * Test SemManager and Parser
     * 
     * Checking to see if it reads a file that does not exist
     */

//    public void testSemManagerError() {
//        String[] args = new String[3];
//        args = new String[3];
//        args[0] = "10";
//        args[1] = "16";
//        args[2] = "nofile.txt";
//        manager.main(args);
//        assertTrue(systemOut().getHistory().contains("Error"));
//        systemOut().clearHistory();
//    }
}
