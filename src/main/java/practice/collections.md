## Map
+ compute, computeIfPresent(), computeIfAbsent(): used to transform value, 
+ get, **getOrDefault**, 
+ merge: more flexible than compute. Has initialization value, and continue function
  e.g. merge('x', Stream.of(v).collect(Collectors.toCollection(ArrayList::new)), 
  (oldList, value)->{ oldList.add(value); return oldList; } )
