# VoiceMixer  
+  It doesn't matter which speaker each interval belongs to . We need to sort them by points. A point is an interval
      start or end position. 
+ At each point, we need to take some action: add a new sound segment or remove sound segment. We are changing the 
     active set of sound segment. 
+ After action is taken, we need to make an optimal decision. We pick up the max sound from Heap. 
  
#Attraction: 
+ We also maintain an active set.  The active set is the set of open attractions. When we exit an attraction, 
  the set of open attractions changes.  Here are the active set of attractions
  1. open as it is right now. 
  1. It has not been visited
+ The whole optimization approach is finding an attraction which can be visited and have the earliest finishing time
  so we have more remaining time for other attraction.
+ when we have the active set, we can select the one with smallest duration. Since all attractions start from the 
  last visit's finish time, smallest duration will give earliest finish time for this visit.  
   + However, there is a catch here, when we are visiting the selected one. There could be one with much smaller 
   duration open. For this new open one, the finish time will be start_time + duration.  So here we need to an
   optimal choice from these two optimal choices in its own domain. 
   + Once we make a final optimal choice, we visit it and changed the finish time. At this time, more attractions
   are open. We need to add them to active set. 
+ There is no interval. There is only one start point and open end .
#Similarity
+ PriorityQueue maintains dynamic set.
+ A sorted list for input (interesting points | start_point/first end time) to scan input in an orderly fashion
+ Change the dynamic set after making an optimal decision.


  
  
  