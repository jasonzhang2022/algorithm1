#review
+ Combination **without** repetition can be viewed as a special case of combination with repetition.
  + Each element can appear **0 to k** time for k combination of n elements for 
  combination **with** repetition
  + Each element can appear **0 to 1** time for k combination of n elements for 
  combination **without** repetition

##permutation with reuse. 
+ at each iteration, we decide whether an element is used as first element or not.

We can't assume the input elements are sorted. We need to use a **set** to judge whether a value is used as
first element or not. Even the input element is sorted,  it could be in unsorted order during swap.

#permutation of multisets.
We have n elements. The output sequence will have n elements, too. 
The multiplicity of each element from input will equal to that in output.

#print Parenthesesis {}
compare this with  **permutation** with reuse without any restriction


