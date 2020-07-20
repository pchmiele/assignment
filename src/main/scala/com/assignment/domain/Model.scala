package com.assignment.domain

case class RowsInReverseOrder(rows: Rows)

case class Solution(distance: Int, steps: List[Node])
object Solution {
  def apply(singleNodeDistance: Int): Solution =
    new Solution(
      distance = singleNodeDistance,
      steps = List(singleNodeDistance)
    )

  def apply(path: Path): Solution =
    new Solution(
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