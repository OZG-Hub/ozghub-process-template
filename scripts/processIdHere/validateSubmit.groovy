package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.FormFieldKeyV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormFieldContentV1
import de.seitenbau.serviceportal.scripting.api.v1.submit.SubmitTaskResultV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

SubmitTaskResultV1 submitTaskResultV1 = api.getVariable("submitTaskResult", SubmitTaskResultV1)
FormContentV1 applicantForm = api.getVariable("applicantForm", FormContentV1)

if (submitTaskResultV1.formIdToInvalidFieldsMap == null || submitTaskResultV1.formIdToInvalidFieldsMap.isEmpty()) {
  api.setVariable("isValidSubmit", true)
  api.setVariable("pressedButton", submitTaskResultV1.pressedButton)
} else {
  api.setVariable("isValidSubmit", false)
  (submitTaskResultV1.formIdToInvalidFieldsMap as Map<String, Set<FormFieldKeyV1>>).each { String formName, Set<FormFieldKeyV1> keys ->
    for (String formKey in keys) {
      FormFieldContentV1 fieldContent = applicantForm.fields[formKey]
      fieldContent.addValidationMessage("Bitte überprüfen Sie, ob dieser Wert aktuell ist.")
    }
  }
  api.setVariable("applicantForm", applicantForm)
}