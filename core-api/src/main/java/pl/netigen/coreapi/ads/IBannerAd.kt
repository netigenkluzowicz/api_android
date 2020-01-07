package pl.netigen.coreapi.ads

import android.content.Context
import android.widget.RelativeLayout

interface IBannerAd : IAd {
    fun getHeightInPixels(context: Context): Int

    val bannerRelativeLayout: RelativeLayout
}