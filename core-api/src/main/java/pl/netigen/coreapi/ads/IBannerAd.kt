package pl.netigen.coreapi.ads

import android.widget.RelativeLayout

interface IBannerAd : IAd {
    val bannerRelativeLayout: RelativeLayout

    fun getHeightInPixels(): Int
}