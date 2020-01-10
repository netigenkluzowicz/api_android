package pl.netigen.coreapi.payments

class Security {
    //TODO implement proper security measures. As of now(09.01.2020) I've been told not to do anything here.

    companion object {
        const val BASE_64_ENCODED_PUBLIC_KEY: String = ""
        fun verifyPurchase(base64PublicKey: String, signedData: String, signature: String): Boolean = true
    }

}
