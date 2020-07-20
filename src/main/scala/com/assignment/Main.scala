package com.assignment

import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp, Sync}
import com.assignment.algorithm.TriangleShortestPathSolver
import com.assignment.program.{ConsoleParser, ConsoleSolutionRenderer, LiveConsole}

object Main extends IOApp {
  def solve[F[_]: Sync]: F[Unit] = {
    val console = new LiveConsole[F]
    val parser = new ConsoleParser[F](console)
    val solver = new TriangleShortestPathSolver[F]
    val renderer = new ConsoleSolutionRenderer[F](console)

    for {
      input <- parser.parse()
      solution <- solver.findShortestPath(input)
      _ <- renderer.render(solution)
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] =
    solve[IO]
      .handleErrorWith { case e => IO(println(s"Application crushed with error: ${e.getMessage}")).as(ExitCode.Error) }
      .as(ExitCode.Success)
}
