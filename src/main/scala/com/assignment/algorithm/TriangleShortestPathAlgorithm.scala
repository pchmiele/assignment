package com.assignment.algorithm

import cats.Applicative
import com.assignment.domain.{InvalidResults, InvalidSingleRowTreeGiven, Node, Path, RowsInReverseOrder, Solution}

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
        Applicative[F].pure(InvalidSingleRowTreeGiven)

      case Nil =>
        Applicative[F].pure(Solution(rows.head.head))

      case previousRows =>
        val lastLine = rows.head
        val initialState = lastLine.map(Path.apply)
        val result = previousRows.foldLeft(initialState)(updateShortestPathInRow)

        if(result.length == 1) Applicative[F].pure(Solution(result.head))
        else Applicative[F].pure(InvalidResults)
    }
  }
}
