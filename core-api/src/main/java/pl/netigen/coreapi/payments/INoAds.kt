package pl.netigen.coreapi.payments

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface INoAds {
    val noAdsActive: Flow<Boolean>

    fun makeNoAdsPayment(activity: Activity, noAdsString: String = "${activity.packageName}.noads")
}