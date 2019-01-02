## Cover problem

+ **Connectivity** study the cut problem. What is the weakest point or edge. How can I establish bifold 
redundancy
+ **Cover** Study cover. How can I cover all nodes or all edges.
   + Cover edge edge exactly once. This is Euler Path or Euler tour. Corresponding to bridge problem
   + Cover each vertex once. This is hamilton cycle or path problem
   
### Euler Tour. 
We care about edge. For example, gabage collecting or mailman post route. In both case, we want to cover
 each edge only once.
 

#### prerequites
undirected graph.
+ all even degree or only two odd degree.

directed graoh
+ in degree equals out degree for every node.

+ Each edge is touched once. Vertex can be touched many times.
#### Fleury's Algorithm
+ If all even degree, start from any vertex. If there are two odd degree, start with the node with odd 
degree
+ At each vertex, Only go edge that is not bridge.  **Don't burn bridge**
  + how to test one edge is bridge or not.

#### Hierholzer's Algorithm
Find all cycle and merge cycles.
+ start from any vertex: this produce an initial cycle.
+ Go over the cycle element: For any vertex, find and merge a child cycle if there is edges from  this vextex.   

EulerLineaer has two implementation
+ First uses a linear approach
+ second uses stack as temporary data structure. Second one follows the Stack pop/push approach.
  + Why is stack better? We can hold of the newly inserted position. For list, once we insert one element. We lost 
  track where we insert.

#### Questions
+ String form a circle: https://www.geeksforgeeks.org/given-array-strings-find-strings-can-chained-form-circle/
    + ask yes or no question.
+ reconstruct itinerary : euler path issue

Al edges need to be used.



  

   
   