package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class QuickSelect_FractionalKnapsack {
    public static class Item {
        int profit;
        int weight;
        double ratio;

        double getRatio(){
            return ratio;
        }
        public Item(int profit, int weight) {
            super();
            this.profit = profit;
            this.weight = weight;
            this.ratio=((double)profit)/((double)weight);
        }
    }

    public static double maximumProfit(QuickSelect_FractionalKnapsack.Item[] items, int capacity){
        partition(items, capacity, 0, items.length -1);
        double p = profit;
        profit = 0;
        return p;
    }

    static double profit = 0;
    public static void partition(QuickSelect_FractionalKnapsack.Item[] items, int capacity, int start, int end){
        if (end<=start){
            if (capacity > items[start].weight){
                profit = items[start].profit;
            } else {
                profit += capacity/Integer.valueOf(items[start].weight).doubleValue()*Integer.valueOf(items[start].profit).doubleValue();
            }
            return;
        }

        int s =start;
        int e = end;
        int leftCapacity = 0;
        int leftProfit = 0;
        QuickSelect_FractionalKnapsack.Item pivotal = items[e];
        e--;
        while (e>=s){
            if (items[s].ratio<= pivotal.ratio) {
                QuickSelect_FractionalKnapsack.Item temp = items[s];
                items[s]=items[e];
                items[e] = temp;
                e--;
            } else {
                leftCapacity += items[s].weight;
                leftProfit += items[s].profit;
                s++;

            }
        }

        if (leftCapacity==capacity ){
            profit+=leftProfit;
            return;
        }
        if (leftCapacity <capacity){
            profit+=leftProfit;
            // s can be start. This will form infinite loop.
            // inifite loop case: all ratio is equal.
            //we can sort and
            if (s==start) {
                linear(items, capacity-leftCapacity, s, end);
            } else {
                partition(items, capacity - leftCapacity, s, end);
            }
        } else {

            // this definitely reduce the segment
            partition(items, capacity, start, s-1);
        }
    }


    public static void linear(QuickSelect_FractionalKnapsack.Item[] items, int capacity, int start, int end){
        QuickSelect_FractionalKnapsack.Item[] sections = Arrays.copyOfRange(items, start, end+1);
        Arrays.sort(sections, Comparator.comparing(QuickSelect_FractionalKnapsack.Item::getRatio).reversed());

        int leftCapacity =0;
        for (QuickSelect_FractionalKnapsack.Item item: sections){
            if (leftCapacity+item.weight < capacity) {
                profit+=item.profit;
                leftCapacity+=item.weight;
            } else {
                profit += Integer.valueOf((capacity-leftCapacity)).doubleValue() * Integer.valueOf(item.profit).doubleValue()
                        /Integer.valueOf(item.weight).doubleValue();
                break;
            }
        }

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
            assertEquals(String.format("%.2f", profit1), "9.50");
        }

        @org.junit.Test
        public void testAllEqual(){
            Item[] items=new Item[4];
            items[0]=new Item(3,3); //ratio 1
            items[1]=new Item(3,3); //ratio 0.5
            items[2]=new Item(3,3); //ratio 2
            items[3]=new Item(3,3); //ratio 2


            double profit1=maximumProfit(items, 5);
            assertEquals(String.format("%.2f", profit1), "5.00");
            profit1=maximumProfit(items, 6);
            assertEquals(String.format("%.2f", profit1), "6.00");
        }
    }
}
