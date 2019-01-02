package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * What it is used for.
 * To queryHelper cumulative sum, (or range sum)  while dynamically adding and removing value.
 * <p>
 * Cumulative frequency
 * <p>
 * We only keep sum. We don't keep value.
 * <p>
 * Why we need tree, we need to maintain some property (sum, order) while repeated mutate the data(adding, deleting)
 * <p>
 * Theory:
 * Tree[idx] only has the sum for the the last idx-2^r+1 to idx inclusive.
 * <p>
 * NOTE: tree[0] is not used. It is always zero.
 * <p>
 * https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees
 * <p>
 * How to read last bit.
 * <p>
 * lastbit = n&-n;
 */
public class BinaryIndexedTree {

    int[] tree;

    //maxIds can't be changed.
    BinaryIndexedTree(int maxIds) {
        //add 1 since index zero is not used.
        tree = new int[maxIds + 1];
    }

    public int readSum(int idx) {

        int sum = 0;

        while (idx >0) {
            sum += tree[idx];
            int lastBit = idx & -idx;
            idx -= lastBit;
        }
        return sum;
    }

    public void increase(int idx, int value) {
        int mdxIdx = this.tree.length - 1;
        while (idx <= mdxIdx) {
            tree[idx] += value;
            int lastBit = idx & -idx;
            idx += lastBit;
        }
    }

    public int readValue(int idx) {
        return readSum(idx) - readSum(idx - 1);
    }

    public void set(int idx, int value) {
        int oldValue = readValue(idx);
        int delta = value - oldValue;
        increase(idx, delta);
    }


    public static class BinaryIndexedTreeTest {
        @Test
        public void test() {

            BinaryIndexedTree bit = new BinaryIndexedTree(16);
            int[] input = new int[]{1, 0, 2, 1, 1, 3, 0, 4, 2, 5, 2, 2, 3, 1, 0, 2};
            int[] sum = new int[input.length];
            sum[0] = input[0];
            for (int i = 1; i < input.length; i++) {
                sum[i] = input[i] + sum[i - 1];
            }
            for (int i = 0; i < input.length; i++) {
                bit.increase(i+1, input[i]);
            }

            for (int i = 0; i < input.length; i++) {
                assertEquals(sum[i], bit.readSum(i+1));
            }
        }
    }

}
