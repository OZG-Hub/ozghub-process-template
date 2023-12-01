package processIdHere

import commons.serviceportal.SupportContactDataGetter
import commons.serviceportal.helpers.ServiceportalLogger
import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

// Write to log
ServiceportalLogger.logWarn("Failed to transfer data to FIT-Connect. Error 'fitConnectSubmissionServiceError' " +
        "occurred. Please check logs for other entries indicating the actual error reason. User will now be sent to " +
        "a 'a error occurred' form.")

// Prepare error message for user
String errorMessageForUser = """\
Bei der Übermittlung Ihrer Daten an die zuständige Stelle ist ein technischer Fehler aufgetreten. Bitte wenden Sie sich \
mit der Support-ID '${api.processInstance.id}' an die folgende Stelle:


${SupportContactDataGetter.getSupportContactOnOzgHub(api)}
""".stripIndent()
api.setVariable("errorMessageForUser", errorMessageForUser)

