package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1

final List<String> expectedParameters = [
        "fitConnectClientId",
        "fitConnectClientSecret",
        "fitConnectDestinationId"
        // TODO: Add parameters this process uses
]

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

expectedParameters.each {parameterName ->
  def parameterValue = api.getStartParameter().parameters.get(parameterName)
  if (parameterValue == null) {
    throw new IllegalStateException("Failed to initialize process. Mandatory parameter '$parameterName' is not " +
            "available. Please add this parameter to the Jesaja system.")
  }
}

// TODO: Add additional checks for expected values.
//  e.g. checking if a parameter 'costInCent' only contains numbers.
