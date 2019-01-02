#Dijkstra’s shortest path algorithm
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

**Problem**: shortest path from source vertex S to all other vertices.

+ Have  a priority queue.
  + element is the minimal **accumulated** distance so far from source node to the element node in the Scanning Tree.
  + Element is compared by distance.
  + We hold references to the element so we can update its distance(remove and add again);
+ Add source node with distance zero to the queue.
+ Each time, a node is picked from queue. This node is moved to the final set.
+ From this node, calculate this distance to its vertices 
  + only for those vertices that is still in queue.
  + update the vertices' distance if the new accumulated distance is less than the distance in queue.
+ Stop is the queue is empty.

## Summary
+ Two sets of nodes: one is the final set which we have answer. The other is in priority queue. 
+ Scanning tree(not DFS). Not follow the DFS tree path. Not dynamically switch next node to be traversed 
  by picking up the node from Priority Queue.
  + DFS is recursive.
  + Scanning is iterative. Same procedure is applied repeated to the first element in the queue.

## Comparison to the Prim's Minimum spanning tree
+ maintain two sets of vertices.
+ First set: the set in the MST
+ second set: the set not in MST
+ At every step, find the minimal edge from first set to second set, and move the edge to first set.
+ Done when second set if empty

#Bellman–Ford Algorithm
https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

Work for edge with negative weight.

+ initialize dist[] with value of Integer.MAX_VALUE and dist[src] =0;
+ do |V|-1 cycle. For each cycle does this.
  + go over every edge. Suppose the edge is u-v.
  + if dist[u]+weight(u->v) < dist[v]. update dist[v] with new value.
  
The idea: 
+ first iteration give the shortest path with one edge.
+ the second iteration gives the shortest path with two edges.
+ maximal number of edge in the shortest path  is |V| -1

# All-pair shortest path algorithm
If all weights are positivve, just run the Dijkstra's algorithm |v| time. 

If the weight could be negative,
## Floyd-Warshall algorithm (work for negative cycle)

1. create distance matrix equals to graph. If the graph(u, v) has not edge, distance is INFINITE.
2. run |V| time
  each time, pick one vertex |k| as intermediate vertex.

## Johnson's algorhtm
If Dijkstra's algorithm performs better, why not converts the weight to positive, then use Dijkstra algorithm?
https://www.geeksforgeeks.org/johnsons-algorithm/

1. added a new vertex s. add weight (s, v)=1 for v belongs V.
2. run Bellman-Ford algorithm from s to v. The shortest weight for each v is the weight of vertext h[v]
   1. if there is negative cycle, stop.
3. transform the W(u, v) to W(u,v)+h[u]-h[v].
   1. if shortest path from s to v passes (u, v), h[v] = h[u]+w(u, v)
   2. if shortest path from s to v doesn't pass (u,v), h[v] <h[u]+w(u,v)
   3. so h[v]<=h[u]+w(u,v),  -> w'(u,v) = h[u]+w(u,v)-h[v] >=0
   After this all weight are positive.
   4. There are multiple paths from (u, v), all these paths change by the same amount, **not depending 
   how many edges in the path.** 
4. remove s, and run dijkstra algorithm
   

##Derivation
+ negative cycle detection: Bellman-Ford algorithm
+ Floyd-Warshall alogithm: w[u,u] < 0.
+ all sources, single destination: reverse edge direction
+ longest path. negate edge weight.
+ shortest path algorithm not only solves the 'shortest' path problem, but also solves the "reachablility" path problems: whether there is a path, how many pathes, etc.
