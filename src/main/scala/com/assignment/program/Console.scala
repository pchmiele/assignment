package com.assignment.program

import cats.effect.Sync

trait Console[F[_]] {
  def putStrLn(line: String): F[Unit]
  def getStrLn: F[String]
}

class LiveConsole[F[_]: Sync] extends Console[F] {
  def putStrLn(line: String): F[Unit] =
    Sync[F].delay(println(line))
  def getStrLn: F[String] =
    Sync[F].delay(scala.io.StdIn.readLine())
}