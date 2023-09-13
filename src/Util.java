
public class Util {

    /**
     * @param <T> any
     * @param arr
     *            - array of any - can be used for both hash table and memory
     *            pool
     * 
     * @return - newArr - array of any
     */

    public static <T> T[] doubleSize(T[] arr) {
        @SuppressWarnings("unchecked")
        T[] newArr = (T[])java.lang.reflect.Array.newInstance(arr.getClass()
            .getComponentType(), arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }


    /**
     * @param arr
     *            - can be used for hash table only (Will probably change this
     *            in the future)
     * 
     * @return - newArr
     */

    public static Integer[] doubleSizeHashTableKeysArray(Integer[] arr) {
        int newArrSize = arr.length * 2;
        Integer[] newArr = new Integer[newArrSize];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        System.out.println("Hash table expanded to " + newArrSize + " records");
        return newArr;
    }


    /**
     * Printing based on one string argument
     * 
     * @param str
     *            - message
     */

    public static void print(String str) {
        System.out.println(str);
    }


    /**
     * Printing based on two arguments
     * 
     * @param str
     *            - message
     * @param arr
     *            - array of messages
     */

    public static void print(String str, String[] arr) {
        System.out.print(str);
        print(arr);
    }


    /**
     * Printing based on one array - prints the last line on a new line
     * 
     * @param arr
     *            - array of messages
     */

    public static void print(String[] arr) {
        if (arr.length >= 1) {
            System.out.print(arr[0]);
        }

        // note that i starts at 1, since we already printed the element at
        // index 0
        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(", " + arr[i]);
            }
            else {
                System.out.print(", " + arr[i]);
            }
        }
    }


    /**
     * Prints ====== for better visualization
     */

    public static void printDiv() {
        System.out.println("============");
    }
}
