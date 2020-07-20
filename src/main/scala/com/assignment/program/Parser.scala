package com.assignment.program

import cats.effect.Sync
import com.assignment.domain.{NoInputData, RowsInReverseOrder}
import cats.implicits._

trait Parser[F[_]] {
  def parse(): F[RowsInReverseOrder]
}

class ConsoleParser[F[_]: Sync](console: Console[F]) extends Parser[F] {
  private def parseLineByLine(alreadyParsedLines: List[List[Int]]): F[RowsInReverseOrder] =
    console.getStrLn.flatMap {
      case None => Sync[F].fromOption(alreadyParsedLines.toNel.map(RowsInReverseOrder.apply), NoInputData)
      case Some(line) =>
        val words = line.split(" ")
        val numbers = words.map(_.toInt).toList
        parseLineByLine(numbers :: alreadyParsedLines)
    }

  override def parse(): F[RowsInReverseOrder] = parseLineByLine(List.empty)
}