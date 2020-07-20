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
      case Nil => Applicative[F].pure(Solution(rows.head.head)) // TODO: handle case where num of nodes in row is different than 0
      case previousRows =>
        val lastLine = rows.head
        val initialState = lastLine.map(Path.apply)
        val result = previousRows.foldLeft(initialState)(updateShortestPathInRow)

        Applicative[F].pure(Solution(result.head)) // TODO: handle case when there are more (or less) results than 1
    }
  }
}
