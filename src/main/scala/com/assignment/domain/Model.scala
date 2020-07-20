package com.assignment.domain

case class RowsInReverseOrder(rows: Rows)

sealed trait Solution
case class MinimalPath(distance: Int, steps: List[Node]) extends Solution

sealed trait NoSolution extends Solution
case object InvalidSingleRowTreeGiven extends NoSolution
case object InvalidResults extends NoSolution

object Solution {
  def apply(singleNodeDistance: Int): Solution =
    MinimalPath(
      distance = singleNodeDistance,
      steps = List(singleNodeDistance)
    )

  def apply(path: Path): Solution =
    MinimalPath(
      distance = path.distance,
      steps = path.steps
    )
}

case class Path(steps: List[Node], distance: Int)
object Path {
  def apply(initialDistance: Int): Path =
    new Path(
      steps = List(initialDistance),
      distance = initialDistance
    )
  def updatePath(currentPath: Path, additionalStep: Node): Path =
    new Path(
      steps = additionalStep +: currentPath.steps,
      distance = additionalStep + currentPath.distance
    )

  def shorterPath(left: Path, right: Path): Path =
    if(left.distance <= right.distance) left else right
}