package pl.netigen.gms.ads

import com.google.android.gms.ads.AdRequest

/**
 * Provides an [AdRequest] contains targeting information used to fetch an ad including if personalized ads are allowed
 *
 */
interface IAdMobRequest {
    fun getAdRequest(): AdRequest
}