package main

object FrequencyAnalyze {
  // digits, english and russian lower letters
  val allowedSymbolsCodes: Seq[Int] = Seq(0x30 to 0x39, 0x61 to 0x7A, 0x430 to 0x44F)
    .flatMap(range => range.toSeq)

  val allowedSymbols: Set[Char] = allowedSymbolsCodes.map(i => i.toChar).toSet

  val splitSymbol: Char = ' '

  def analyze(rows: Iterator[String]): Map[String, Int] =
    rows
      .flatMap(row => prepareToken(row))
      .toSeq
      .filterNot(s => s == "")
      .groupBy(identity)
      .view
      .mapValues(_.size)
      .toMap

  private def prepareToken(token: String): Array[String] =
    if (token == "") Array()
    else token.toLowerCase
      .toCharArray
      .map(ch => if (allowedSymbols.contains(ch)) ch else splitSymbol)
      .mkString
      .split(splitSymbol)
}
