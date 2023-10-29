package main

import main.Symbols.{allowedSymbols, splitSymbol}

object FrequencyAnalyze {
  def analyze(rows: Iterator[String]): Map[String, Int] =
    rows
      .flatMap(row => rowToTokens(row))
      .filterNot(s => s == "")
      .toSeq
      .groupBy(identity)
      .view
      .mapValues(_.size)
      .toMap

  private def rowToTokens(row: String): Array[String] =
    if (row == "") Array()
    else row.toLowerCase
      .toCharArray
      .map(ch => if (allowedSymbols.contains(ch)) ch else splitSymbol)
      .mkString
      .split(splitSymbol)
}
