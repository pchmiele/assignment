package com.assignment.program

import cats.effect.Sync
import com.assignment.domain.{MinimalPath, NoSolution, Solution}

trait SolutionRenderer[F[_]] {
  def render(solution: Solution): F[Unit]
}

class ConsoleSolutionRenderer[F[_]: Sync](console: Console[F]) extends SolutionRenderer[F] {
  override def render(solution: Solution): F[Unit] = {
    solution match {
      case NoSolution(explanation) =>
        console.putStrLn(s"Could not find a solution for given input data. Here is a reason why: $explanation")
      case MinimalPath(distance, steps) =>
        console.putStrLn(s"Minimal path is = ${steps.mkString(" + ")} = $distance")
    }
  }
}