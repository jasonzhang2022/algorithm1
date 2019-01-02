package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * We use SegmentedTree to implement RMQ
 */
public class SegmentedTree {


    int[] data;
    int[] tree;

    public SegmentedTree(int[] input) {
        this.data = input;
        int depth = Double.valueOf(Math.ceil(Math.log(input.length) / Math.log(2))).intValue();
        int size = Double.valueOf(2 * Math.pow(2, depth)).intValue() + 1;
        tree = new int[size];
        init(1, 0, input.length - 1);
    }

    /*
      InteralStart and intervalEnd are processing context.
     */
    public void init(int nodeIndex, int intervalStart, int intervalEnd) {
        if (intervalEnd == intervalStart) {
            //lead node;
            tree[nodeIndex] = data[intervalEnd];
        } else {

            //when we come here. interval end is at least one bigger than start.
            int leftEnd = intervalStart + (intervalEnd - intervalStart) / 2;
            init(nodeIndex * 2, intervalStart, leftEnd);
            init(nodeIndex * 2 + 1, leftEnd + 1, intervalEnd);

            // compute the parent value from child value.
            // This is a signature of tree traversal. Updation.
            tree[nodeIndex] = Math.min(tree[nodeIndex * 2], tree[nodeIndex * 2 + 1]);
        }
    }

    public int query(int targetedStart, int targetedEnd) {
        return queryHelper(1, 0, data.length - 1, targetedStart, targetedEnd);
    }

    /**
     * intervalStart and intervalEnd are node's property. Also called processing context.
     * targetedStart, and targetedEnd are our range.
     */
    public int queryHelper(int nodeIndex, int intervalStart, int intervalEnd, int targetedStart, int targetedEnd) {
        if (intervalStart == targetedStart && intervalEnd == targetedEnd) {
            return tree[nodeIndex];
        }

        // we only need to queryHelper part of the tree.
        int leftTreeEnd = intervalStart + (intervalEnd - intervalStart) / 2;
        if (targetedEnd <= leftTreeEnd) {
            return queryHelper(nodeIndex * 2, intervalStart, leftTreeEnd, targetedStart, targetedEnd);
        } else if (targetedStart > leftTreeEnd) {
            return queryHelper(nodeIndex * 2 + 1, leftTreeEnd + 1, intervalEnd, targetedStart, targetedEnd);
        } else {
            // the interval span two children nodes.
            int leftMin = queryHelper(nodeIndex * 2, intervalStart, leftTreeEnd, targetedStart, leftTreeEnd);
            int rightMin = queryHelper(nodeIndex * 2 + 1, leftTreeEnd + 1, intervalEnd, leftTreeEnd + 1, targetedEnd);
            return Math.min(leftMin, rightMin);
        }
    }

    public void update(int dataIndex, int value) {
        updateHelper(1, 0, data.length - 1, dataIndex, value);
    }

    public void updateHelper(int nodeIndex, int intervalStart, int intervalEnd, int dataIndex, int value) {
        if (intervalStart == intervalEnd && intervalStart == dataIndex) {
            tree[nodeIndex] = value;
            return;
        }

        int leftTreeEnd = intervalStart + (intervalEnd - intervalStart) / 2;
        if (dataIndex <= leftTreeEnd) {
            updateHelper(nodeIndex * 2, intervalStart, leftTreeEnd, dataIndex, value);
        } else {
            updateHelper(nodeIndex * 2 + 1, leftTreeEnd + 1, intervalEnd, dataIndex, value);
        }
        tree[nodeIndex] = Math.min(tree[nodeIndex * 2], tree[nodeIndex * 2 + 1]);

    }

    public static class TestCase {


        @Test
        public void testSimple() {
            int inputLen = 10;
            int[] input = new int[inputLen];
            for (int i = 0; i < inputLen; i++) {
                input[i] = i + 5;
            }
            Shuffler.shuffle(input);
            SegmentedTree segmentedTree = new SegmentedTree(input);
            int start = 5;
            int end = 9;
            int min = Integer.MAX_VALUE;
            for (int j = start; j <= end; j++) {
                min = Math.min(min, input[j]);
            }

            int computedMin = segmentedTree.query(start, end);
            assertThat(computedMin, equalTo(min));
            segmentedTree.update(start + 1, 4);
            computedMin = segmentedTree.query(start, end);
            assertThat(computedMin, equalTo(4));

        }

        @Test
        public void test() {
            int inputLen = 1000;
            int[] input = new int[inputLen];
            for (int i = 0; i < inputLen; i++) {
                input[i] = i;
            }

            Shuffler.shuffle(input);
            Random random = new Random(new Date().getTime());

            SegmentedTree segmentedTree = new SegmentedTree(input);

            for (int i = 0; i < 50; i++) {
                int start = random.nextInt(inputLen);
                int end = random.nextInt(inputLen);
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }

                int min = Integer.MAX_VALUE;
                for (int j = start; j <= end; j++) {
                    min = Math.min(min, input[j]);
                }
                int computedMin = segmentedTree.query(start, end);
                if (computedMin != min) {
                    System.out.printf("not equa1: %d, %d: %d, %d\n", start, end, min, computedMin);
                    System.out.println(Arrays.toString(Arrays.copyOfRange(input, start, end + 1)));
                }
                assertThat(computedMin, equalTo(min));
            }
        }
    }
}
