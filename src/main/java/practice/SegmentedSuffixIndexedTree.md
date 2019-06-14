##Segmented Tree

### Problem. 
Suppose you have a fixed length array. You change element value from time time. You repeated query some 
property for an interval, E.g: sum, average, min, max, etc.

Here are 3 properties: 
1. fixed length.
1. Change value
1. At the same time, you want to query property dynamically. 

The property 2 and 3 hints a Tree data structure. 
(1. property 1 suggests an array-backed tree structure like heap/segmented tree).

### Idea
We have a tree. Each node have the property for an interval(i, j)
Its left node contains the property for the left half the interval(i, i+j/2).
Its rght now contains the property for the right half of the interval(j/2+1, j).
(**Don't debate whether j is even or oddd. We just need a  rule which reliably devide the 
interval into two**)


Any update will be  O(logN). 
Query will be O(logN).

#### Naive solution.
Suppose we don't have any data structure. Update will be O(1). Query will be O(n).

### Segmented Tree data structure. 

Property 1 in problem section hints an array-based tree structure like heap.

The tree is formulated like this: for a node at index i.
+ the parent node is i/2;
+ the left child node is 2*i
+ the right child node is 2*i+1

Tree[i] contains the property. 

### Tree[i] value.

Tree[i] contains the interval property: sum, average, min, max, etc. 
**We don't know what interval it is for.**  The interval it is for is fixed since the original array 
has fixed length. 

We of course can have tree[i] containing an object(tuple). The object has one interval property. However, 
this is not necessary. All tree traverse starts from root. Root is for interval [0, n-1]. From idea section, 
we know interval for children node once we know the interval for one node. 


### Processing context
We can pass down the interval when we process one node recursively. I call the the interval 
**Processing Context**. It can be passed down as recursive function arguments. It can also be maintained as
 map, or matrix. **It can also be pushed to stack.  We only need to maintain stack 
 when we do iterative traversal.**
 



## Tree size.
For a tree at level(index) h, we can have **2^h nodes**. All level about it has **2^h-1** nodes.
For example, first level (level = 0), we have 2^0=1. We have 2^1 for second level. 

For segmented tree, we know leaf nodes contains the value of each element in original array.  
Since we have n elements,  the tree height will be Math.ceil(log(n))
So we have 
  tree depth d= Math.ceil(log(n));
  number of nodes at last level: 2^d.
  number of nodes above last level: 2^d-1;
  
We don't use tree[0] since 2*0=0. It has issue with its children node.

  This will be the tree size.

    
## Binary Indexed Tree

Binary indexed tree solves one special case for Segmented Tree: The sum for interval.
It has the same property with segmented tree
1. fixed array length.
2. array element value can be changed. 
3. Many repeated query. 

However, its meta data structure is much simple than Segmented Tree. 
1. It only keeps the meta data (sum). Original value is computed.
2. Its code is very simple. 

### Idea

Suppose original array has size n, tree size will be n+1 (node 0 is not used).

node index i can be viewed as many bits:i = 2^k+2^m+2^n.
+ tree[2^n] contains the sum for data[0:2^n-1];
+ tree[2^m] contains the sum for data[0:2^m-1];
+ tree[2^k] contains the sum for data[0:2^k-1];
+ for i, it falls into two segments: 
  + tree[2^k] covers the sum data[0:2^k-1];
  + child tree that covers 2^k+1 to 2^(k+1):
    + i only occupies part of this tree. We need to navigate the child tree. 

### Original Data
tree[idx] only keep meta data. Original data can be computed. 
Of course, you can have a data array. This makes reading data value a constant operation.


### index Zero.
like segmented tree, tree[0] is not used.


##Suffix Tree
It is special Trie structure. It is designed to solve some special String problem. 

+ Quick judge whether a substring exists in a string or not
+ Longest Common Subsequence among two strings
+ Longest repeated substring
+ longest palindromic sequence

Web page autocompletion can be solved using suffix tree.

It is special data structure designed for string pattern propblem.

 


