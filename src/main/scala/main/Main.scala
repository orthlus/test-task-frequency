package main

import main.FrequencyAnalyze.analyze

import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Main {
  def main(args: Array[String]): Unit = {
    val filePath = parseFilePathFromArgs(args)
    Using(Source.fromFile(filePath)) {
      source =>
        val lines = source.getLines()
        if (lines.isEmpty) {
          exit("passed file is empty")
        }

        val result = analyze(lines).mkString("\n")
        println(s"frequency analyze result:\n$result")
    }.get
  }

  private def parseFilePathFromArgs(args: Array[String]): String = {
    if (args.length != 1) {
      exit("need only 1 argument - local file path")
    }
    if (Files.notExists(Paths.get(args(0)))) {
      exit("passed file not found or path is invalid")
    }
    args(0)
  }

  private def exit(errorMessage: String): Unit = {
    System.err.println(errorMessage)
    throw new RuntimeException(errorMessage)
    // для тестов
    // System.exit(1)
  }
}
