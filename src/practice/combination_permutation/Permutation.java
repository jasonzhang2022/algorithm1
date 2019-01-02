package practice.combination_permutation;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class Permutation {
    public static void permute(int[] ints, Consumer<int[]> consumer){
        helper(ints, 0, consumer);
    }

    public static void helper(int[] ints, int offset, Consumer<int[]> consumer) {
        if (offset == ints.length){
            consumer.accept(ints);
            if (Arrays.equals(ints, new int[]{0,2,2,3,1,1})){
                System.out.println("found");
            }
            return;
        }

        // which element is first
        int firstIndex = offset;
        BitSet bits = new BitSet(ints.length);

        while (firstIndex<ints.length){
            int firstElement= ints[firstIndex];
            ints[firstIndex]= ints[offset];
            ints[offset] = firstElement;
            bits.set(firstElement);

            helper(ints, offset+1, consumer);

            ints[offset]=ints[firstIndex];
            ints[firstIndex] = firstElement;
            firstIndex++;
            while (firstIndex<ints.length && bits.get(ints[firstIndex])){
                firstIndex++;
            }
        }

    }
    public static class Count {
        int count;
    }
    public static final class Test {
        /**
         * All elements in input are unique
         */
        @org.junit.Test
        public void TestPermutationN(){
            String input="ABCD";
            int expectedPermutation=4*3*2*1;
            Count c=new Count();

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c.count++;
            };
            Permutation.permute(new int[]{0, 1,2,3}, consumer);

            assertEquals(c.count, expectedPermutation);
        }

        @org.junit.Test
        public void TestPermutationN1(){
            String input="ABCDE";
            int expectedPermutation=5*4*3*2*1;
            Count c=new Count();

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c.count++;
            };
            Permutation.permute(new int[]{0, 1,2,3,4}, consumer);

            assertEquals(c.count, expectedPermutation);
        }

        /**
         * Some element are duplicated.
         * Here ABCDBC is a multiset. B  and C appear twice each time.
         */
        @org.junit.Test
        public void TestPermutationNDuplicate(){
            //this is a multiset
            String input="ABCD";
            int expectedPermutation=6*5*4*3*2*1/2/2;
            Count c=new Count();

            Set<String> strings = new HashSet<>();
            Consumer<int[]> consumer= a->{
                StringBuilder sb = new StringBuilder();
                for (int index: a){
                    System.out.print(input.charAt(index));
                    sb.append(input.charAt(index));
                }

                System.out.print("\n");
                if (strings.contains(sb.toString())){
                    System.out.println("-----duplicated: "+sb.toString());
                }
                strings.add(sb.toString());
                c.count++;
            };
            int[] ints = new int[]{0, 1,2,3, 1,2};
            Arrays.sort(ints);
            Permutation.permute(ints, consumer);
            //Permutation.permuteAvoidDuplicate(new int[]{0, 1,2,3, 1,2}, consumer);

            assertEquals(c.count, expectedPermutation);
        }
    }
}
