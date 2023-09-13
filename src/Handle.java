
public class Handle {
    /**
     * Position that has the start of the memory block.
     */
    private int startingPos;
    private int length;

    /**
     * This method will initialize startPos and length
     * 
     * @param startPos
     *            Starting position in memory
     * @param length
     *            length of the block in memory
     */
    Handle(int startPos, int length) {
        /** constructing */
        this.startingPos = startPos;
        this.length = length;
    }


    /**
     * 
     * @return length of the memory block (the size on disk). this will have the
     *         space occupied if filled; otherwise the maximum size this block
     *         can hold
     */
    public int getLength() {
        return length;
    }


    /**
     * Sets the length of a block of memory
     * 
     * @param length
     *            is the new length of this handle
     */
    protected void setLength(int length) {
        this.length = length;
    }


    /**
     * This method will get the start of the memory block
     * 
     * @return start of the memory block
     */
    public int getStartingPos() {
        return startingPos;
    }


    /**
     * This method will set the start of the location
     * 
     * @param startingPos
     *            start of memory block
     */
    public void setStartingPos(int startingPos) {
        this.startingPos = startingPos;
    }
}
