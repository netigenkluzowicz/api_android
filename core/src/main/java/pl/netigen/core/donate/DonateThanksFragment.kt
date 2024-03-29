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
import pl.netigen.coreapi.donate.DonateInterface
import timber.log.Timber

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class DonateThanksFragment : BaseDialogFragment() {

    private lateinit var webView: WebView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fragment_donate_thanks_webview_netigen_api, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun setDialogSize(widthDp: Int, window: Window) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.BOTTOM)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUp() {
        webView = requireView().findViewById(R.id.webView)
        val context = webView.context
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(
            DonateInterface { dismissAllowingStateLoss() },
            interfaceName,
        )
        val packageName = context.packageName
        val locale = ChangeLanguageHelper.getCurrentAppLocale(context)
        val apiLink = DONATE_API_LINK
        val url = "$apiLink?packageName=$packageName&platform=android&locale=$locale&premium=$isPremium"
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Timber.d("view = [$view], request = [$request], error = [$error]")
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                Timber.d("view = [$view], errorCode = [$errorCode], description = [$description], failingUrl = [$failingUrl]")
            }

        }
    }


    companion object {
        private const val DONATE_API_LINK = "https://apis.netigen.eu/donate-webview"
        private const val interfaceName = "Android"
        private var isPremium = false

        fun newInstance(isPremium: Boolean): DonateThanksFragment {
            DonateThanksFragment.isPremium = isPremium
            return DonateThanksFragment()
        }
    }
}
