package pl.netigen.coreapi.donate

import androidx.annotation.Keep
import pl.netigen.extensions.fromJson


@Keep
sealed class DonateEvent {
    abstract val action: String

    @Keep
    data class Unknown(override val action: String = "") : DonateEvent()

    @Keep
    data class Exit(override val action: String = "EXIT") : DonateEvent()

    @Keep
    data class DonateClick(override val action: String, val productIndex: Int) : DonateEvent()


    companion object {
        fun parseMessage(message: String): DonateEvent {
            return when {
                message.contains("EXIT") -> message.fromJson<Exit>()
                message.contains("DONATE") -> message.fromJson<DonateClick>()
                else -> Unknown()
            }
        }
    }
}
