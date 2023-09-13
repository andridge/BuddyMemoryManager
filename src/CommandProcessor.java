
public class CommandProcessor {
    private static MemManager mm;
    /** Using a hash table to store data */
    private static MyHashTable hash;

    /**
     * This method will initialize MemManager and HashTable
     * 
     * @param initialMemorySize
     *            the size in bytes of memory manager
     * @param initialHashSize
     *            the number of slot in the initial hash
     */
    public CommandProcessor(int initialMemorySize, int initialHashSize) {
        mm = new MemManager(initialMemorySize);
        hash = new MyHashTable(initialHashSize);
    }


    /**
     * Processing the commands that will be inserted into the Hashtable and the
     * MemoryManager
     * 
     * @param lines
     *            from the command file
     */

    public void command(String[][] lines) throws NoSuchFieldException {
        String action = lines[0][0];
        switch (action) {
            case "insert":
// Pretend getting Handle from mm
                Seminar sem = createSeminar(lines);
                Handle mockFromMM = new Handle(7, 2345);
                int insertKey = Integer.parseInt(lines[0][1]);
                boolean didInsert = hash.insert(insertKey, mockFromMM);
                if (didInsert) {
                    Util.print(sem.toString());
                    try {
                        byte[] serializedSem = sem.serialize();
                        Util.print("Size: " + serializedSem.length);
                    }
                    catch (Exception e) {
                        Util.print("Could not serialize Seminar");
                    }
                }
                break;
            case "delete":
                int deleteKey = Integer.parseInt(lines[0][1]);
                hash.delete(deleteKey);
                break;
            case "search":
                int searchKey = Integer.parseInt(lines[0][1]);
                int foundIndex = hash.search(searchKey, true);
                if (foundIndex > -1) {
                    //
// printLines(lines);
// Get record from Memory and print.
                }
                break;
            case "print":
                print(lines[0][1]);
                break;
            default:
                /** Falls through */
                throw new NoSuchFieldException();
        }
    }

// private static void printLines(String[][] lines) {
// int insertKey = Integer.parseInt(lines[0][1]);
// Util.print("ID: " + insertKey + ", Title: " + lines[1][0]);
// Util.print("Date: " + lines[2][0] + ", Length: " + lines[2][1] + ", X: "
// + lines[2][2] + ", Y: " + lines[2][3] + ", Cost: " + lines[2][4]);
// Util.print("Description: " + lines[4][0]);
// Util.print("Keywords: ", lines[3]);
// }


    /** Custom Printing method */

    private void print(String location) throws NoSuchFieldException {
        switch (location) {
            case "hashtable":
                System.out.println("Hashtable:");
                hash.printHashTable();
                break;
            case "blocks":
                System.out.println("Freeblock List:");
                // Code here
                break;
            default:
                /** Falls through */
                throw new NoSuchFieldException();
        }
    }


    private Seminar createSeminar(String[][] lines) {
        int idin = Integer.parseInt(lines[0][1]);
        String tin = lines[1][0];
        String datein = lines[2][0];
        int lin = Integer.parseInt(lines[2][1]);
        short xin = Short.parseShort(lines[2][2]);
        short yin = Short.parseShort(lines[2][3]);
        int cin = Integer.parseInt(lines[2][4]);
        String[] kin = lines[3];
        String descin = lines[4][0];

        Seminar sem = new Seminar(idin, tin, datein, lin, xin, yin, cin, kin,
            descin);
        return sem;
    }


    /**
     * Getter for the private method above - to use in unit tests
     * 
     * @param lines
     *            - 2D array of Strings
     * @return createSeminar
     */
    public Seminar getCreateSeminar(String[][] lines) {
        return createSeminar(lines);
    }

}
