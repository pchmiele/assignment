package com.assignment.program

import cats.effect.Sync
import com.assignment.domain.WrongRowLength

trait Validation[F[_]] {
  def validateRowLength(row: List[Int], expectedRowLength: Int): F[Unit]
}

class Validator[F[_] : Sync] extends Validation[F] {
  override def validateRowLength(row: List[Int], expectedRowLength: Int): F[Unit] =
    if(row.length != expectedRowLength) Sync[F].raiseError(WrongRowLength(expectedRowLength, row.length, expectedRowLength))
    else Sync[F].unit
}
