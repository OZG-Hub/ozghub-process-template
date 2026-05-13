package processIdHere

import commons.serviceportal.SupportContactDataGetter
import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

// Write to log
api.logger.warn("Failed to transfer data to FIT-Connect. Error 'fitConnectSubmissionServiceError' " +
        "occurred. Please check logs for other entries indicating the actual error reason. User will now be sent to " +
        "a 'a error occurred' form.")

// Prepare error message for user
String errorMessageForUser = "Ihr Antrag wurde erfolgreich eingereicht.<br>" +
        "Leider ist beim Nachrichtenversand an Ihr Postfach ein technischer Fehler aufgetreten.<br>" +
        "Bitte wenden Sie sich mit der Support-ID '${api.processInstance.id}' an die folgende Stelle:<br><br>" +
        "${SupportContactDataGetter.getSupportContactOnOzgHub(api).replace("\n","<br>")}"
api.setVariable("errorMessageForUser", errorMessageForUser)