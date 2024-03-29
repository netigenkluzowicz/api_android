package pl.netigen.core.donate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import pl.netigen.core.R
import pl.netigen.core.newlanguage.ChangeLanguageHelper
import pl.netigen.core.utils.BaseDialogFragment
import pl.netigen.core.utils.Utils
import pl.netigen.coreapi.donate.DonateEvent
import pl.netigen.coreapi.donate.DonateInterface
import pl.netigen.extensions.toPx
import timber.log.Timber

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class DonateFragment : BaseDialogFragment() {

    private lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fragment_donate_webview_netigen_api, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun setDialogSize(widthDp: Int, window: Window) {
        val size630px = 630.toPx()
        val screenHeightPixels = resources.displayMetrics.heightPixels
        val heightPx = if (screenHeightPixels > size630px) size630px else screenHeightPixels
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, heightPx)
        window.setGravity(Gravity.BOTTOM)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUp() {
        webView = requireView().findViewById(R.id.webView)
        val context = webView.context
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(
            DonateInterface(::onNextAction),
            interfaceName,
        )
        val packageName = context.packageName
        val locale = ChangeLanguageHelper.getCurrentAppLocale(context)
        if (donates.size != 3) {
            dismissAllowingStateLoss()
            return
        }
        val apiLink = DONATE_API_LINK
        val price1 = donates[0].priceText
        val price2 = donates[1].priceText
        val price3 = donates[2].priceText
        val url =
            "$apiLink?packageName=$packageName&platform=android&locale=$locale&options[0]=$price1&options[1]=$price2&options[2]=$price3"
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Timber.d("view = [$view], request = [$request], error = [$error]")
                Utils.showShortToast(requireActivity(), requireActivity().getString(R.string.error_donate_not_loaded_netigen))
                dismissAllowingStateLoss()
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                Timber.d("view = [$view], errorCode = [$errorCode], description = [$description], failingUrl = [$failingUrl]")
                Utils.showShortToast(requireActivity(), requireActivity().getString(R.string.error_donate_not_loaded_netigen))
                dismissAllowingStateLoss()
            }

        }
    }

    private fun onNextAction(donateEvent: DonateEvent) {
        when (donateEvent) {
            is DonateEvent.DonateClick -> launchPayment(donateEvent)
            is DonateEvent.Exit -> dismissAllowingStateLoss()
            is DonateEvent.Unknown -> dismissAllowingStateLoss()
        }
    }

    private fun launchPayment(donateEvent: DonateEvent.DonateClick) {
        viewModel.makePurchase(requireActivity(), donates[donateEvent.productIndex].productId)
        dismissAllowingStateLoss()
    }

    companion object {
        private const val DONATE_API_LINK = "https://apis.netigen.eu/donate-webview"
        private const val interfaceName = "Android"

        private var donates = emptyList<Donate.DonateInfo>()

        fun newInstance(data: List<Donate.DonateInfo>): DonateFragment {
            donates = data.sortedBy { it.productIndex }
            return DonateFragment()
        }
    }
}
