package processIdHere

import commons.serviceportal.helpers.ServiceportalLogger
import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.fitconnect.FitConnectSubmissionServiceResultV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

FitConnectSubmissionServiceResultV1 fitConnectResult = api.getVariable("fitConnectResult", FitConnectSubmissionServiceResultV1)
assert fitConnectResult != null

String submissionId = fitConnectResult.submissionId
String caseId = fitConnectResult.caseId

ServiceportalLogger.log("Sending data to FIT-Connect succeeded. submissionId = '$submissionId', caseId = '$caseId'")

// Used for Bund ID account message:
api.setVariable("fitConnectCaseId", caseId)
api.setVariable("fitConnectSubmissionId", submissionId)
