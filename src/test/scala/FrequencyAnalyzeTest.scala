import main.{FrequencyAnalyze, Main}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

import scala.io.Source
import scala.util.Using

class FrequencyAnalyzeTest extends AnyFunSuite {
  test("map equals with hardcode input") {
    val result = Map("это" -> 2, "текстовый" -> 1, "файл" -> 1, "второе" -> 1, "предложение" -> 1)

    result shouldBe FrequencyAnalyze.analyze(Seq("Это текстовый файл. Это второе предложение.").iterator)
  }

  test("map equals with read file") {
    val result = Map("это" -> 2, "текстовый" -> 1, "файл" -> 1, "второе" -> 1, "предложение" -> 1)
    val url = getClass.getResource("/test-file-1.txt")

    val calculatedMap = Using(Source.fromURL(url)) {
      source =>
        val lines = source.getLines()
        FrequencyAnalyze.analyze(lines)
    }.get

    calculatedMap shouldBe result
  }

  test("map equals with read multiline file") {
    val result = Map(
      "пунктуации" -> 1,
      "нижний" -> 1,
      "принимает" -> 1,
      "преобразовывать" -> 1,
      "к" -> 1,
      "пробелами" -> 1,
      "их" -> 2,
      "утилита" -> 1,
      "возвращает" -> 1,
      "разделять" -> 1,
      "принимать" -> 1,
      "подсчета" -> 1,
      "все" -> 1,
      "читать" -> 1,
      "параметра" -> 1,
      "текст" -> 1,
      "слов" -> 4,
      "регистр" -> 1,
      "данных" -> 1,
      "файлу" -> 1,
      "файла" -> 1,
      "итоговую" -> 1,
      "которая" -> 1,
      "качестве" -> 2,
      "в" -> 4,
      "на" -> 1,
      "файл" -> 1,
      "создать" -> 1,
      "символы" -> 1,
      "разделенные" -> 1,
      "возвращать" -> 1,
      "частоты" -> 2,
      "входных" -> 1,
      "фильтровать" -> 1,
      "файле" -> 1,
      "текстовый" -> 1,
      "знаки" -> 1,
      "путь" -> 1,
      "слова" -> 2,
      "содержимое" -> 1,
      "карту" -> 3,
      "специальные" -> 1,
      "утилиту" -> 1,
      "должна" -> 1,
      "и" -> 4,
      "вхождений" -> 1,
      "создавать" -> 1)
    val url = getClass.getResource("/test-file-2.txt")

    val calculatedMap = Using(Source.fromURL(url)) {
      source =>
        val lines = source.getLines()
        FrequencyAnalyze.analyze(lines)
    }.get

    calculatedMap shouldBe result
  }

  test("console, args - empty file") {
    val url = getClass.getResource("/test-file-3.txt").getFile.substring(1)

    an[RuntimeException] should be thrownBy Main.main(Array(url))

    val thrown = the[RuntimeException] thrownBy Main.main(Array(url))
    thrown.getMessage shouldBe "passed file is empty"
  }

  test("console, args - not found file") {
    val url = "/test-file-0.txt"

    an[RuntimeException] should be thrownBy Main.main(Array(url))

    val thrown = the[RuntimeException] thrownBy Main.main(Array(url))
    thrown.getMessage shouldBe "passed file not found or path is invalid"
  }

  test("console, args - no args") {
    an[RuntimeException] should be thrownBy Main.main(Array())

    val thrown = the[RuntimeException] thrownBy Main.main(Array())
    thrown.getMessage shouldBe "need only 1 argument - local file path"
  }
}
