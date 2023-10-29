package main

object Symbols {
  // digits, english and russian lower letters
  val allowedSymbols: Set[Char] = Array(0x30 to 0x39, 0x61 to 0x7A, 0x430 to 0x44F)
    .flatMap(_.toSeq)
    .map(_.toChar)
    .toSet

  private val possibleSplitSymbols = " !#$%&'()*+,-./:;<=>?@\""
    .split("")
    .map(_.charAt(0))
    .toSet

  val splitSymbol: Char = possibleSplitSymbols
    .diff(allowedSymbols)
    .headOption
    .getOrElse(throw new RuntimeException("impossible to calculate splitSymbol"))
}
