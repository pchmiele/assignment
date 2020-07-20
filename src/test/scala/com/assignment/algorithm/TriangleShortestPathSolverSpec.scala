package com.assignment.algorithm

import cats.Id
import cats.data.NonEmptyList
import com.assignment.domain.RowsInReverseOrder
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.assignment.domain._
import org.scalatest.OptionValues

class TriangleShortestPathSolverSpec extends AnyFlatSpec with Matchers with OptionValues {
  "findShortestPath" should "find no solution for invalid input (single row with no elements)" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.one(
        List.empty
      )
    )
    algorithm.findShortestPath(input) shouldBe NoSolution("For single row input - it has to be of length == 1.")
  }

  it should "find no solution for invalid input (single row with multiple elements)" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.one(
        List(7, 2, 3)
      )
    )
    algorithm.findShortestPath(input) shouldBe NoSolution("For single row input - it has to be of length == 1.")
  }

  it should "find valid solution for single node tree" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.one(
        List(7)
      )
    )
    algorithm.findShortestPath(input) shouldBe MinimalPath(distance = 7, steps = List(7))
  }

  it should "find valid solution for tree with two rows" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.of(
        List(6, 3),
        List(7)
      )
    )
    algorithm.findShortestPath(input) shouldBe MinimalPath(distance = 10, steps = List(7, 3))
  }

  it should "find valid solution for example tree from assignment" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.of(
        List(11, 2, 10, 9),
        List(3, 8, 5),
        List(6, 3),
        List(7)
      )
    )
    algorithm.findShortestPath(input) shouldBe MinimalPath(distance = 18, steps = List(7, 6, 3, 2))
  }

  it should "find no solution for invalid tree" in new Context {
    val input = RowsInReverseOrder(
      NonEmptyList.of(
        List(11, 2, 10, 9),
        List(11, 2, 10, 9)
      )
    )
    algorithm.findShortestPath(input) shouldBe NoSolution("Found multiple minimal paths in given tree - this may be a result of invalid input data.")
  }

  trait Context {
    val algorithm = new TriangleShortestPathSolver[Id]
  }
}
