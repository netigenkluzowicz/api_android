package pl.netigen.gms.ads

import com.google.android.gms.ads.AdRequest

interface IAdMobRequest {
    fun getAdRequest(): AdRequest
}