package com.assignment.algorithm

import cats.Applicative
import com.assignment.domain.{Node, Path, RowsInReverseOrder, Solution}

trait TriangleShortestPathAlgorithm[F[_]] {
  def findShortestPath(input: RowsInReverseOrder): F[Solution]
}

class TriangleShortestPathSolver[F[_]: Applicative] extends TriangleShortestPathAlgorithm[F] {
  private def choseShorterPath(currentNode: Node, leftPath: Path, rightPath: Path): Path = {
    val shorterPath = Path.shorterPath(leftPath, rightPath)
    Path.updatePath(shorterPath, currentNode)
  }

  private def updateShortestPathInRow(initialState: List[Path], row: List[Node]): List[Path] = {
    initialState.sliding(2).zip(row).foldRight(List.empty[Path]) {
      case ((List(leftPath, rightPath), node), pathsAcc) => choseShorterPath(node, leftPath, rightPath) :: pathsAcc
    }
  }

  override def findShortestPath(input: RowsInReverseOrder): F[Solution] = {
    val rows = input.rows
    rows.tail match {
      case Nil if rows.head.length != 1 =>
        Applicative[F].pure(Solution("For single row input - it has to be of length == 1."))

      case Nil =>
        Applicative[F].pure(Solution(rows.head.head))

      case previousRows =>
        val lastLine = rows.head
        val initialState = lastLine.map(Path.apply)
        val result = previousRows.foldLeft(initialState)(updateShortestPathInRow)

        if(result.length == 1) Applicative[F].pure(Solution(result.head))
        else Applicative[F].pure(Solution("Found multiple minimal paths in given tree - this may be a result of invalid input data."))
    }
  }
}
