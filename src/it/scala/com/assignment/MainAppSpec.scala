package com.assignment.algorithm

import java.io.{ByteArrayOutputStream, File}
import java.nio.charset.StandardCharsets

import cats.effect.ExitCode
import com.assignment.Main
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{Assertion, OptionValues}

import scala.io.Source

class MainAppSpec extends AnyFlatSpec with Matchers with OptionValues {
  "App" should "work correctly for example from assignment" in new Context {
    compare(s"SimpleTree.txt", "Minimal path is: 7 + 6 + 3 + 2 = 18")
  }

  it should "work correctly when no data provided" in new Context {
    compare(s"EmptyTree.txt", "No data provided. Could not find the minimal path.")
  }

  it should "work correctly when single node tree provided" in new Context {
    compare(s"SingleNodeTree.txt", "Minimal path is: 1")
  }

  it should "work correctly when invalid (single row) tree provided" in new Context {
    compare(s"InvalidSingleRowTree.txt", "Could not find a solution for given input data. Here is a reason why: For single row input it has to be of length == 1.")
  }

  trait Context {
    def compare(inputFileName: String, expectedOutput: String): Assertion = {
      val inputPath = new File(getClass.getResource("/" + inputFileName).getFile).getAbsolutePath
      val inputStream =  Source.fromFile(inputPath, StandardCharsets.UTF_8.displayName()).reader()

      val outputStream = new ByteArrayOutputStream
      Console.withOut(outputStream) {
        Console.withIn(inputStream){
          Main.run(List.empty).unsafeRunSync() shouldBe ExitCode.Success
          val result = outputStream.toString(StandardCharsets.UTF_8).trim
          result shouldBe expectedOutput
        }
      }
    }
  }
}


