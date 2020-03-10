package pl.netigen.core.ads

import com.google.android.gms.ads.AdRequest

interface IAdMobRequest {
    fun getAdRequest(): AdRequest
}