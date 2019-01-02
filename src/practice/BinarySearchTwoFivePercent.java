package practice;

/**
 * Give a sorted array of integer. Find element that occur more than 25% of element length.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Thoughts:
 * This array is sorted.
 * Suppose there is one element which occurs more than n/4 times.
 * All copies of this element stay together on one continuous stretch.
 * If such element occurs, it must in the n/4, n/4*2, n/4*3 indices
 *
 * Suppose n=100 which is 4 multiple,
 * 25% is n/4=25 percent. Element must occur at least n/4+1 times.
 * suppose n=99 if this element must occur n/4 times.
 */

/**
 * Why I am slow here
 * 1. index and length calculation.
 *  Give an offset, you want to an segment of length n. What is the last index of this segment.
 *   If offset(0) is included, the last index is n+offset-1: normal array with offset=10
 *   If offset is not included, the last index is n+offset: normal array with offset = -1;
 * 2, Binary search : see summary below in code
 * 3. Slide window
 */

public class BinarySearchTwoFivePercent {

    public int input[];

    BinarySearchTwoFivePercent(int input[]){
        this.input = input;
    }
    public  List<Integer> find() {
        List<Integer> founds = new ArrayList<>(3);

        int minimalLength = input.length/4+1;
        int previousIndex = -1;
        int nextIndex = 0;
        while ( (nextIndex = previousIndex+minimalLength) < input.length) {
            int value = input[nextIndex];
            int start = findStart(previousIndex+1, nextIndex, value);
            int end = findEnd(nextIndex, input.length-1, value);
            if (end-start+1>=minimalLength){
                founds.add(value);
                previousIndex = end;
            } else{
                previousIndex = nextIndex;
            }
        }
        return founds;
    }

    /**
     *
     * Summary for Binary search
     *
     * 1. Special handle for end=start and end-start =1;
     * For this two case, middle = start.
     * If middle is converted to start at next loop,
     * we have an infinite loop.
     *
     * 2. always include middle in next loop.
     * not middle -1 or middle +1
     *
     * 3. if end -start >1. middle != start, middle != end.
     */
    int findStart(int start, int end, int value){
     if (start==end){
         return start;
     }
     if (end-start ==1){
         if (input[start]==value){
             return start;
         }
         return end;
     }
     int middle = (start+end)/2;
     if (input[middle]==value){
         return findStart(start, middle, value);
     } else {
         return findStart(middle, end, value);
     }
    }


    int findStartIteraive(int start, int end, int value) {
         while (true) {
             if (start ==end){
                 return end;
             }
             if (end-start ==1) {
                 if (input[start]== value){
                     return start;
                 }
                 return end;
             }
             int middle = (start+end)/2;
             if (input[middle]==value){
                 end = middle;
             } else {
                 start = middle;
             }
         }
    }

    int findEnd(int start, int end, int value){
        if (start == end){
            return end;
        }
        if (end-start == 1){
            if (input[end]== value){
                return end;
            }
            return start;
        }
        int middle = (start+end)/2;
        if (input[middle]==value){
            return findEnd(middle, end, value);
        } else {
            return findEnd(start, middle, value);
        }
    }

    public static void main(String[] args) {
        int[] inputs;
        List<Integer> founds;

        inputs= new int[]{};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);

        inputs= new int[]{1};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);

        inputs= new int[]{1,2,3};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);

        inputs= new int[]{1,2,3,4};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);

        inputs= new int[]{1, 1,1, 2, 3,4,4};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);


        inputs= new int[]{1, 2,3,4,5};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);

        inputs= new int[]{1, 2,3,4,5,5,5,8,9};
        founds = new BinarySearchTwoFivePercent(inputs).find();
        System.out.print(Arrays.toString(inputs));
        System.out.print(",");
        System.out.println(founds);
    }



}


