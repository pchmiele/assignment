package com.assignment.algorithm

import cats.Applicative
import com.assignment.domain.{InvalidResults, InvalidSingleRowTreeGiven, Node, Path, RowsInReverseOrder, Solution}

import scala.collection.immutable.Queue

trait TriangleShortestPathAlgorithm[F[_]] {
  def findShortestPath(input: RowsInReverseOrder): F[Solution]
}

class TriangleShortestPathSolver[F[_]: Applicative] extends TriangleShortestPathAlgorithm[F] {
  private def choseShorterPath(currentNode: Node, leftPath: Path, rightPath: Path): Path = {
    val shorterPath = Path.shorterPath(leftPath, rightPath)
    Path.updatePath(shorterPath, currentNode)
  }

  private def updateShortestPathInRow(initialState: Queue[Path], row: Queue[Node]): Queue[Path] = {
    initialState.sliding(2).zip(row).foldLeft(Queue.empty[Path]) {
      case (pathsAcc, (Queue(leftPath, rightPath), node)) =>
        pathsAcc.enqueue(choseShorterPath(node, leftPath, rightPath))
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
