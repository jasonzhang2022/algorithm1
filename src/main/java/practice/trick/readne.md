1. AndroidUnlockPattern
   
   **permutation with restriction**. Usually a subset of permutation is valid. First we decide this is a permutation 
   problem  and have a basic permutation skeleton. Then we add the constraint in proper place.

1. Basic Calculator: +, - (, )
    
   If we don't use iterator, we need to pass down and up string index. This is cumbersome. **Iterator and recursion**.
   
   a **"+"** is added to string end to handle terminal conditions. 

1. Basic Calculator II: +, -, * /
   
   We didn't use iterator here. a shared index is used instead. We want to move the index forward/backward. 
   You can't do forward/backward with iterator. You can increase/decrease easily with index.
   
   There are two mutually called recursive functions.
   
1. BurstBallon
   
   Think  backward. What is the last element to keep?
   Recursion cache for i, j. Always keep i and j. Last element m must i<m<j. We can't burst i and j.
 