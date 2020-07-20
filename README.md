## Minimum/Maximum Triangle Paths - assignment
Application reads tree in the following format:
```
1
2 1
3 1 2
```
For given tree, application finds the shortest path from top node to bottom one and prints it on Standard Output.
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
Application reads data from Standard Input and prints results to Standard Output

```scala
sbt run
```

If you want to run program with example tree as input use following command:
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
``````
sbt test it:test