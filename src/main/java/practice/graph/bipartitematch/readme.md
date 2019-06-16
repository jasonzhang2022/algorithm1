Bipartite Matching
An important algorithm to assign: die to letter, applicant to job, etc.
How does the algorithm works.
1. Find Augmenting path. 
   1. start with free vertex
   2. alternating path
   3. end with free vertex
   
   The implementation uses dist[left_vertex] which serves several purpose.
   If it is matching vertex, it is initialized as -1. otherwise it is initialized as 0. By this way
   1. We know which vertex is free. we can start from free vertex
   2. When finding alternating path means looking for matching vertex: dist[x]==-1
   3. When matching vertex is visited, we set dist[x] as visited depth. It contains more information than
      boolean value. This boolean value is used next step
   
   As regular BFS, queue is used to add all nodes in next level.
2. Find a path using DFS
   1. when a free vertex as right is reached, a path is found. 
   2. Only find alternating vertex found from previous step.
 
   

