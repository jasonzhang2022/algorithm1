package practice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class Note {

    //priority queue can have equals priority
    static void priorityQeue(){

        PriorityQueue<Integer> nums = new PriorityQueue<Integer>();
        nums.offer(5);
        nums.offer(5);
        System.out.println(nums.size());
        nums.poll();
        System.out.println(nums.size());
    }

    // binary search: position = -findPoint -1;
    // Both Arrays.binarySearch, Collections.binarySearch
    static void bs(){
        int[] nums = {1,1,2,2,3,3,4,4,5,5,5,6,6,6};
        double[] ds  = Arrays.stream(nums).mapToDouble(i->Integer.valueOf(i).doubleValue()).toArray();

        int start = -Arrays.binarySearch(ds, 2.9)-1;
        int end = -Arrays.binarySearch(ds, 3.1)-1;
        System.out.printf("Found position %d, %d %d\n", start, end, end-start);
    }

    public static void main(String args[]){
        grid();

    }

    public static void testFindStartIndex(){
        int[] nums = {1,1,2,2,3,3,4,4,5,5,5,6,6,6};
        int start2=findStartIndex(2, nums);
        int start1 = findStartIndex(1, nums);
        System.out.printf("Found position %d, %d\n",start1, start2);
    }

    // How to manipulate start, end, middle.
    public static int findStartIndex(int value, int[] input){

        int start = 0;
        int end = input.length-1;

        //find the first index which starts value;
        while (end-start>1){
            int middle = start + (end-start)/2;
            if (input[middle]>=value){
                end=middle;
            } else{
                start = middle;
            }
        }
        if (input[start]==value){
            return start;
        }
        return end;
    }


    public static void grid() {
        //multiple-dimension array is auto initialized
        int[][] grid = new int[5][5];
        System.out.println(grid[3][3]);
    }
}
