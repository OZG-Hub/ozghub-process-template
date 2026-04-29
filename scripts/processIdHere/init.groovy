package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

// Allow users to stop/delete the currently running process instance
// See https://doku.pmp.seitenbau.com/pages/viewpage.action?pageId=394836
api.setVariable("killable", true)

String leistungsschluessel
try {
  leistungsschluessel = api.startParameter.leistung.id
} catch (NullPointerException ne) {
  api.logger.warn("Unable to determine the leika key; using null instead.", ne)
  leistungsschluessel = null
}
api.setVariable("leistungsschluessel", leistungsschluessel)