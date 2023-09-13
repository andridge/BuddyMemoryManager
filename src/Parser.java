
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private Scanner scan;

    /**
     * We are constructing the Parser based on the name of file
     * 
     * @param str
     *            - name of file
     */
    public Parser(String str) throws FileNotFoundException {
        scan = new Scanner(new File(str));
    }


    /**
     * Reading 5 lines at a time of the command file
     * 
     * @return insertChunck
     */

    public String[][] readNextChunk() {
        if (!scan.hasNextLine()) {
// End of the file.
            return null;
        }
        String[] commands = getNextLine(true);

        if (commands[0].toLowerCase().equals("insert")) {
            String[][] insertChunck = new String[5][];
            insertChunck[0] = commands;
            int i = 1;
            while (i < 5) {
                insertChunck[i] = getNextLine(i == 1 || i == 4 ? false : true);
                i++;
            }
            return insertChunck;
        }
        else {
            return new String[][] { commands };
        }
    }


    private String[] getNextLine(boolean shouldSplit) {
        String currentLine = scan.nextLine().trim();

        if (shouldSplit) {
            while (currentLine.contains("  ")) {
                currentLine = currentLine.replace("  ", " ");
            }
            return currentLine.split(" ");
        }
        else {
            return new String[] { currentLine };
        }
    }


    /**
     * Done Scanning the file
     */
    public void closeScan() {
        scan.close();
    }
}
