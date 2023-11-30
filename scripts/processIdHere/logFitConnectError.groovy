package processIdHere

import commons.serviceportal.helpers.ServiceportalLogger
import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.start.OrganisationseinheitParameterV1
import de.seitenbau.serviceportal.scripting.api.v1.start.StartParameterV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

// Write to log
ServiceportalLogger.logWarn("Failed to transfer data to FIT-Connect. Error 'fitConnectSubmissionServiceError' " +
        "occurred. Please check logs for other entries indicating the actual error reason. User will now be sent to " +
        "a 'a error occurred' form.")

// Prepare error message for user
String errorMessageForUser = """\
Bei der Übermittlung Ihrer Daten an die zuständige Stelle ist ein technischer Fehler aufgetreten. Bitte wenden Sie sich \
mit der Support-ID '${api.processInstance.id}' an die folgende Stelle:


${getSupportContact(api)}
""".stripIndent()
execution.setVariable("errorMessageForUser", errorMessageForUser)

//////////////////////
// HELPER FUNCTIONS //
//////////////////////

static String getSupportContact(ScriptingApiV1 api) {
  StartParameterV1 startParameter = api.getVariable("startParameter", StartParameterV1)
  OrganisationseinheitParameterV1 orgUnit = startParameter.organisationseinheit
  if (orgUnit == null) {
    final String defaultContact = "Land Baden-Württemberg\n" +
            "E-Mail: service-bw@im.bwl.de\n" +
            "Telefax: +49 (0)711/231-5000\n" +
            "Internetseite: https://im.baden-wuerttemberg.de"
    ServiceportalLogger.logWarn("Failed to determine contact when displaying error page for the user. Process " +
            "start parameter 'organisationseinheit' was null. (Probably because this process was not started in a " +
            "parametrized way.) Defaulting to '$defaultContact'")
    return defaultContact
  } else {
    /* Example:
    Musterbehörde

    E-Mail: example@example.org
    Telefon: +49 123 456789
    Fax: +49 123 456 789

    Anschrift:
    Musterstraße 123
    Postfach 123
    12345 Musterort

    Anschrift:
    Musterstraße 123
    Postfach 123
    12345 Musterort
    */

    // Name
    String result = orgUnit.name + "\n"
    result += "\n"

    // Print phone, fax and email
    if(orgUnit.erreichbarkeit?.email){
      result += "Email: ${orgUnit.erreichbarkeit?.email}\n"
    }

    if(orgUnit.erreichbarkeit?.telefon){
      result += "Telefon: ${orgUnit.erreichbarkeit?.telefon}\n"
    }

    if(orgUnit.erreichbarkeit?.fax){
      result += "Fax: ${orgUnit.erreichbarkeit?.fax}\n"
    }

    result += "\n"

    // Address
    orgUnit.anschriften.each {
      result += "Anschrift:\n"
      if (it.strasseHausnummer) {
        result += "${it.strasseHausnummer}\n"
      }
      if (it.postfach) {
        result += "Postfach: ${it.postfach}\n"
      }
      result += "${it.postleitzahl ?: ""} ${it.ort ?: ""}\n"
      result += "\n"
    }

    return result
  }
}
