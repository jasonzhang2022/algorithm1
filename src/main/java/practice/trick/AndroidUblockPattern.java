package practice.trick;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

//https://www.geeksforgeeks.org/number-of-ways-to-make-mobile-lock-pattern/

/**
 * Permutation with restriction.
 * When select next element for first position, that element may be invalid.
 * In that case, it is skipped.
 *
 * This is a typical permutation interview question.
 */
public class AndroidUblockPattern {


    int count=0;

    int[] nums = new int[]{0,1,2,3,4,5,6,7,8};
    BitSet set = new BitSet();

    int min = 1;
    int max = 2;
    public int permute(int min, int max){
        this.min = min;
        this.max = max;

        for (int i=0; i<nums.length; i++){
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i]=temp;
            set.set(nums[0]);
            permuteHelper(nums, 1, nums[0]);
            set.clear(nums[0]);
            nums[i] = nums[0];
            nums[0] = temp;
        }
        return count;
    }


    public void permuteHelper(int[] nums, int offset, int prev){
        if (offset >=min) {
            count++;
        }

        if (offset == max){
            return;
        }
        for (int i=offset; i<nums.length; i++) {
            int next = nums[i];
            if (isValid(prev, next)) {
                nums[i] = nums[offset];
                nums[offset] = next;
                set.set(next);
                permuteHelper(nums, offset+1, next);
                set.clear(next);
                nums[offset]=nums[i];
                nums[i] = next;
            }
        }
    }

    public boolean isValid(int prev, int next) {

        int smaller = Math.min(prev, next);
        int bigger = Math.max(prev, next);
        if (smaller%3==0 && bigger==smaller+2 && !set.get(smaller+1)){
            return false;
        }
        if (smaller<=2 && bigger == smaller+6 && !set.get(smaller+3)){
            return false;
        }
        if ( ((smaller==0 && bigger==8) || (smaller==2 && bigger==6 )) && !set.get(4)){
            return false;
        }
        return true;
    }


    public static class Test {
        @org.junit.Test
        public void test() {
            AndroidUblockPattern pattern = new AndroidUblockPattern();
           assertEquals(pattern.permute(1,1), 9);

            pattern = new AndroidUblockPattern();
            assertEquals(pattern.permute(1,2), 65);

            pattern = new AndroidUblockPattern();
            assertEquals(pattern.permute(4,6), 34792);
        }
    }

}
