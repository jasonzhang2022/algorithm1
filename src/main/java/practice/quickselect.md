## Trikcy point with Quick Select

+ FractionalKnapsack
+ Hindex


One segment could have all values larger than pivotal value or less than pivotal value.

+ If we leave pivotal value at right segment, if all valuees >= pivotal value, the left segment
length is zero
+ If we leave pivotal value at left side, if all values <= pivotal value, the right segment 
length is zero

There are two solutions.
1. Tree way partitions: If there is many duplicated elements in the array. So we devide each 
segments into 3 segments: left, middle, right.  
   1. For this, middle segment always present, left or right side could be empty. So this approach is not
   very useful to handle the empty case.
2. Pick up pivotal value as one segment. The other segment could contain pivotal value, too.
Always think about pivotal value is at extreme side.
   1. if one segment is empty and we want to go to that segment, most likely, we reach a end case.
   2. We can swap pivotalValue to empty segment so both segment has some values.


