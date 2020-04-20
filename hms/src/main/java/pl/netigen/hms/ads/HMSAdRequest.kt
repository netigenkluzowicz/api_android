package pl.netigen.hms.ads

import com.google.android.gms.ads.AdRequest

interface HMSAdRequest {
    fun getAdRequest(): AdRequest
}