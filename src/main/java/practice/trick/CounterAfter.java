package practice.trick;

import com.google.common.collect.Streams;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
/*
 * You are given an integer array nums and you have to return a new counts array.
 *  The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
 */

/*
Trick: count and sum
 */
public class CounterAfter {

    //issues: can't handle duplicated value
    public static int[] bst(int[] input){
        int[] result = new int[input.length];
        int n = input.length;
        result[n-1] = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i= n-1; i>=0; i--){
            int curretValue = input[i];
            result[i]=map.headMap(curretValue, false).values().stream().mapToInt(v-> v).sum();
            map.merge(curretValue, 1, (oldv, v)-> oldv+v);
        }
        return result;
    }

    public static int[] bit(int[] input){
        int n= input.length;
        int[] tree = new int[n+1];
        ArrayList<Integer> indice = new ArrayList<>(n);
        for (int i=0; i<n; i++){
            indice.add(i);
        }
        int[] result = new int[input.length];
        Collections.sort(indice, (a, b)->Integer.compare(input[a], input[b]));
        for (int i=0; i<n; i++){
            int index = indice.get(i);
            int value = input[index];
            int treeIndex = index+1;
            int smallAfter = read(tree, n) - read(tree, treeIndex);
            result[index] = smallAfter;
            if (i!=n-1) {
                int nextIndex = indice.get(i+1);
                int nextValue = input[nextIndex];
                if (nextValue!=value){
                    // only populate index when when start a new value segment.
                    int prevalue=value;
                    int prei = i;
                    while (prevalue==value){
                        set(tree, index+1);
                        prei--;
                        if (prei<0){
                            break;
                        }
                        index = indice.get(prei);
                        prevalue = input[index];
                    }
                }
            }
        }
        return result;
    }




    static int read(int[] tree, int i){
        int sum = 0;

        while (i!=0){
            sum+=tree[i];
            int lastbit = i & -i;
            i -=lastbit;
        }
        return sum;
    }
    static void set(int[] tree, int i) {
        while (i<tree.length){
            tree[i]++;
            int lastbit = i &-i;
            i+=lastbit;
        }
    }


    public static class ValueAndIndex {
        int value;
        int index;
    }
    public static int[] sort(int[] input){
        int n = input.length;
        ValueAndIndex[] values = new ValueAndIndex[n];
        for (int i = 0; i<n; i++){
            ValueAndIndex value = new ValueAndIndex();
            value.index= i;
            value.value = input[i];
            values[i]=value;
        }


        int[] count =new int[n];

        ValueAndIndex[] inputValues = values;
        ValueAndIndex[] outputValues = new ValueAndIndex[n];
        ValueAndIndex max= new ValueAndIndex();
        max.value = Integer.MAX_VALUE;
        max.index = n;
        for (int len= 1; len<n; len*=2){
            for (int i=0; i<n; i+=len*2){
                int l=i;
                int lstart=i;
                int lOneOverEnd = i+len;
                if (lOneOverEnd>n){
                    lOneOverEnd = n;
                }

                int r = lOneOverEnd;
                int rstart = lOneOverEnd;
                int rOneOverEnd = rstart + len;
                if (rOneOverEnd >n){
                    rOneOverEnd=n;
                }

                /*
                 assumption: [l, lOneOverEnd), [r, rOneOverEnd) is already sorted
                 All index in left is smaller than right.
                 count in one segment is correct for this segments.
                 */
                int outputPosition = l + r-rstart;

                //merge sort logic.
                //compare ValueAndIndex at l and r.
                while (outputPosition<rOneOverEnd){
                    ValueAndIndex lValue = inputValues[l];
                    if (l>=lOneOverEnd){
                        lValue = max;
                    }
                    ValueAndIndex rValue =max;
                    if (r<rOneOverEnd){
                        rValue = inputValues[r];
                    }

                    if (lValue.value <=rValue.value) {
                        outputValues[outputPosition]=lValue;
                        //count doesn't change.
                        l++;

                         //r- rstart is the number of elements at right side which is less than lValue.value.
                        count[lValue.index] += r-rstart;

                        //for equal value, we move left side to output first so we never count equal elements are
                        // right side.
                    } else {
                        outputValues[outputPosition]=rValue;
                        r++;
                    }
                    outputPosition++;
                }
            }
            ValueAndIndex[] temp = inputValues;
            inputValues = outputValues;
            outputValues = temp;
        }
        return count;
    }



    public static final class TestCase {
        @Test
        public void testBST(){
            assertThat("[2, 1, 0]", equalTo(Arrays.toString(bst(new int[]{5,2,1}))));
            assertThat("[0, 0]", equalTo(Arrays.toString(bst(new int[]{5,5}))));
            assertThat("[2, 1, 1, 0]", equalTo(Arrays.toString(bst(new int[]{5,2,6,1}))));
            assertThat("[4, 2, 5, 0, 2, 1, 2, 0, 0]", equalTo(Arrays.toString(bst(new int[]{5,2,6,1,5,2,6,1,5}))));
        }

        @Test
        public void testBit(){
            assertThat("[2, 1, 0]", equalTo(Arrays.toString(bit(new int[]{5,2,1}))));
            assertThat("[0, 0]", equalTo(Arrays.toString(bit(new int[]{5,5}))));
            assertThat("[2, 1, 1, 0]", equalTo(Arrays.toString(bit(new int[]{5,2,6,1}))));
            assertThat("[4, 2, 5, 0, 2, 1, 2, 0, 0]", equalTo(Arrays.toString(bit(new int[]{5,2,6,1,5,2,6,1,5}))));
        }

        @Test
        public void testSort(){
            assertThat("[2, 1, 0]", equalTo(Arrays.toString(sort(new int[]{5,2,1}))));
            assertThat("[0, 0]", equalTo(Arrays.toString(sort(new int[]{5,5}))));
            assertThat("[2, 1, 1, 0]", equalTo(Arrays.toString(sort(new int[]{5,2,6,1}))));
            assertThat("[4, 2, 5, 0, 2, 1, 2, 0, 0]", equalTo(Arrays.toString(sort(new int[]{5,2,6,1,5,2,6,1,5}))));
        }

    }



}
