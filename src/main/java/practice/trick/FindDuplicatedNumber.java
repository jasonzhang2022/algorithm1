package practice.trick;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

//https://leetcode.com/problems/find-the-duplicate-number/
/*
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 *  prove that at least one duplicate number must exist.
 *  Assume that there is only one duplicate number, find the duplicate one.
 *
 *
 *  Exactly the same problem as LinkedListCycleII
 */
/*
Approach 1: like linkedListCycle detection. Slow and fast point
approach 2. DAG cycle detection.
Approach 3. XOR
Approach 4: in place element touch detection.
 */
public class FindDuplicatedNumber {

    static int xor(int[] numbers){
        int len = numbers.length;
        int n = len-1;
        int result = 0;
        for (int i=1; i<=n; i++){
            result ^= i^numbers[i];
        }
        result^=numbers[0];
        return result;
    }

    static int sort(int[] numbers){
        int len=numbers.length;
        int n=len-1;
        for (int i=0; i<len-1; i++){
            int expectedNumber = i+1;

            while (numbers[i]!=expectedNumber){
                int temp=numbers[i];
                int expectedPosition = temp-1;
                if (numbers[expectedPosition] == temp){
                    // try to put temp to its position. But its position already has the number.
                    return temp;
                }

                numbers[i]=numbers[expectedPosition];
                numbers[expectedPosition] = temp;
            }
        }
        return numbers[n];
    }

    static int fastSlow(int[] numbers){

        int head = 0;
        int slow = numbers[head];
        int fast = numbers[slow];

        while (slow!=fast){
            slow = numbers[slow];
            fast = numbers[fast];
            fast = numbers[fast];
        }

        int slow1 = head;
        while (slow1!=slow){
            slow1=numbers[slow1];
            slow = numbers[slow];
        }

      return slow;
    }

    public static final class TestCase {
        @Test
        public void test() {
            int n= 200;
            List<Integer> ints= IntStream.concat(IntStream.range(1, n+1), IntStream.of(20)).boxed().collect(Collectors.toList());
            Collections.shuffle(ints);
            int[] input = ints.stream().mapToInt(Integer::intValue).toArray();
            assertEquals(xor(input), 20);
            assertEquals(sort(input), 20);
        }

        @Test
        public void testSlowFast() {
            int n= 200;
            List<Integer> ints= IntStream.concat(IntStream.range(1, n+1), IntStream.of(20)).boxed().collect(Collectors.toList());
            for (int i=0; i<100; i++ ){
                Collections.shuffle(ints);
                int[] input = ints.stream().mapToInt(Integer::intValue).toArray();
                assertEquals(fastSlow(input), 20);
            }
        }

        @Test
        public void testSlowFast1() {
            assertEquals(fastSlow(new int[]{5,1,4,5,2,3}), 5);
        }
    }
}
