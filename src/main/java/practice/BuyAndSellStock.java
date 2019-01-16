package practice;

import static org.junit.Assert.assertEquals;

public class BuyAndSellStock {

    //https://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-stock-iii-java/
    /*
     * DP: although we want to tx2Profit,
     * we maintain other intermediate property(buy1, tx1Profit, buy2 we can be used to derive tx2Profit.
     */
    public static int profitTwoTransactions(int[] prices){

        int buy1=prices[0];
        int tx1Profit = 0;
        int buy2=prices[2];
        int tx2Profit =0;
        for (int i=1; i<prices.length; i++) {

            //replace second sell.
            int newTx2Profit = tx1Profit+prices[i]-buy2;
            if (newTx2Profit>tx2Profit) {
                tx2Profit = newTx2Profit;
            }

            if (prices[i]<buy2) {
                buy2 = prices[i];
            }
            int newTx1Profit = prices[i]-buy1;
            if (newTx1Profit >tx1Profit) {
                tx1Profit = newTx1Profit;
            }
            if (prices[i]<buy1) {
                buy1=prices[i];
            }
        }

        return Math.max(tx1Profit, tx2Profit);
    }


    public static class Test {
        @org.junit.Test
        public void test(){
            int[] input={1, 4, 5, 7, 6, 3, 2, 9};
            assertEquals(13, profitTwoTransactions(input));
        }

    }
}
