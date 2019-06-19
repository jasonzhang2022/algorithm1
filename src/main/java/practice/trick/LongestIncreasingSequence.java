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

import jdk.nashorn.api.scripting.AbstractJSObject;

import java.util.*;

import static org.junit.Assert.assertEquals;

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
    public static int find(int[] nums){
        int[] temp = new int[nums.length+1];
        temp[0]=Integer.MIN_VALUE;
        temp[1]=nums[0];

        int lastLen = 1;
        for (int i=1; i<nums.length; i++){
            int index = Arrays.binarySearch(temp, 0, lastLen+1, nums[i]);
            if (index>0){
                continue;
            }
            int position = -index-1;
            temp[position] = nums[i];
            if (position==lastLen+1){
                lastLen++;
            }
        }
        return lastLen;
    }


    /*
      We sort the internals.

      We want to find the longest increasing by internal end. So internal internal late in sequence will contain
      internal early in the sequence.

      However, we need also make sure internal late in the sequence has smaller start_point so "containing" condition
      is satisfied.

     */
    public static int longestInternal(int[][] intervals){

        //descending order by start position. So internal later in sequence has smalll value containing those internal
        // early in sequence.
        Arrays.sort(intervals, Comparator.comparingInt((int[] interval)->interval[0]).reversed());

        //We need decide longest increasing sequence on end position.
        int n = intervals.length;
        int[] results = new int[n+1];
        results[0]= Integer.MIN_VALUE;
        results[1]= intervals[0][1];

        int lastLen = 1;

        for (int i=1; i<n; i++){
            int endValue = intervals[i][1];

            int foundEnd = Arrays.binarySearch(results, 0, lastLen+1, endValue);
            if (foundEnd>0){
                continue;
            }
            foundEnd = -foundEnd -1;
            results[foundEnd] = endValue;
            if (foundEnd == lastLen+1) {
                lastLen++;
            }
        }
        return lastLen;
    }

    public static int longestInternalDAG(int[][] intervals){
        int n = intervals.length;
        List<Integer>[] adj = new List[n];
        for (int i=0; i<n; i++) {
            adj[i]= new LinkedList<>();
        }

        for (int i=0; i<intervals.length; i++){
            for (int j=0; j<intervals.length; j++){
                if (intervals[i][0]<intervals[j][0] && intervals[i][1]>intervals[j][1]){
                    adj[i].add(j);
                }
            }
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<n; i++){
            if (!visited[i]) {
                topologicalSorting(i, adj, visited, stack);
            }
        }

        int[] dist = new int[n];
        int maxDistance = 0;
        while (!stack.isEmpty()){
            int top = stack.pop();
            for (int v : adj[top]) {
                int newDistance = dist[top]+1;
                dist[v] = Math.max(newDistance, dist[v]);
                maxDistance = Math.max(maxDistance, dist[v]);
            }
        }
        return maxDistance+1;
    }

    private static void topologicalSorting(int u,  List<Integer>[] adj, boolean[] visited, Stack<Integer> stack){
        visited[u] = true;

        for (int v: adj[u]){
            if (!visited[v]){
                topologicalSorting(v, adj, visited, stack);
            }
        }
        stack.push(u);
    }




    /*
     Later elements wants to contain previous element. It should has both greater width and length
     */
    public static int russianDoll(int[][] envelops) {
        //descending order by start position. So internal later in sequence has smalll value containing those internal
        // early in sequence.

        Arrays.sort(envelops,
                Comparator.comparingInt((int[] interval) -> interval[0]).thenComparingInt(interval -> interval[1]));

        //We need decide longest increasing sequence on end position.
        int n = envelops.length;
        int[] results = new int[n+1];
        results[0]= Integer.MIN_VALUE;
        results[1]= envelops[0][1];

        int lastLen = 1;

        for (int i=1; i<n; i++){
            int endValue = envelops[i][1];

            int foundEnd = Arrays.binarySearch(results, 0, lastLen+1, endValue);
            if (foundEnd>0){
                continue;
            }
            foundEnd = -foundEnd -1;
            results[foundEnd] = endValue;
            if (foundEnd == lastLen+1) {
                lastLen++;
            }
        }
        return lastLen;
    }

    public static final class TestCase {
        @org.junit.Test
        public void testLIS(){
            assertEquals(find(new int[]{10,9,2,5,3,7,101,18}), 4);
        }

        @org.junit.Test
        public void testInterval(){
            assertEquals(longestInternal(new int[][]{{3,4}, {1,9}, {2,8}, {4,5}, {11,12}}), 3);
            assertEquals(longestInternal(new int[][]{{1,10}, {3,4}, {2,9}, {3,8}, {4,5}, {11,12}}), 4);
        }

        @org.junit.Test
        public void testIntervalDAG(){
            assertEquals(longestInternalDAG(new int[][]{{3,4}, {1,9}, {2,8}, {4,5}, {11,12}}), 3);
            assertEquals(longestInternalDAG(new int[][]{{1,10}, {3,4}, {2,9}, {3,8}, {4,5}, {11,12}}), 4);
        }


        @org.junit.Test
        public void testRussianDoll(){
            assertEquals(3, russianDoll(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
        }
    }
}
