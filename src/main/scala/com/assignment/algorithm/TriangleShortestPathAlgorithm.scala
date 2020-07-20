package com.assignment.algorithm

import cats.Applicative
import com.assignment.domain.{RowsInReverseOrder, Solution}

trait TriangleShortestPathAlgorithm[F[_]] {
  def findShortestPath(input: RowsInReverseOrder): F[Solution]
}

class TriangleShortestPathSolver[F[_]: Applicative] extends TriangleShortestPathAlgorithm[F] {
  override def findShortestPath(input: RowsInReverseOrder): F[Solution] = {
    val rows = input.rows
    rows.tail match {
      case Nil => Applicative[F].pure(Solution(rows.head.head)) // TODO: handle case where num of nodes in row is different than 0
      case _ => Applicative[F].pure(Solution(0, List.empty))
    }
  }
}
