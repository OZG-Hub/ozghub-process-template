package govTecEventSubmitForm

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.MessageTypeV1
import de.seitenbau.serviceportal.scripting.api.v1.form.ValidationMessageV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormFieldContentV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

api.setVariable("isFormValid", true) // Initially, assume all input is correct
FormContentV1 applicantForm = api.getVariable("applicantForm") as FormContentV1
