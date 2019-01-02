## Minimum Height Tree
+ How do we scan tree. We can scan from one node It is BFS. BFS use Queue
+ We can scan from multiple nodes (here we select leaf nodes)
+ Represent Tree as Graph. 

## Reachability
Let G = (V, E) be a directed graph in which each vertex u∈V is labeled with a unique
integer L(u) from the set {1, 2, …, |V|}. For each vertex u∈V, let R(u) = {v∈V| ∃ a path
from u to v in G}. Define min(u) to be the vertex in R(u) whose label is minimum, i.e.,
min(u) is the vertex v such that L(v) = min{L(w)|w∈R(u)}. Give an O(V+E)-time
algorithm that computes min(u) for all vertices u∈v. 

This question is very similar to minimum extract problem: practice.disjoint_set.OfflineMinimum.

It starts from the final result, and induce back to original path.  Graph transpose







