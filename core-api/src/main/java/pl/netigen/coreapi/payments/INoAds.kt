package pl.netigen.coreapi.payments

import androidx.lifecycle.LiveData

interface INoAds {
    val noAdsActive: LiveData<Boolean>

    fun makeNoAdsPayment()
}