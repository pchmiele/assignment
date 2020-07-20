package com.assignment.domain

case class RowsInReverseOrder(rows: Rows)

case class Solution(distance: Int, steps: List[Int])
object Solution {
  def apply(singleNodeDistance: Int): Solution =
    new Solution(
      distance = singleNodeDistance,
      steps = List(singleNodeDistance)
    )
}