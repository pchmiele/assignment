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

  trait Context {
    val algorithm = new TriangleShortestPathSolver[Id]
  }
}
