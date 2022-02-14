import static org.slf4j.LoggerFactory.getLogger

import de.seitenbau.serviceportal.formulare.api.FormContent
import org.activiti.engine.delegate.DelegateExecution
import org.activiti.engine.impl.context.Context
import org.slf4j.Logger

// Access process data via this object
DelegateExecution execution = Context.getExecutionContext().getExecution()

// Write logger messages like this
Logger logger = getLogger("de.seitenbau.serviceportal.prozess.processNameHere")
logger.info("[DEBUG:] Example logger message")

// Access form data like this
FormContent formContent = execution.getVariable("applicantForm") as FormContent
