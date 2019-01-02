##Kruskals's Algorithm
https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

add smallest edges one by one and avoid cycle

## Prim's algorithm
1. maintain a priority queue
   + key is vertex. Value is the distance from vextices in MST set to non-mst set
   + the initial vextex 0 has value 0.
2. pick up the vertex u has smallest value in the priority queue
   + find all v with edge (u,v), if edge(u,v) < q[v], update q[v] to edge[u,v]
   
 

