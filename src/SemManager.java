import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.FileNotFoundException;

public class SemManager {
    private static CommandProcessor cp;

    /**
     * 
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * 
     * @return the string
     * 
     * @throws IOException
     * 
     */

    static String readFile(String path) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));

        return new String(encoded);

    }


    private static Parser createParser(String[] args) {
        Parser parser = null;
        try {
            parser = new Parser(args[2]);
        }
        catch (FileNotFoundException e) {
            Util.print("Error: File Not Found! Check syntax");
            return null;
        }
        return parser;
    }


    /**
     * @param args
     *            - initial sizes of Memory pool, Hash table and name of the
     *            input file
     */

    public static void main(String[] args) {
        Parser parser = createParser(args);
        if (parser == null) {
            return;
        }
        int initialMemorySize = 10;
        int initialHashSize = 16;
        try {
            initialMemorySize = Integer.parseInt(args[0]);
            initialHashSize = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            return;
        }

        cp = new CommandProcessor(initialMemorySize, initialHashSize);

        String[][] nextChunk = parser.readNextChunk();

        while (nextChunk != null) {
            if ((nextChunk[0][0]).equals("")) {
                nextChunk = parser.readNextChunk();
                continue;
            }
            try {
                cp.command(nextChunk);
            }
            catch (NoSuchFieldException e) {
                Util.print("Syntax error in input file.");
            }
            nextChunk = parser.readNextChunk();
        }
        parser.closeScan();
    }
}
