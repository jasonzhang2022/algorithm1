package practice.combination_permutation;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class CombinationWithRepeat {
    public static void combination(int n, int k, Consumer<int[]> consumer){
        int[] results=new int[k];
        helper(n, k, results, 0, 0, consumer);

    }

    private static void helper(int n, int k, int[] results, int resultOffsetToBeFilled, int nOffsetToStart, Consumer<int[]> consumer) {
        if (k == 0) {
            consumer.accept(results);
            return;
        }
        if (k<0 || nOffsetToStart>=n){
            return;
        }
        // repeat 0 times
        helper(n, k, results, resultOffsetToBeFilled, nOffsetToStart + 1, consumer);
        // repeat 1 to k time.
        for (int i = 0; i < k; i++) {
            results[resultOffsetToBeFilled + i] = nOffsetToStart;
            helper(n, k - i - 1, results, resultOffsetToBeFilled + i + 1, nOffsetToStart + 1, consumer);
        }
    }

    public static class Count {
        int count;
    }
    public static final class Test {
        @org.junit.Test
        public void TestCombination(){
            String input="ABCDEFGH";
            int n=input.length();
            int k=1;

            //formua=C(n+k-1, k)
            int expectedCombination=8;
            Count c=new Count();

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c.count++;
            };

            CombinationWithRepeat.combination(n, k, consumer);
            assertEquals(expectedCombination, c.count);
        }

        @org.junit.Test
        public void TestCombinationTwo(){
            String input="ABCDEFGH";
            int n=input.length();
            int k=2;

            //formua=C(n+k-1, k)
            int expectedCombination=9*8/2;
            Count c=new Count();

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c.count++;
            };

            CombinationWithRepeat.combination(n, k, consumer);
            assertEquals(expectedCombination, c.count);
        }

        @org.junit.Test
        public void TestCombinationThree(){
            String input="ABCDEFGH";
            int n=input.length();
            int k=3;

            //formua=C(n+k-1, k)
            int expectedCombination=10*9*8/2/3;
            Count c=new Count();

            Consumer<int[]> consumer= a->{
                for (int index: a){
                    System.out.print(input.charAt(index));
                }
                System.out.print("\n");
                c.count++;
            };

            CombinationWithRepeat.combination(n, k, consumer);
            assertEquals(expectedCombination, c.count);
        }
    }
}
