package pl.netigen.ads

import com.google.android.gms.ads.AdRequest

interface IAdMobRequest {
    fun getAdRequest(): AdRequest
}