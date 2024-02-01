package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1

final List<String> expectedParameters = [
        "fitConnectClientId",
        "fitConnectClientSecret",
        "fitConnectDestinationId"
        // TODO: Add parameters this process uses
]

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

assert api?.startParameter?.leistung?.id != null: "Failed to initiate process. Leika-ID required but was not " +
        "provided. Please ensure that the process is provided a Leika-ID via process-start-parameters."
assert api?.startParameter?.organisationseinheit?.id != null: "Failed to initiate process. OrgUnit-ID required but " +
        "was not provided. Please ensure that the process is provided a OrgUnit-ID via process-start-parameters."
assert api.getStartParameter().parameters != null: "Failed to initiate process. Process parameters are not present. " +
        "Please ensure least one parameter is configured in Jesaja."

expectedParameters.each {parameterName ->
  def parameterValue = api.getStartParameter().parameters.get(parameterName)
  if (parameterValue == null) {
    throw new IllegalStateException("Failed to initialize process. Mandatory parameter '$parameterName' is not " +
            "available. Please add this parameter to the Jesaja system.")
  }
}

// TODO: Add additional checks for expected values.
//  e.g. checking if a parameter 'costInCent' only contains numbers.
