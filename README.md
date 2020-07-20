## Minimum/Maximum Triangle Paths - assignment
Application reads from standard-input a tree in the following format:
```
1
2 1
3 1 2
```
For given tree, application finds the shortest (minimal) path from top node to bottom one and prints it to standard-output.
Result for example tree:
```
Minimal path is: 1 + 1 + 1 = 3
```

### Original Assignment

[Assignment Description PDF](assignment.pdf)

### Requirements

 * any [JDK](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-20.1.0) (> 8.0) - tested on OpenJDK 64-Bit GraalVM CE 19.3.0 
 * [sbt](https://www.scala-sbt.org/download.html)
 
### How to run
Application reads data from standard-input and prints results to standard-output

```scala
sbt run
```

If you want to run program with example tree as input, use following command:
```
cat src/it/resources/SimpleTree.txt| sbt run
```

### How to test

* Unit tests
```scala
sbt test
```
* Integration tests
```scala
sbt it:test
```
* All available tests:
```
sbt test it:test
```

### Important

After I submitted the assignment I found out that there is one potential issue with current solution:
- rows are treated as lists and in TriangleShortestPathSolver.scala:17 there is foldRight where list is reverted
- it can be fixed by changing it to foldLeft, List to Queue and prepending to appending (enqueueing in this case)
Thir [PR](https://github.com/pchmiele/assignment/pull/1) is taking care of this problem.