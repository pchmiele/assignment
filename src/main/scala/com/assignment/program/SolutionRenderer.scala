package com.assignment.program

import cats.effect.Sync
import com.assignment.domain.{InvalidResults, InvalidSingleRowTreeGiven, MinimalPath, Solution}

trait SolutionRenderer[F[_]] {
  def render(solution: Solution): F[Unit]
}

class ConsoleSolutionRenderer[F[_]: Sync](console: Console[F]) extends SolutionRenderer[F] {
  private def invalidMessage(explanation: String): String = {
    s"Could not find a solution for given input data. Here is a reason why: $explanation"
  }

  override def render(solution: Solution): F[Unit] = {
    solution match {
      case InvalidSingleRowTreeGiven =>
        console.putStrLn(invalidMessage("For single row input it has to be of length == 1."))
      case InvalidResults =>
        console.putStrLn(invalidMessage("Found multiple minimal paths in given tree - this may be a result of invalid input data."))
      case MinimalPath(distance, steps) if steps.length == 1 =>
        console.putStrLn(s"Minimal path is: $distance")
      case MinimalPath(distance, steps)=>
        console.putStrLn(s"Minimal path is: ${steps.mkString(" + ")} = $distance")
    }
  }
}