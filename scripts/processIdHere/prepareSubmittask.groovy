package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1
import de.seitenbau.serviceportal.scripting.api.v1.submit.SubmitTaskConfigV1
import de.seitenbau.serviceportal.scripting.api.v1.submit.SubmitTaskFormConfigV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine
FormContentV1 applicantForm = api.getVariable("applicantForm") as FormContentV1

SubmitTaskConfigV1 submitConfig = new SubmitTaskConfigV1()
submitConfig

submitConfig.title = "Zusammenfassung"
submitConfig.submitTaskFormConfigs = [new SubmitTaskFormConfigV1(applicantForm, "Angaben korrigieren", "back", false)]
submitConfig.submitButtonLabel = "Absenden"

api.setVariable("submitConfig", submitConfig)