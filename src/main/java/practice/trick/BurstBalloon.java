package practice.trick;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

//https://leetcode.com/problems/burst-balloons/
/*
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 *  Here left and right are adjacent indices of i. After the burst, the left and
 *   right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
/*
 NOTE: like almost all DP problem, work from end result, move backward to beginning.

 */
public class BurstBalloon {
    long[][] cache;
    int n;
    public long maxCoinsCachedDp(int[] nums){
        n = nums.length;
        cache = new long[n][n];
        helper(nums, 0, n-1);
        return cache[0][n-1]+nums[0]*nums[n-1]+Math.max(nums[0], nums[n-1]);
    }

    /**
     * The maximal coin earned for segments from i to j.
     */
    public long helper(int[] num, int i, int j) {
        if (cache[i][j]!=0){
            return cache[i][j];
        }
        if (j==i+1){
           return 0;
        }

        long max = Long.MIN_VALUE;
        for (int lastBalloonIndex = i+1; lastBalloonIndex<j; lastBalloonIndex++){
            long temp = helper(num, i, lastBalloonIndex) + helper(num, lastBalloonIndex, j)+num[i]*num[j]*num[lastBalloonIndex];
            max= Math.max(max, temp);
        }
        cache[i][j]= max;
        return max;
    }

    public static final class TestCase {

        @Test
        public void testCachedDp(){
            BurstBalloon burster=new BurstBalloon();
            long result=burster.maxCoinsCachedDp(new int[]{3, 1, 5, 8});
            assertThat(result, equalTo(167l));
        }
    }
}
