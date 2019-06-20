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
   
   **Think  backward**. What is the last element to keep?
   Recursion cache for i, j. Always keep i and j. Last element m must i<m<j. We can't burst i and j.
 
   Think backward is a very important technique. Extract offline Miminum, Job deadline/priority schedule use 
   backward thinking, too.
   
1. CounterAfter

    Tree Map solution: Use tree Map. Equal Key issues.
    
    BIT solution: Use BIT for cumulative counting. Special handling for equal numbers.
    
1. Duplicated number:

   Slow-Fast pointer solution. Once both slow find the same index, both slow will become synchronized.
   memorize the algorithm.
   
1. IsValidPreorderSerialization

   use iterator as in BasicCalculator

1. LongestIncreasingSequence

    Used in several screnario: nested box, Russia Envelope.
    
    Can also be solved using DAG

1. Maximum number

   process end element in a loop until reaching desired state. (Not a single step)

1. RemoveDuplicateLetter
   

1. RemoveInvalidParenthesis
    
    Brute Force is the only feasible solution during interview since one doesn't have enough time to think through
    all details.
    
1. SuperUglyNumber
   
   PriorityQueue problem. 

1. ThreeNumberSmaller:
   
   Tree Map
   
1. Attraction V.S VoiceMixer.
   
   A very difficult problem. Similar to VoiceMixer problem.  
   VoiceMixer problem involves interval. 
   +  It doesn't matter which speaker each interval belongs to . We need to sort them by points. A point is an interval
      start or end position. 
   + At each point, we need to take some action: add a new sound segment or remove sound segment. We are changing the 
     active set of sound segment. 
   + After action is taken, we need to make an optimal decision. We pick up the max sound from Heap. 
  
  Attraction: 
  + We also maintain an active set. It becomes active when an attraction is open. 