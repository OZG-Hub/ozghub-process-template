package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.BinaryContentV1
import de.seitenbau.serviceportal.scripting.api.v1.form.content.FormContentV1

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine
FormContentV1 applicantForm = api.getVariable("applicantForm", FormContentV1)

// set FIT-Connect payload
String fitConnectPayload = """
You can construct the payload that is sent to FIT-Connect here.
The format of that payload depends on the selected third-party-system (and should correspond to the `mimeType` property
  of the following Service task).
For instance, when sending to enaio, you'd construct a JSON-object here.
Those articles from the groovy documentation might help:
- JSON: https://groovy-lang.org/processing-json.html#_jsonoutput
- XML: https://groovy-lang.org/processing-xml.html#_creating_xml

To read data from the forms, you need to use the FormContentV1 and FormFieldContentV1 objects.
In the following example, we assume the form contains a group "personalDataGroup" and a field "firstName":

${applicantForm.fields.get("personalDataGroup:0:firstName").value}
      ^                             ^         ^      ^ 
      |                             |         |      |
     form                        groupId   instance fieldId

As you can see, fields are identified with their groupId, a instance number and their fieldId.
Instance numbers usually are zero unless you use a grouping element that can be filled multiple times (e.g. tables).
In such a case it's usually best to select the desired fields with a regEx and loop over the results.
For instance:

${
  applicantForm.fields
          .findAll { it.key.matches("childrenTable:\\d*:name") }
          .each { key, field ->
            // the child's name is available in field.value
          }
} 

Not all field types return a String.
For instance, checkbox-lists return a List<String>
In such a case, you will need to find a suitable String representation for that field.
See the documentation for all fields and their data types: https://doku.pmp.seitenbau.com/display/DFO/Feldtypen
"""
api.setVariable("fitConnectPayload", fitConnectPayload)

// set attachments for FIT-Connect
// TODO: By default, this code collects all the files a user uploaded with the multi-upload form field type.
//   If this is not what is required, feel free to modify this code
Set<BinaryContentV1> fitConnectAttachments = [] // start with an empty set
applicantForm.fields.findAll { it.value.value instanceof List }.each {
  // Field could be a multi-upload, but could also be a normal list element. So we need to look at the actual values
  List list = it.value.value as List
  list.each { element ->
    if (element instanceof BinaryContentV1) {
      // Now we know, that this is indeed a multi-upload
      fitConnectAttachments.add(element)
    }
  }
}
api.setVariable("fitConnectAttachments", fitConnectAttachments)
