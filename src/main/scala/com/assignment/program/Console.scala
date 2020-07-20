package com.assignment.program

import cats.effect.Sync

trait Console[F[_]] {
  def putStrLn(line: String): F[Unit]
  def getStrLn: F[Option[String]]
}

class LiveConsole[F[_]: Sync] extends Console[F] {
  def putStrLn(line: String): F[Unit] =
    Sync[F].delay(println(line))
  def getStrLn: F[Option[String]] = {
    Sync[F].delay(Option(scala.io.StdIn.readLine()))
  }
}