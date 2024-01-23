package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.MessageTypeV1
import de.seitenbau.serviceportal.scripting.api.v1.form.ValidationMessageV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormFieldContentV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

api.setVariable("isFormValid", true) // Initially, assume all input is correct
FormContentV1 preliminaryForm = api.getVariable("preliminaryForm") as FormContentV1

// TODO: Add your own validation here
FormFieldContentV1 fieldToCheck = preliminaryForm.fields.get("groupId:0:fieldId") // TODO: Replace groupId and fieldId
//                                                                                     with the values you set in your
//                                                                                     form
if (!yourComplexCheck(fieldToCheck.value as String)) {
  final String errorMsg = "todo" // TODO: Please add a helpful error message (in the language of your form) which tells
  //                                  your users what they need to do to fix the problem.
  fieldToCheck.addValidationMessage(errorMsg)
  api.setVariable("isFormValid", false)
}

static boolean yourComplexCheck(String fieldValue) {
  // TODO: Implement your own validation logic here. Alternatively, you might want to use a validator from the commons
  //  submodule (see ./commons/serviceportal/scripts/commons/serviceportal/validators). Those validators contain logic
  //  that might be used in more than one process. Feel free to
  //  [contribute](https://github.com/OZG-Hub/serviceportale-commons/blob/master/README.md#contributing) to that project
  //  if you develop a validator that might be useful for others as well.

  return false
}
