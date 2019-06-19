# Java Collections
+ map: putIfAbsent, getOrDefault, computIfAbsent, ComputeIfPresent, compute, merge
+ sortedmap: firtKey, lastKey, subMap, headMap, tailMap, keySet
+ NavigavleMap: subMap, headMap, tailMap, descdendingKetSet, descendingMap, ceilingKey/floorKey, higherKey/lowerKey
+ LinkedHashMap
+ Arrays.binarySearch(), Collections.binarySearch, toIndex exclusively
+ Arrays.copyOfRange(), list.subList()
+ Arrays.sort(), Colections.sort() are in-place sorting


need think about test case
#Java Stream
+ filter, map, limit, skip, distinct
+    allMatch, anyMatch, noneMatch, count
+    sorted
+   toArray, findFirst, findAny, collect
+    empty, concat, peek, of
+  iterator

# BIT
+ tree[max_element+1]: tree[0] is not used so we need one element than underlying array
+ lastbit = n&-n;
+ stop traverse down when idx reach zero.
+ stop traverse up when idx is out of boundary.

#SegmentedTree
+ the parent node is i/2;
+ the left child node is 2*i
+ the right child node is 2*i+1
+ We don't use tree[0] since 2*0=0. This will map left child to node itself.

# BinarySearch 
Arrays.binarySearch or Collections.binarySearch
Use them clever to avoid to implement binarySearch by myself.
**end-start >1** for Binart Search

# QuickSelect, Partial ordering
+ choose end item as pivotal value during interview. Easy programming
+ in Each partition loop, 
  + the end condition is end<start.
  + Basically we are move Start forward, however, start may never move if all
    values in segment >= pivotal value. 
    In this case we have infinite loop. We need to fall back to linear approach.
+ Partition reaches base case when end<=start, In this case, we need to check elements 
  at start to make final decision where to split.

# Review
+ Articulation point in graph/connectivity/readme.md

# Graph
+ DFS: Maintain node property: color, parent, disc, min, 
  + DFS for undirect only has two colors. If it is black, it is definitely ancestors
+ BFS: use queue. Maintain auxillary information in a map structure
  + Use for all shortest computation with priority queue
+ Bipartitle Matching: assign one set to another set 
+ Use Edge or vertex only once: 
+ Topological sorting
+ Euler tour

## formularize graph problem
https://www.geeksforgeeks.org/given-array-strings-find-strings-can-chained-form-circle/
nested box

## pigeon counting
topk
hindex

## global minimal
burst ballon
offline minimum extraction
job sequence deadline


#Performance
+ O(NlogN): sorting
+ logN: binary search
+ N: partial sorting, quick select. Pigeon hole sorting. Count sorting.


#Flow
## Design:
 DB schema, COmponent, API, Tech stack, Scalability, Security(Auth, Access Control), Monitor, Audting,

## Code
  Test case
  check input, Check Exception
   

   