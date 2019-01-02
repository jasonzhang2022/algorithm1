##SCC

what is SCC? 
For **directed** graph, there is a path from any vertex to another vertex. SCC must has at least one cycle. Given 
two vertices: A, B. There is one path from A to B: A->B, there is also one path from B->B. This is from definition.
A->B->A. So we have one cycle.

## Algorithm
### [Kosaraju](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
The idea is
1. start from A, do DFS, we can reach B since there is A->B path.
2. reverse the edge. The original B->A path will become A-'->B path. 
 So if A to B is connected, A can still reach B by DFS when graph is reversed.
 
 During the process we keep a stack which populates the element like Topology sorting algorithm. 
 Which will remove (A->C)-x->B
 + A->C in both case. 
 + A can't reach B anymore.
 + So (A, C) is removed before B is touched.