Java BinarySearchTree implementation is TreeMap.

## SortedMap interface
+ keySet() is in search order
+ firstKey, lastKey
+ headMap, subMap, tailMap: **This can be used to remove part of the map**

## Navigable Map
+ descendingKeySet()
+ ceilingKey, higherKey. **higherKey** is used to find successor.
+ floorKey, lowerKey. **lowerKey** is used to find predecessor.
+ augmented headMap, subMap, tail with option to specify whether each index is inclusive or not.

## Some Map


## BST property:
+ predecessor
  1. right most child in left subtree. 
  2. first ancestor who has a right ancestor for this node.
+ successor
  1. left most child in right subtree.
  2. first ancessor who has a left ancestor for this node.
  
If TreeMap(NavigableMap) is used, NavigableMap provides LowerXXX/HigherXXX, ceilingXXX/floorXXX methods.
 
### Predecessor and Successor properties
+ KClosest: Use a global map to keep parent. Easy and clean.
+ KClosestUsingStack: Use a stack to keep parent. Have NAZI shape algorithm to find predecessor and successor
+ KClosestUsingStack: when traverse to find node, separate parent into predecessors and successors.

#### What do we learn from KClosestUsingStackPredecessorSuccessor
  For a particular node, its predecessors can be from ancestor and from its children. 
  When we traverse from root to the nodes, we separate its parent into two categories: 
  predecessors and successors.
  At a particular moment, when we try to find predecessor for top node in predecessors. 
  We only need to traverse down to the tree. All its parent predecessor already on the stack.
  
   


