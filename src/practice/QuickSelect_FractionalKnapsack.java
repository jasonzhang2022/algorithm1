package practice;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class QuickSelect_FractionalKnapsack {
    public static class Item {
        int profit;
        int weight;
        double ratio;
        public Item(int profit, int weight) {
            super();
            this.profit = profit;
            this.weight = weight;
            this.ratio=((double)profit)/((double)weight);
        }
    }



    public static double maximumProfit(Item[] items, int capacity){

        int divideIndex = partition(items, 0, items.length-1, capacity);
        double profit =0;
        int weightSoFar=0;
        for (int i=0; i<divideIndex; i++) {
            profit+=items[i].profit;
            weightSoFar += items[i].weight;
        }
        if (capacity - weightSoFar >= items[divideIndex].weight){
            return profit + items[divideIndex].profit;
        } else {
            double remaingProfit = items[divideIndex].ratio * (capacity-weightSoFar) / items[divideIndex].weight;
            return profit + remaingProfit;
        }
    }

    public static int partition(Item[] items, int start, int end, int desiredWeight) {
        if (start==end) {
            return start;
        }
        int pivotalIndex = start + new Random().nextInt(end-start);
        Item pivotalItem = items[pivotalIndex];

        Item temp = items[end];
        items[end]=pivotalItem;
        items[pivotalIndex] = temp;

        int m=start;
        int n=end-1;
        while (m<n) {
            if (items[m].ratio >pivotalItem.ratio){
                m++;
            } else {
                temp = items[n];
                items[n] = items[m];
                items[m] = temp;
                n--;
            }
        }

        //m == n right now.
        if (items[m].ratio <=pivotalItem.ratio){
            m--;
        }
        //every element in this segments is  <= pivotal elements

        if (m<start) {
            m=start;
            items[end] = items[start];
            items[start] = pivotalItem;
        }

        int leftWeight = 0;
        for (int i =start; i<=m; i++){
            leftWeight += items[i].weight;
        }
        if (leftWeight == desiredWeight) {
            return m;
        }

        if (leftWeight>desiredWeight) {
            return partition(items, start, m, desiredWeight);
        }

        return partition(items, m+1, end, desiredWeight-leftWeight);
    }


    public static class Test {

        @org.junit.Test
        public void testCanHaveAllItems(){
            Item[] items=new Item[1];
            items[0]=new Item(3,3);
            int targetWeight=5;
            double profit1=maximumProfit(items, targetWeight);
            assertEquals(String.format("%.2f", profit1), "3.00");
        }

        @org.junit.Test
        public void testCanHaveAllItems1(){
            Item[] items=new Item[2];
            items[0]=new Item(3,3);
            items[1]=new Item(3,2);
            int targetWeight=5;
            double profit1=maximumProfit(items, targetWeight);

            assertEquals(String.format("%.2f", profit1), "6.00");
        }

        @org.junit.Test
        public void test(){
            Item[] items=new Item[3];
            items[0]=new Item(3,3); //ratio 1
            items[1]=new Item(2,4); //ratio 0.5
            items[2]=new Item(6,2); //ratio 2

            int targetWeight=5;

            double profit1=maximumProfit(items, targetWeight);
            assertEquals(String.format("%.2f", profit1), "9.00");
            profit1=maximumProfit(items, 6);
            assertEquals(String.format("%.2f", profit1), "9.13");
        }
    }
}
