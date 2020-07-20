package com.assignment.program

import cats.data.NonEmptyList
import cats.effect.Sync
import com.assignment.domain.RowsInReverseOrder

trait Parser[F[_]] {
  def parse(): F[RowsInReverseOrder]
}

class ConsoleParser[F[_]: Sync] extends Parser[F] {
  override def parse(): F[RowsInReverseOrder] = Sync[F].pure(RowsInReverseOrder(NonEmptyList.one(List(0))))
}