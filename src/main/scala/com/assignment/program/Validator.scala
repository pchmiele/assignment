package com.assignment.program

import cats.effect.Sync
import com.assignment.domain.{RowContainsInvalidCharacter, WrongRowLength}

trait Validation[F[_]] {
  def validateRowLength(row: Seq[Int], expectedRowLength: Int): F[Unit]
  def validateRow(row: Array[String], rowPosition: Int): F[Unit]
}

class Validator[F[_] : Sync] extends Validation[F] {
  override def validateRowLength(row: Seq[Int], expectedRowLength: Int): F[Unit] =
    if(row.length == expectedRowLength) Sync[F].unit
    else Sync[F].raiseError(WrongRowLength(expectedRowLength, row.length, expectedRowLength))

  override def validateRow(row: Array[String], rowPosition: Int): F[Unit] =
    if(row.forall(_.toIntOption.isDefined)) Sync[F].unit
    else Sync[F].raiseError(RowContainsInvalidCharacter(rowPosition))
}
