package practice.combination_permutation;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class Combination {
    public static void combination(int n, int k, Consumer<int[]> consumer){
        int[] results=new int[k];


        helper(n, k, results, 0, 0, consumer);

    }

    private static void helper(int n, int k, int[] results, int resultOffsetToBeFilled, int nOffsetToStart, Consumer<int[]> consumer) {
        if (k==0){
            consumer.accept(results);
            return;
        }
        if (k<0 || nOffsetToStart>=n){
            return;
        }

        helper(n, k, results, resultOffsetToBeFilled, nOffsetToStart+1, consumer);
        results[resultOffsetToBeFilled]=nOffsetToStart;
        helper(n, k-1, results, resultOffsetToBeFilled+1, nOffsetToStart+1, consumer);
    }


    public static final class Test {

        @org.junit.Test
        public void test(){
            String input="ABCDEFGH";
            int n=input.length();
            int k=3;

            int expectedCombination=n*(n-1)*(n-2)/3/2;
            int[] c= new int[1];
            c[0]=0;

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c[0]++;
            };

            Combination.combination(n, k, consumer);
            assertEquals(expectedCombination, c[0]);
        }
    }
}
