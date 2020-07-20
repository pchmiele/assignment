package com.assignment

import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp, Sync}
import com.assignment.algorithm.TriangleShortestPathSolver
import com.assignment.domain.{NoInputData, WrongRowLength}
import com.assignment.program.{ConsoleParser, ConsoleSolutionRenderer, LiveConsole, Validator}

object Main extends IOApp {
  private def solve[F[_]: Sync]: F[Unit] = {
    val console = new LiveConsole[F]
    val validator = new Validator[F]
    val parser = new ConsoleParser[F](console, validator)
    val solver = new TriangleShortestPathSolver[F]
    val renderer = new ConsoleSolutionRenderer[F](console)

    for {
      input <- parser.parse()
      solution <- solver.findShortestPath(input)
      _ <- renderer.render(solution)
    } yield ()
  }

  private def printAndExitAsError(message: String): IO[ExitCode] = {
    IO(println(message)).as(ExitCode.Error)
  }

  override def run(args: List[String]): IO[ExitCode] =
    solve[IO]
      .handleErrorWith {
        case NoInputData => printAndExitAsError("No data provided. Could not find the minimal path.")
        case WrongRowLength(row, length, expectedLength) => printAndExitAsError(s"#$row Row has different size ($length) than expected ($expectedLength)")
        case e => IO(println(s"Application crushed with error: ${e.getMessage}")).as(ExitCode.Error)
      }
      .as(ExitCode.Success)
}
