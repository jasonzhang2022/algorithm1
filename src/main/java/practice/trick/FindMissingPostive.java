package practice.trick;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 *
 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

If we put every number x to position x-1;
At the end, the first position without a number is missed.
 */
/*
 * Pigeonhole principle
 */
public class FindMissingPostive {

    static int findingMissing(int[] nums) {
        int n=nums.length;

        boolean hasN=false;

        int i=0;

        while (i<n) {

            //case 1. >=n;
            if (nums[i]<0){
                //don't care negative number
                i++;
            } else if (nums[i]<i){
                //0<= nums[i] <i
                // set the value back to its position.
                // repeated number is processed fine.
                int index=nums[i];
                nums[index]=index;
                nums[i]=-1;
                i++;
            } else if (nums[i]==i) {
                i++;
            } else if (nums[i] <n ) {
                // i<nums[i]<n
                int index=nums[i];
                int temp = nums[index];
                if (temp==index) {
                    // nums[index] already has its correct value.
                    nums[i] =-1;
                    i++;
                } else {
                    nums[index]=index;
                    nums[i]= temp;
                    // don't increase i.
                }
            } else if (nums[i]==n){
                hasN= true;
                nums[i]=-1;
                i++;
            } else {
                nums[i]=-1;
                i++;
            }
        }

        for (i=1; i<n; i++){
            if (nums[i]<0){
                return i;
            }
        }
        if (hasN) {
            return n+1;
        } else {
            return n;
        }
    }

    public static final class TestCase {
        @Test
        public void test() {
            assertEquals(findingMissing(new int[]{0,1,2,3}), 4);
            assertEquals(findingMissing(new int[]{1,2,3,4}), 5);
            assertEquals(findingMissing(new int[]{1,3,3,4}), 2);
            assertEquals(findingMissing(new int[]{4,-1,0,2,5, 1, 2,5,4}), 3);
            assertEquals(findingMissing(new int[]{3,4,-1,1}), 2);
        }
    }


}


