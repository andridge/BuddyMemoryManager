# Buddy Method Memory Manager

## Introduction

The Buddy Method Memory Manager is a memory allocation and management technique used in computer systems. It allows for efficient allocation and deallocation of memory by dividing the memory pool into sections of sizes that are powers of two. This readme file provides an overview of how the Buddy Method Memory Manager works and how to use it.

## Key Concepts

### Memory Pool

The memory pool is a byte array used to store information. It must always have a size that is a power of two. The individual sections within the memory pool, whether they are "free to use" or "reserved," must also have sizes that are powers of two.

### Free Block List (FBL)

The Free Block List is an array that keeps track of free sections within the Memory Pool. Each index in the FBL represents different powers of two sections of the Memory Pool.

- Index zero stores blocks of size 2^0.
- Index one stores blocks of size 2^1.
- ...
- Index N stores blocks of size 2^N.

Each block in the FBL contains the start position of the section it refers to in the Memory Pool.

### Insertion

When inserting data into the Memory Pool, the Buddy Method Memory Manager finds the smallest power of two section that can contain the data. For example, if the data size is 7, it will use a section of size 8. If the data size is 9, it will use a section of size 16.

The manager checks the FBL to find a suitable free section and updates it accordingly. If the selected section is larger than needed, it may be split into smaller sections.

### Merging

Merging occurs when a block is freed. If there are adjacent free blocks in the FBL that are buddies (i.e., their binary representations of start positions are the same except for the bit corresponding to their lengths), they are merged into a single larger block. This process continues recursively until no further merging is possible.

## Example

Let's illustrate how the Buddy Method Memory Manager works with an example:

1. The Memory Pool (MP) has a size of 256 (2^8), and the Free Block List (FBL) has 9 indices (0 to 8).
2. Initially, only index 8 of the FBL has a block, which represents the entire Memory Pool.
3. If data of size 7 needs to be inserted, the manager searches for a size 8 section in the FBL.
4. Since the 2^8 block is too large, it is split into two size 2^7 blocks (128 each).
5. The FBL is updated to reflect the new blocks in index 7.
6. The process continues until a size 2^3 block is found.
7. The data is inserted into this block, and the block is removed from the FBL.

## Insertion and Merging

When data is deleted, a block of the appropriate size is added back to the FBL. If adjacent free blocks are buddies, they are merged.

For example, if a size 7 block is deleted, a size 8 block is added back to the FBL. If there is an adjacent size 8 block, they are merged into a size 16 block.

Merging is based on comparing binary representations of start positions with bit masking to determine if two blocks are buddies.

## Conclusion

The Buddy Method Memory Manager is a memory management technique that efficiently allocates and deallocates memory by dividing it into sections of powers of two. It utilizes the Free Block List to keep track of available memory sections and supports both insertion and merging operations.

To use this memory manager, you should understand binary representation and bitwise operations, as they are essential for identifying and merging buddy blocks.

This readme provides an overview of the Buddy Method Memory Manager's key concepts and operations. To implement and use this memory manager in your projects, refer to the specific documentation or code provided.