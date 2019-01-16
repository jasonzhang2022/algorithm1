package practice.trick;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

//https://leetcode.com/problems/super-ugly-number/
/*
 * Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the
  first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */
/*
Trick:
1. Output is view as input, too.
2. Each prime is queued to add value to output.
 */
public class SuperUglyNumber {


    public static class QueueValue {
        int prime;
        int inputIndex;

        int result;

    }

    public static int nthSuperUglyNumber(int[] primes, int n) {

        int[] input = new int[n];
        input[0]=1;
        int outputIndex = 1;
        PriorityQueue<QueueValue>  queue = new PriorityQueue<>( (a, b)->Integer.compare(a.result, b.result));

        for (int prime: primes){
            QueueValue value = new QueueValue();
            value.prime = prime;
            value.inputIndex = 0;
            value.result = prime;
            queue.add(value);
        }
        while (outputIndex<n){
            QueueValue nextValue = queue.poll();
            if (input[outputIndex-1]!=nextValue.result) {
                input[outputIndex++]=nextValue.result;
            }
            nextValue.inputIndex++;
            nextValue.result = nextValue.prime * input[nextValue.inputIndex];
            queue.offer(nextValue);
        }
        return input[n-1];

    }

    public static final class TestCase {
        @Test
        public void test2(){
            int[] prime={2, 7, 13, 19};
            assertEquals(32, nthSuperUglyNumber(prime, 12));
        }

    }

}
