package pl.netigen.coreapi.gdpr

interface GDPRClickListener {
    fun onConsentAccepted(personalizedAds: Boolean)

    fun clickPay()
}