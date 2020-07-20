package com.assignment.algorithm

import cats.Applicative
import com.assignment.domain.{RowsInReverseOrder, Solution}

trait TriangleShortestPathAlgorithm[F[_]] {
  def findShortestPath(input: RowsInReverseOrder): F[Solution]
}

class TriangleShortestPathSolver[F[_]: Applicative] extends TriangleShortestPathAlgorithm[F] {
  override def findShortestPath(input: RowsInReverseOrder): F[Solution] = Applicative[F].pure(Solution(0, List.empty))
}
