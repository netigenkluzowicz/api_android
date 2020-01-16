package pl.netigen.coreapi.ads

import android.widget.RelativeLayout

interface IBannerAd : IAd {
    fun getHeightInPixels(): Int

    val bannerRelativeLayout: RelativeLayout
}