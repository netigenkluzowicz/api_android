package pl.netigen.coreapi.payments

import kotlinx.coroutines.flow.Flow

interface INoAds {
    val noAdsActive: Flow<Boolean>

    fun makeNoAdsPayment()
}