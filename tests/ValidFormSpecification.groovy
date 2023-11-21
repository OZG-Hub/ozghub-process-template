import commons.serviceportal.forms.FormValidator
import spock.lang.Specification

class ValidFormSpecification extends Specification {
  def "Verify form is valid"() {
    when:
    // TODO: Add the filenames of all forms in this project here.
    //   e.g. List<String> forms = ["Staatskanzlei_OpferVonGewalttaten_Applicant-v1.0-de.json"]
    throw new RuntimeException("Did you forget to add all the forms to the ValidFormSpecification?")
    List<String> forms = []
    forms.each { form ->
      new FormValidator(getClass().getResourceAsStream(form).text).validate()
    }

    then:
    noExceptionThrown()
  }
}
