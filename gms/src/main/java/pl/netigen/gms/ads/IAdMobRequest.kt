package pl.netigen.gms.ads

import com.google.android.gms.ads.admanager.AdManagerAdRequest

/**
 * Provides an [AdManagerAdRequest] contains targeting information used to fetch an ad including if personalized ads are allowed
 *
 */
interface IAdMobRequest {
    fun getAdRequest(): AdManagerAdRequest
}
