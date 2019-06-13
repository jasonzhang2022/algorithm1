## Disjoint set

Design to solve the group membership problem: whether a and b in a group or not. 
Then merge group
Concepts:
1. We need to maintain a group forest.  
   1. each tree is initialized with tree[i]=i
   1. if tree[i]=i, 
      1. **i** is the root of the tree, 
      1. **i** is the **ID** of the group.
   1. we need a i representing invalid group.
   1. union merges tree by rank to avoid deep tree.
2. We need to problem domain object. The object is mapped to a group in tree.
### Job_Sequence
+ The group is its deadline.
+ invalid group is 0 since no job deadline is 0.
+ The domain object is task
+ **Union with rank** is not used since the app logic defines merged parent
### Offline Minimum
+ The group is ExtractIndex
+ Invalid group is lastIndex since no number can be extracted to lastIndex
+ The domain object is the number to be extracted.
+ **Union with rank** is not used since the app logic defines merged parent

Typical application: 
    Offline LCA.
        Offline means the tree structure is fixed. 
    cyclic detection in undirected graph.
    MST: minimal spanning tree
    
 
    