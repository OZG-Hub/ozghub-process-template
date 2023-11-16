import spock.lang.Specification

class ExampleTest extends Specification {
  def "Perform a example operation"() {
    given:
    int anExampleValue = 1

    when: "you run a code snippet under test"
    int result = anExampleValue + 1

    then: "you get your expected values"
    result == 2
  }
}
