package practice.trick;

/*
 * In this solution
 * cache[n-1] is not a solution, but an intermediate state to assist finding a solution.
 * The final solution can be derived by scanning call cache.
 *
 * The intermediate state is designed such a way that downstream result can be obtained from upstream result
 *
 */
//https://leetcode.com/problems/longest-increasing-subsequence/
/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */


/*
 * Given a set of [a,b] intervals on the number line, find the longest ordered sequence (consecutive in original order) of
 *  (a subset of) the intervals such that each consecutive interval is nested inside the previous one.
"Nested" means this: If interval X is nested inside Y, then Y.a < X.a and X.b < Y.b.
E.g. given [[3,4], [1,9], [2,8], [4,5], [11,12]]
Return: [[1, 9], [2, 8], [4, 5]].鐣欏璁哄潧-涓€浜�-涓夊垎鍦�

Follow-up #1:
Given a set of [a,b] intervals on the number line, find the longest ordered sequence of (a subset of) the intervals such that
each consecutive interval is nested inside the previous one.
"Nested" means this: If interval X is nested inside Y, then Y.a < X.a and X.b < Y.b.
E.g. given [[1,10], [3,4], [2,9], [3,8], [4,5], [11,12]]
Return: [[1,10], [2,9], [3,8], [4,5]]

Follow-up #2:
Given a set of [a,b] intervals on the number line, find the longest sequence of (a subset of) the intervals such that
 each consecutive interval is nested inside the previous one.
"Nested" means this: If interval X is nested inside Y, then Y.a < X.a and X.b < Y.b.
Note: the original order doesn't need to be maintained.
E.g. given [[1,10], [3,4], [4,5], [11,12], [3,8], [2,9]]
Return: [[1,10], [2,9], [3,8], [4,5]]

 */

//https://leetcode.com/problems/russian-doll-envelopes/
/*
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
/*
 *
 * Analysis
If an envelope late in the sequence can contain the other one, this envelope must have both greater width and length.
So we first sort the envelope by with in ascending order.  After this, we only need checking the length for containment.

The length can be checked by LIS algorithm
 */
public class LongestIncreasingSequence {
    /*
     * cache[i] is the longest increasing sequence which ends with nums[i];
	 */
    int[] cache=null;
    int max=0;
    public int find(int[] nums){

        cache[0]=1;
        max=1;

        for (int i=1; i<nums.length; i++){
            int imax=1;
            for (int j=i-1; j>=0; j--){
                if (nums[i]>nums[j]){
                    imax=Math.max(imax, cache[j]+1);
                }
            }
            cache[i]=imax;
            max=Math.max(imax, max);
        }
        return max;

    }
}
