package pl.netigen.coreapi.payments.model

/**
 * Represents Payment state it can be [PaymentSuccess], [PaymentRestored] or [Error]
 *
 */
sealed class PaymentEvent

/**
 * Represents successful payment done by user with given sku(product Id)
 *
 * @property sku Payment event sku(product Id)
 */
data class PaymentSuccess(val sku: String) : PaymentEvent()

/**
 * Represents payment restored from billing service with given sku(product Id)
 *
 * @property sku Payment event sku(product Id)
 */
data class PaymentRestored(val sku: String) : PaymentEvent()

/**
 *
 * Represents payment or billing service error with message and error type
 *
 * @property errorMessage This error debug message
 * @property errorType This error type
 */
data class PaymentError(val errorMessage: String, val errorType: PaymentErrorType) : PaymentEvent()
