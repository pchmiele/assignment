package com.assignment.algorithm

import cats.Id
import cats.data.NonEmptyList
import com.assignment.domain.RowsInReverseOrder
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.assignment.domain._

class TriangleShortestPathSolverSpec extends AnyFlatSpec with Matchers {
  "findShortestPath" should "find valid solution for single node tree" in new Context {
    val input = RowsInReverseOrder(NonEmptyList.one(List(7)))
    algorithm.findShortestPath(input) shouldBe Solution(distance = 7, steps = List(7))
  }

  it should "find valid solution for tree with two rows" in new Context {
    val input = RowsInReverseOrder(NonEmptyList(List(6, 3), List(List(7))))
    algorithm.findShortestPath(input) shouldBe Solution(distance = 10, steps = List(7, 3))
  }

  it should "find valid solution for example tree from assignment" in new Context {
    val input = RowsInReverseOrder(NonEmptyList(List(11, 2, 10, 9), List(List(3, 8, 5), List(6, 3), List(7))))
    algorithm.findShortestPath(input) shouldBe Solution(distance = 18, steps = List(7, 6, 3, 2))
  }

  trait Context {
    val algorithm = new TriangleShortestPathSolver[Id]
  }
}
