package processIdHere

import commons.serviceportal.forms.FormDumper
import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine
FormContentV1 applicantForm = api.getVariable("applicantForm") as FormContentV1

// Generate summary via the FormDumper class
String summaryAsHtml = ""
summaryAsHtml += FormDumper.getCssClasses()
summaryAsHtml += new FormDumper(applicantForm, api).dumpFormAsHtmlTable()

// Store summary as process instance variable
api.setVariable("summaryAsHtml", summaryAsHtml)

