package practice.trick;

import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * A list of digits, create a biggest number with k digits
 */
/*
  Trick
  For one array,

  Stack,
  dynamic queue.

  Two arrays:
  what is brute force approach?
  Suppose there is a final solution, what is contribution from each array?
 */
public class MaximumNumber {

    public static int maxNumberOneArray(int[] digits, int k){
        Deque<Integer> deque = new LinkedList<>();

        for (int i=0; i<digits.length; i++){
            int currentDigit=digits[i];
            while (!deque.isEmpty() && deque.size()+ digits.length-i>k && currentDigit>deque.peekLast()){
                deque.pollLast();
            }
            deque.offerLast(currentDigit);
        }

        int  count = 0;
        int num = 0;
        while (count<k && !deque.isEmpty()){
            num=num*10+deque.pollFirst();
            count++;
        }

        return num;
    }

    public static class IndexedValue {
        int value;
        int index;

        public int getValue(){
            return value;
        }
        public int getIndex(){
            return index;
        }

    }
    public static int maxNumberOneArrayGreedy(int[] digits, int k){

       IndexedValue[] values = new IndexedValue[digits.length];
       PriorityQueue<IndexedValue>  queue = new PriorityQueue<>((a, b)->{
           int v= Integer.compare(b.value, a.value);
           if (v!=0){
               return v;
           }
           return Integer.compare(a.index, b.index);
       });

        for (int i=0; i<digits.length; i++) {
            IndexedValue value = new IndexedValue();
            value.index = i;
            value.value = digits[i];
            values[i]=value;
            if (digits.length-i>k){
                queue.add(value);
            }
        }
        int num = 0;
        int lastIndex = -1;
        for (int i=0; i<k; i++){
            queue.offer(values[digits.length-k+i]);
            IndexedValue value = queue.poll();
            if (value.index<lastIndex){
                i--;
                continue;
            }
            lastIndex = value.index;
            num=num*10+value.value;
        }
        return num;
    }

    /**
     * Brute force.
     * Suppose,
     * array 1 contribute  0,   1, 2, .. k
     * array 2 contribute  k,   k-1, k-2, ...0
     *
     * Combine each portion into ine segment with k elements.
     * If both element has equal digits, use the one following by bigger elements.
     *
     *
     * Suppose we already has a biggest result with k elements. How can find out the biggest result with k-1.
     *
     * Start from beginning, delete the first element which is smaller than next element.
     * element[k] is assumed to be Integer.MIN_VALUE;
     *
     * Comparing this logic with remove DuplicateLetter, and next permutation number.
     *
     *
     */
    public static int maxNumberTwoArrayGreedy(int[] digits1, int[] digits2, int k){

        return 0;
    }

    public static final class  TestCase {
        //mergeTwoArray

        @Test
        public void testSimple() {

            assertEquals(maxNumberOneArray(new int[]{9,6, 6,5,7}, 3), 967);
            assertEquals(maxNumberOneArray(new int[]{8,9,7,6}, 2), 97);
            assertEquals(maxNumberOneArray(new int[]{8,9,6,7}, 2), 97);
            assertEquals(maxNumberOneArray(new int[]{9,8,7,6}, 2), 98);
        }

        @Test
        public void testSimpleGreedy() {
            assertEquals(maxNumberOneArrayGreedy(new int[]{9,6, 6,5,7}, 3), 967);
            assertEquals(maxNumberOneArrayGreedy(new int[]{8,9,7,6}, 2), 97);
            assertEquals(maxNumberOneArrayGreedy(new int[]{8,9,6,7}, 2), 97);
            assertEquals(maxNumberOneArrayGreedy(new int[]{9,8,7,6}, 2), 98);
        }


    }
}
