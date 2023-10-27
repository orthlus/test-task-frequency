package main

object FrequencyAnalyze {
  // digits, english and russian lower letters
  val allowedSymbols: Set[Char] = Seq(0x30 to 0x39, 0x61 to 0x7A, 0x430 to 0x44F)
    .flatMap(range => range.toSeq)
    .map(i => i.toChar)
    .toSet

  // TODO нужно проверять что символ не входит в allowedSymbols
  val splitSymbol: Char = ' '

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
