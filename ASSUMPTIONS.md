# Assumptions

1. There will be only one minimal path 
2. (Written Before I Started) I plan to start with implementation (code + tests) of algorithm to find Minimum Triangle Paths and after I'm sure it
works I'm going to focus on the others parts of application (parsing input, rendering output, validation)
3. (Written Before I Started) My first thought was to build ADT representation of a Tree and use DFS to find the shortest path, but because of the simplicity 
and the fact that building the representation of a Tree will take time, and it won't be used anywhere else I'm going to use
the bottom up approach.
4. Algorithm should find both: minimal path length and steps of this path 
5. Application will not return results for invalid input. It will validate input and return information what is wrong with it in such case.
    6. I wanted to write the application in a way I usually write them. In most cases I try to write purely functional application (focusing on the functional paradigm of scala). 
7. [TriangleShortestPathSolver.scala](src/main/scala/com/assignment/algorithm/TriangleShortestPathAlgorithm.scala) is a place where entire algorithm is located.