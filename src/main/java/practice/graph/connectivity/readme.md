## Connectivity: weak point, weak edge, bifold reduandancy
**Articulation point**, **Bridge**, **Biconnect** are for undirected graph. 
**Articulation Point**, **Bridge** are used to detect **weak point** in a graph system. They are used to solve 
**cut problem**

**Biconnected Graph**: A graph with no articulation point. No matter what node is removed, every node can 
 reach other nodes. So originally there are at least distinct paths for each pairs of nodes. Why we need to
 know graph is biconnected? We want to make sure the graph is **bifold redundant**
  

They can be solved using DFS with extra properties collected during traversal. This is why DFS is good approach 
exploring graph topology.

### DFS and collected properties
+ node color: 
  + white: not visited.
  + grey: visited, but subtree headed by this node has not be fully visited. 
  + black: this nodes and its subtree have been visited.
+ disc: discovery time. Increase by one when each new node is visited.
+ low: the lowest top node's discovery time that can be reached by this node. 
+ node stack: just like tree traversal, we collect the node when
   + a) when we visit the node first time or 
   + b) when we finish its subtree.
   We use stack to collect so we can popup/push dynamically.

### Tree Edge type
+ tree node: from grey node to white node.
+ forward edge: from grey node to black node where grey node's discovery time is less than that black's discovery time.
+ cross edge: from grey node to back node where grey node's discovery time is larger than that black's discovery time.
+ back edge: from black node to grey node. 

There is no forward edge and cross edge for undirected graph. 
+ if there is cross edge from A->B, A should be visited from B in the first place.
+ forward edge will be a back edge.

### Tree traversal and property collection.
 Please see comment in Articulation point
 
 
### Disconnected VS connected
+ **disconnected** a forest
+ **connected** a tree.

### Tree 
Tree is minimal connected graph. Every node is articulation point. Every edge is bridge.
