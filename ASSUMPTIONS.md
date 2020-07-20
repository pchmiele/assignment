# Assumptions

1. There will be only one minimal path 
2. I plan to start with implementation (code + tests) of algorithm to find Minimum Triangle Paths and after I'm sure it
works I'm going to focus on the others parts of application (parsing input, rendering output, validation)
3. My first thought was to build ADT representation of a Tree and use DFS to find the shortest path, but because of the simplicity 
and the fact that building the representation of a Tree will take time, and it won't be used anywhere else I'm going to use
bottom up approach.