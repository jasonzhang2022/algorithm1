## Connectivity: weak point, weak edge, bifold reduandancy
Articulation Point, Bridge are used to detect **weak point** in a graph system. They are used to solve 
**cut problem**

### Articulation point algorithm:
+ when reach ancestor, low[descendant] = disc[ancestor] (not low[ancestor])
+ root and non-root node has exclusive criteria.
+ terminal node is not articulation point since it doesn't separate graph into two sets.
+ check condition: we check low[child]>=disc[self], not low[self]. 
  + check condition is perform on child's low. But judge whether parent is articulation point or not.

### Bridge
Same traversal logic can be used to find SCC.  Different from articulation point.
+ no root special logic
+ check condition is low[child]>disc[parent] (not >=).

### SCC
Same traversal logic can be used to find SCC.  Different from articulation point.
+ check condition is done at parent node. 
+ low[u] == disc[u].
+ use a stack to maintain all nodes in the subtree.
+ for directed graph

**Biconnected Graph**: A graph with no articulation point. No matter what node is removed, every node can 
 reach other nodes. So originally there are at least distinct paths for each pairs of nodes. Why we need to
 know graph is biconnected? We want to make sure the graph is **bifold redundant**. 
  
 
 How many unique path from one node to anotther: max flow with edge of capacity of 1.

  

They can be solved using DFS with extra properties collected during traversal. This is why DFS is good approach 
exploring graph topology.

### DFS and collected properties
+ node color: 
  + white: not visited.
  + grey: visited, but subtree headed by this node has not be fully visited. 
  + black: this nodes and its subtree have been visited.
   
   Only 3 color for Directed graph. For undirected graph, if it is black, it is ancestor. There is no
   confusion. That is why we don't need 3-color schema.
+ disc: discovery time. Increase by one when each new node is visited.
  + or start_time, we could have finish_time. If we have finish_time, we know how many nodes
    we have in the subtree
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
Tree is **minimal connected graph**. Every node is articulation point. Every edge is bridge.
