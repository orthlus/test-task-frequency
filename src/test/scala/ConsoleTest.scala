import main.ConsoleMain
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

class ConsoleTest extends AnyFunSuite {
  test("console, args - empty file") {
    val fileUrl = getClass.getResource("/test-file-3.txt").getFile.substring(1)

    an[RuntimeException] should be thrownBy ConsoleMain.main(Array(fileUrl))

    val thrown = the[RuntimeException] thrownBy ConsoleMain.main(Array(fileUrl))
    thrown.getMessage shouldBe "passed file is empty"
  }

  test("console, args - not found file") {
    val fileUrl = "/test-file-0.txt"

    an[RuntimeException] should be thrownBy ConsoleMain.main(Array(fileUrl))

    val thrown = the[RuntimeException] thrownBy ConsoleMain.main(Array(fileUrl))
    thrown.getMessage shouldBe "passed file not found or path is invalid"
  }

  test("console, args - no args") {
    an[RuntimeException] should be thrownBy ConsoleMain.main(Array())

    val thrown = the[RuntimeException] thrownBy ConsoleMain.main(Array())
    thrown.getMessage shouldBe "need only 1 argument - local file path"
  }
}
