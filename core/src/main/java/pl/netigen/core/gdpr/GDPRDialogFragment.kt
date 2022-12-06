package pl.netigen.core.gdpr

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.dialog_fragment_gdpr_netigen_api.*
import pl.netigen.core.R
import pl.netigen.core.fragment.NetigenDialogFragment
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.gdpr.GDPRClickListener
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.Store
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.setDialogSizeAsMatchParent
import pl.netigen.extensions.setTint
import timber.log.Timber

/**
 * Fragment for showing GDPR user consent, see [IGDPRConsent]
 *
 */
class GDPRDialogFragment : NetigenDialogFragment() {
    private val splashVM: ISplashVM by activityViewModels<SplashVM> { (requireActivity() as CoreMainActivity).viewModelFactory }

    companion object {
        private const val NETIGEN_PRIVACY_FOR_PACKAGE_NAME_URL = "https://www.netigen.pl/privacy/only-for-mobile-apps-name?app="
        private const val NETIGEN_APP_COLOR = "&color="
        private const val INSIDE_WEB_VIEW_MARGIN_0 = "&containerPadding=0&bodyMargin=0"
        private const val NETIGEN_PRIVACY_MOBILE_URL = "https://www.netigen.pl/privacy/only-for-mobile-apps?app=2"

        fun newInstance(): GDPRDialogFragment {
            val dialogFragment = GDPRDialogFragment()
            dialogFragment.isCancelable = false
            return dialogFragment
        }
    }

    private var isNoAdsAvailable = false
    private var gdprClickListener: GDPRClickListener? = null
    private var admobText: Boolean = false
    private var webViewGdpr: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (dialog != null) {
            val window = dialog?.window
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE)
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }

        val view = inflater.inflate(R.layout.dialog_fragment_gdpr_netigen_api, container, false)
        createWebView(view)
        return view
    }

    private fun createWebView(view: View) {
        activity?.let {
            webViewGdpr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                WebView(it.createConfigurationContext(Configuration()))
            } else {
                WebView(it.applicationContext)
            }
        }
        webViewGdpr?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return try {
                    activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    true
                } catch (e: Exception) {
                    false
                }
            }
        }
        view.findViewById<FrameLayout>(R.id.containerGDPRInfo).addView(webViewGdpr)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity == null) {
            dismissAllowingStateLoss()
            return
        }
        setIcon()

        appNameTextViewGdpr.text = getApplicationName(requireActivity())
        setButtons()
        showGDPRText()
    }

    override fun onStart() {
        super.onStart()
        setDialogSizeAsMatchParent()
        setButtonsBackgroundTints()
    }

    private fun setButtonsBackgroundTints() {
        context?.let {
            buttonYes.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)
            buttonPolicy.background.setTint(it, R.color.dialog_accent, PorterDuff.Mode.MULTIPLY)

            buttonNo.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
            buttonPay.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
            buttonBack.background.setTint(it, R.color.dialog_neutral_button_bg, PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setButtons() {
        buttonYes.setOnClickListener {
            gdprClickListener?.onConsentAccepted(true)
            dismissAllowingStateLoss()
        }
        buttonNo.setOnClickListener {
            showPrivacyPolicy()
        }
        buttonBack.setOnClickListener {
            showAdmobText()
        }
        if (isNoAdsAvailable) {
            buttonPay.visibility = View.VISIBLE
            buttonPay.setOnClickListener { gdprClickListener?.clickPay() }
        } else {
            buttonPay.visibility = View.GONE
        }
        buttonPolicy.setOnClickListener {
            gdprClickListener?.onConsentAccepted(false)
            dismissAllowingStateLoss()
        }
    }

    private fun setIcon() {
        try {
            val icon = activity?.packageManager?.getApplicationIcon(requireActivity().packageName)
            appIconImageViewGdpr.setImageDrawable(icon)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getApplicationName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }

    fun setIsPayOptions(isNoAdsAvailable: Boolean) {
        this.isNoAdsAvailable = isNoAdsAvailable
        if (isAdded) {
            setButtons()
        }
    }

    private fun showGDPRText() {
        if (isNoAdsAvailable) {
            buttonPay.visibility = View.VISIBLE
        }
        buttonNo.visibility = View.VISIBLE
        buttonYes.visibility = View.VISIBLE
        buttonPolicy.visibility = View.GONE
        buttonBack.visibility = View.GONE

        admobText = true
        if (showOnlineVersion()) {
            offlinePrivacyPolicyTextView.visibility = View.GONE
            webViewGdpr?.visibility = View.VISIBLE
            webViewGdpr?.loadUrl(getLinkForPrivacy())
        } else {
            webViewGdpr?.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            setOfflineText()
            setScrollToOfflinePolicy()
        }
    }

    private fun setOfflineText() {
        offlinePrivacyPolicyTextView.text = ""
        val ss1 = SpannableString(splashVM.gdprConsent.text1)
        ss1.setSpan(StyleSpan(Typeface.BOLD), 0, ss1.length, 0)
        val ss2 = SpannableString(splashVM.gdprConsent.text3)
        ss2.setSpan(StyleSpan(Typeface.BOLD), 0, ss2.length, 0)
        offlinePrivacyPolicyTextView.append(ss1)
        offlinePrivacyPolicyTextView.append(splashVM.gdprConsent.text2 + "\n")
        offlinePrivacyPolicyTextView.append(ss2)
        offlinePrivacyPolicyTextView.append(splashVM.gdprConsent.text4 + "\n")
        offlinePrivacyPolicyTextView.append(splashVM.gdprConsent.text5 + "\n")
    }

    private fun showPrivacyPolicy() {
        if (isNoAdsAvailable) {
            buttonPay.visibility = View.INVISIBLE
        }
        buttonYes.visibility = View.GONE
        buttonNo.visibility = View.INVISIBLE
        buttonPolicy.visibility = View.VISIBLE
        buttonBack.visibility = View.VISIBLE
        admobText = false
        if (showOnlineVersion()) {
            offlinePrivacyPolicyTextView.visibility = View.GONE
            webViewGdpr?.visibility = View.VISIBLE
            val linkForMobiles = getLinkForMobiles()
            Timber.d(": %s", linkForMobiles)
            webViewGdpr?.loadUrl(linkForMobiles)
        } else {
            webViewGdpr?.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            setScrollToOfflinePolicy()
            onNoInternetConnection()
        }
    }

    private fun showOnlineVersion() = viewModel.isConnectedOrConnecting && splashVM.store != Store.HUAWEI

    private fun onNoInternetConnection() {
        offlinePrivacyPolicyTextView.text = ""
        offlinePrivacyPolicyTextView.append(splashVM.gdprConsent.textPolicy1 + "\n")
        offlinePrivacyPolicyTextView.append(splashVM.gdprConsent.textPolicy2)
    }

    private fun setScrollToOfflinePolicy() {
        offlinePrivacyPolicyTextView.movementMethod = ScrollingMovementMethod()
    }

    override fun onDetach() {
        super.onDetach()
        gdprClickListener = null
    }

    private fun showAdmobText() {
        if (!admobText) {
            showGDPRText()
        }
    }

    fun bindGDPRListener(gdprClickListener: GDPRClickListener) {
        this.gdprClickListener = gdprClickListener
    }

    private fun getLinkForPrivacy(): String {
        var link = NETIGEN_PRIVACY_FOR_PACKAGE_NAME_URL

        context?.let {
            val netigenApiAccentColor =
                String.format("#%06x", ContextCompat.getColor(it, R.color.dialog_accent) and 0xffffff).replace("#", "")
            link =
                NETIGEN_PRIVACY_FOR_PACKAGE_NAME_URL + getApplicationName(it) + NETIGEN_APP_COLOR + netigenApiAccentColor + INSIDE_WEB_VIEW_MARGIN_0
        }

        return link
    }

    private fun getLinkForMobiles() = NETIGEN_PRIVACY_MOBILE_URL + INSIDE_WEB_VIEW_MARGIN_0
}
