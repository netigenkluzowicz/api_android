package pl.netigen.core.ads

import android.widget.RelativeLayout

interface IBannerAd {
    var bannerLayout: RelativeLayout?
    var enabled: Boolean
}