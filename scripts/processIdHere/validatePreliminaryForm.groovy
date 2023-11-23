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
if (yourComplexCheck(fieldToCheck.value) == false) {
  final String errorMsg = "todo" // TODO: Please add a helpful error message (in the language of your form) which tells
  //                                  your users what they need to do to fix the problem.
  fieldToCheck.validationMessages.add(new ValidationMessageV1(MessageTypeV1.INTERNAL, errorMsg))
  api.setVariable("isFormValid", false)
}

