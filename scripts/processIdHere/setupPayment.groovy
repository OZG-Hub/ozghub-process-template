package processIdHere

import de.seitenbau.serviceportal.scripting.api.v1.ScriptingApiV1
import de.seitenbau.serviceportal.scripting.api.v1.payment.DisplayConfigV1
import de.seitenbau.serviceportal.scripting.api.v1.payment.EpayBlPaymentConfigV1
import de.seitenbau.serviceportal.scripting.api.v1.payment.PaymentConfigV1
import de.seitenbau.serviceportal.scripting.api.v1.payment.TransactionConfigV1
import groovy.json.JsonOutput

ScriptingApiV1 api = apiV1 // Variable is automatically set by Serviceportal process engine

// Note: You probably want to read most of these values from process parameters.
// Use `api.getStartParameter().parameters.get(parameterName)` to do so.

// Setup paymentConfig
PaymentConfigV1 paymentConfig = new EpayBlPaymentConfigV1() // TODO: Select correct type. Might be
                                                            //  - EpayBlPaymentConfigV1, or
                                                            //  - GiroCheckoutPaymentConfigV1, or
                                                            //  - BerlinPaymentConfigV1, or
                                                            //  - Epay21PaymentConfigV1, or
                                                            //  - XBezahldienste1PaymentConfigV1
                                                            //  See https://doku.pmp.seitenbau.com/display/DFO/paymentConfig
paymentConfig.instanz = "bw" // TODO: Fill all required attributes, as described in their respective documentation.
api.setVariable("paymentConfig", JsonOutput.prettyPrint(JsonOutput.toJson(paymentConfig)))

// Setup transactionConfig
// See https://doku.pmp.seitenbau.com/display/DFO/transactionConfig for expected values and other optional parameters
TransactionConfigV1 transactionConfig = new TransactionConfigV1()
transactionConfig.betrag = 1234 // TODO: Update
transactionConfig.verwendungszweck = "TODO" // TODO: Update
api.setVariable("transactionConfig", JsonOutput.prettyPrint(JsonOutput.toJson(transactionConfig)))

// Setup displayConfig
DisplayConfigV1 displayConfig = new DisplayConfigV1()
displayConfig.skipSuccessPage = true // Success page is not necessary, as the process provides it's own "last page"
api.setVariable("displayConfig", JsonOutput.prettyPrint(JsonOutput.toJson(displayConfig)))
