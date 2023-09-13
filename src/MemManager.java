import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class MemManager {
    private MyHashTable hashTable;
    private Freeblock list;
    private int blockSize;
    private int numOfAllocate = 0;
    private MemoryPool pool;

    public MemManager(int blockSize, int initialHashTableSize) {
        hashTable = new MyHashTable(initialHashTableSize);
        list = new Freeblock(blockSize);
        this.blockSize = blockSize;
    }

    public Handle insert(byte[] space, int size) {
        int position = list.findPosition(size + 2);
        if (position != -1) {
            // Successfully insert the record into the hash table.
            Handle handle = new Handle(position,size); // Simulating handle as the position.
            hashTable.insert(handle.getStartingPos(), handle);
            list.updateList(size + 2);
            return handle;
        } else {
            while (position == -1) {
                // Reallocate the pool and store the record again.
                pool.reallocate();
                numOfAllocate++;
                // New free block needs to be inserted into the free block list.
                Node newFreeBlock = new Node(blockSize, numOfAllocate * blockSize);
                list.insert(newFreeBlock);
                // Update the list: block size and block position.
                position = list.findPosition(size + 2);
            }
            Handle handle = new Handle(position,size);
            hashTable.insert(handle.getStartingPos(), handle);
            list.updateList(size + 2);
            return handle;
        }
    }

    public void remove(Handle handle) {
        int position = handle.getStartingPos();
        int recordSize = pool.read(position) & 0xFFFF;
        Node freeBlock = new Node(recordSize + 2, position);
        list.insert(freeBlock);
        // Remove the record from the hash table.
        hashTable.delete(handle.getStartingPos());
    }

    public int get(byte[] space, Handle handle, int size) {
        int copySize;
        Handle foundHandle = hashTable.search(handle.getStartingPos());
        if (foundHandle == null) {
            return 0; // Handle not found in the hash table.
        }
        int memSize = foundHandle.getLength();
        if (memSize > size) {
            copySize = size;
        } else {
            copySize = memSize;
        }
    
        // Access the memory block based on your Handle class
        pool.read(space, handle.getStartingPos() + 2, size);
        return copySize;
    }
    
    
    
    

    public void dump() {
        System.out.println(list.toString());
    }

    public String byteToString(byte[] data, int size) {
        String str = null;
        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte[] buf = new byte[size];
        buffer.get(buf);
        try {
            str = new String(buf, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
