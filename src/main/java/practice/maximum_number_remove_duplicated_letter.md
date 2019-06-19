# Problem
1.  Maximum number. A list of digits, create a biggest number with k digits
1.  [remove duplicate letter](https://leetcode.com/problems/remove-duplicate-letters/). 

# Similarity

+ Scan the list from left to right, make an optimal local decision. Global optimal decision is made when end is reached. 
+ **Maximum number** 
  + The global optimal decision. Try best to have the maximal first digit, then maximal second digit. 
  + optimization: when a digit is see, pop any smaller digit before it since the bigger one always give better solution.
  + constraint: the result length on stack and the length of remaining characters must be >=k. 
+ **Remove Duplicate letter**
  + **stack approach**: similar to maximum number
     + when process a character, pop bigger character(chronologically behind) before this one.
     + constraint: the popped character must still exist in the input after current index.
     + There is **serious flaw**. The whole "pop" approach will assume the current character will be present in final
      sequence and replace the popped one. but for this problem, the current one may not be pushed to stack since
      there is already one in stack. This breaks the assumption.
  + **disjoint approach**: 
     + when see a character X, try to find the last X occurrence before current one. Let represent last X as X'. So 
     the segment looks like this X'..A..C..X. "." represented deleted character. A, C, X are arbitrary characters.
     Here we need to make a decision whether to delete X or X'.
     +  Keep X', delete X: X'AC
     +  Delete X', keep X: ACX. 
     We just need to compare X' and A, and make a decision.
 
 # Solution Summary
 + process element one by one , and make one local optimization at each step.
 