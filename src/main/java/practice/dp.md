#Knapsack problem
N items, Capacity = w.
##. 0/1 knapsack problem
1. Create M=(N+1)*(W+1)
1. Go row by row.
   1. Each row introduces a new item. 
   1. At each weight, we have two options:
        1. include this item
        1. exclude this item.
## Unbounded Knapsack problem
1. create array Max[w+1]
   1. M[i] is the max value for weight i.
   1. All max values for weight before i are calculated
2. move to weight  i+1.
   1. Check which items can produce new max value basing on Max value for index<=i;
## Fractional Knapsack
   1. greedy or Quick Partition approach.
