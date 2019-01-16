package practice.trick;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/*
  Trick : count. Similar questions. Top K frequent elements.  Integer-based counting

  Trick: partition3Way. Separate elements into two groups.

  Can also use binary search. Search a number between 1 and maximal citation.

 */

//https://leetcode.com/problems/h-index/
/*
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h 
of his/her N papers have at least h citations each, and 
the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers 
in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 */
public class Hindex {
    public static int hIndex(int[] citations) {
        
        int max = Arrays.stream(citations).max().getAsInt();
        int counts[] = new int[max+1];
        for (int citation : citations) {
            counts[citation]++;
        }
        
        int totalCount = 0;
        for (int j = max; j>=0; j--) {
            totalCount+=counts[j];
            if (totalCount>=j){
                return j;
            }
        }
        return -1;
    }

    public static int hIndexPartition2Way(int[] citations) {
        return Math.max(partition2Way(citations, 0, citations.length -1), 0);
    }
    static int partition2Way(int[] citations, int start, int end) {
        if (end==start) {
            int rightCount = citations.length - start;
            int citation = citations[start];
            int hindex = citation;
            while (rightCount<hindex){
                hindex--;
            }
            return hindex;
        }
        int i= start; //beginning index for pivotal segments.
        int j = end;  //running index
        int pivotalIndex = start + (end-start)/2;
        int pivotalValue = citations[pivotalIndex];

        int endValue = citations[end];
        citations[end]= pivotalValue;
        citations[pivotalIndex]=endValue;
        j--;

        int rightMin = Integer.MAX_VALUE;
        int leftMax = Integer.MIN_VALUE;
        int pivotalCount = 1;
        while (i<j) {
            if (citations[i]>=pivotalValue){
                if (citations[i]==pivotalValue){
                    pivotalCount++;
                } else {
                    rightMin = Math.min(rightMin, citations[i]);
                }
                endValue = citations[j];
                citations[j]= citations[i];
                citations[i]= endValue;
                j--;
            } else {
                leftMax = Math.max(leftMax, citations[i]);
                i++;
            }
        }

        // i is the first index if right segments.
        if (citations[i]<pivotalValue){
            leftMax = Math.max(leftMax, citations[i]);
          i++;
        } else if (citations[i]== pivotalValue){
            pivotalCount++;
        } else {
            rightMin = Math.min(rightMin, citations[i]);
        }

        //if left side is empty, leftMax is Integer.MIN_VALUE;
        if (citations.length - i == pivotalValue){
            return pivotalValue;
        } else if ( citations.length -i <pivotalValue) {
            int count = citations.length -i;
           if (count+1==leftMax){
               return leftMax;
           }

            if (count> leftMax){
                //  leftMax<=hIndex <pivotalValue
                int hIndex = pivotalValue-1;
                while (count<hIndex){
                    hIndex --;
                }
                return hIndex;
            } else {
                //if left side is empty, that is i==start
                // leftMax is Integer.MIN_VALUE.
                // so the above if condition works.
                return partition2Way(citations, start, i-1);
            }
        } else  {

            int count = citations.length - i - pivotalCount;
            if (count<=rightMin){
              //if right segment is empty, it is handled here.
                int hIndex = pivotalValue;
                while (count>hIndex){
                    hIndex++;
                }
                return hIndex;
            } else {
                return partition2Way(citations, i, end);
            }
        }


    }


    public static final class TestCase  {

        @Test
        public void test() {
            assertEquals(0, hIndex(new int[] { 0 }));

            assertEquals(1, hIndex(new int[] { 1 }));
            assertEquals(1, hIndex(new int[] { 1, 1 }));
            assertEquals(0, hIndex(new int[] { 0, 0 }));
            assertEquals(1, hIndex(new int[] { 0, 0, 1, 1 }));

            assertEquals(0, hIndex(new int[] { 0, 0 }));
            assertEquals(2, hIndex(new int[] { 7, 7 }));
            assertEquals(2, hIndex(new int[] { 2, 2 }));

            for (int i = 0; i < 20; i++) {
                assertEquals(7, hIndex(new int[] { 1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 8 }));
            }
            assertEquals(3, hIndex(new int[] { 3, 0, 6, 1, 5 }));
            assertEquals(0, hIndex(new int[] { 0, 0 }));
            assertEquals(5, hIndex(new int[] { 0, 1, 2, 6, 6, 7, 8, 9 }));
        }

        @Test
        public void testPartition2() {
            assertEquals(5, hIndexPartition2Way(new int[] { 0, 1, 2, 6, 6, 7, 8, 9 }));
            assertEquals(3, hIndexPartition2Way(new int[] { 3, 0, 6, 1, 5 }));
            assertEquals(1, hIndexPartition2Way(new int[] { 1, 1 }));
            assertEquals(0, hIndexPartition2Way(new int[] { 0 }));

            assertEquals(1, hIndexPartition2Way(new int[] { 1 }));

            assertEquals(0, hIndexPartition2Way(new int[] { 0, 0 }));
            assertEquals(1, hIndexPartition2Way(new int[] { 0, 0, 1, 1 }));

            assertEquals(0, hIndexPartition2Way(new int[] { 0, 0 }));
            assertEquals(2, hIndexPartition2Way(new int[] { 7, 7 }));
            assertEquals(2, hIndexPartition2Way(new int[] { 2, 2 }));

            for (int i = 0; i < 20; i++) {
                assertEquals(7, hIndexPartition2Way(new int[] { 1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 8 }));
            }
            assertEquals(0, hIndexPartition2Way(new int[] { 0, 0 }));

        }
    }


}
