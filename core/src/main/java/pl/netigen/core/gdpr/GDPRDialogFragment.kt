package pl.netigen.gdpr

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_gdpr.*
import pl.netigen.core.R
import pl.netigen.core.gdpr.ConstRodo

class GDPRDialogFragment : AppCompatDialogFragment() {

    private var isNoAdsAvailable = false
    private var gdprClickListener: GDPRClickListener? = null
    private var admobText: Boolean = false

    override fun onStart() {
        super.onStart()
        setDialogSize(0.9, 0.9)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (dialog != null) {
            val window = dialog?.window
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE)
                dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
        return inflater.inflate(R.layout.dialog_fragment_gdpr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity == null) {
            dismiss()
            return
        }
        setIcon()

        appNameTextViewGdpr.text = getApplicationName(activity!!)
        setButtons()
        showGDPRText()
    }

    private fun setButtons() {
        buttonYes.setOnClickListener { v -> gdprClickListener?.clickYes() }
        buttonNo.setOnClickListener { v ->
            gdprClickListener!!.clickNo()
            showPrivacyPolicy()
        }
        buttonBack.setOnClickListener { v ->
            showAdmobText()
        }
        if (isNoAdsAvailable) {
            buttonPay.setOnClickListener { v -> gdprClickListener?.clickPay() }
        } else {
            buttonPay.visibility = View.GONE
        }
        buttonPolicy.setOnClickListener { v -> gdprClickListener?.clickAcceptPolicy() }
    }

    private fun setIcon() {
        try {
            val icon = activity?.packageManager?.getApplicationIcon(activity!!.packageName)
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
        if (isNetworkOn()) {
            offlinePrivacyPolicyTextView.visibility = View.GONE
            webViewGdpr.visibility = View.VISIBLE
            webViewGdpr.loadUrl(NETIGEN_PRIVACY_FOR_PACKAGE_NAME_URL + getApplicationName(context!!))
        } else {
            webViewGdpr.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            setOfflineText()
        }
    }

    private fun isNetworkOn(): Boolean {
        if (context == null) {
            return false
        }
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                ?: return false
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun setOfflineText() {
        offlinePrivacyPolicyTextView.text = ""
        val ss1 = SpannableString(ConstRodo.text1)
        ss1.setSpan(StyleSpan(Typeface.BOLD), 0, ss1.length, 0)
        val ss2 = SpannableString(ConstRodo.text3)
        ss2.setSpan(StyleSpan(Typeface.BOLD), 0, ss2.length, 0)
        offlinePrivacyPolicyTextView.append(ss1)
        offlinePrivacyPolicyTextView.append(ConstRodo.text2 + "\n")
        offlinePrivacyPolicyTextView.append(ss2)
        offlinePrivacyPolicyTextView.append(ConstRodo.text4 + "\n")
        offlinePrivacyPolicyTextView.append(ConstRodo.text5 + "\n")
    }

    private fun showPrivacyPolicy() {
        if (isNoAdsAvailable) {
            buttonPay.visibility = View.GONE
        }
        buttonYes.visibility = View.GONE
        buttonNo.visibility = View.GONE
        buttonPolicy.visibility = View.VISIBLE
        buttonBack.visibility = View.VISIBLE
        admobText = false
        if (isNetworkOn()) {
            offlinePrivacyPolicyTextView.visibility = View.GONE
            webViewGdpr.visibility = View.VISIBLE
            webViewGdpr.loadUrl(NETIGEN_PRIVACY_MOBILE_URL)
        } else {
            webViewGdpr.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            onNoInternetConnection()
        }
    }

    private fun onNoInternetConnection() {
        offlinePrivacyPolicyTextView.text = ""
        offlinePrivacyPolicyTextView.append(ConstRodo.textPolicy1 + "\n")
        offlinePrivacyPolicyTextView.append(ConstRodo.textPolicy2)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GDPRClickListener) {
            gdprClickListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement InitAdmobAds")
        }
    }

    override fun onDetach() {
        super.onDetach()
        gdprClickListener = null
    }

    fun showAdmobText() {
        if (!admobText) {
            showGDPRText()
        }
    }

    interface GDPRClickListener {
        fun clickYes()

        fun clickNo()

        fun clickPay()

        fun clickAcceptPolicy()
    }

    companion object {

        private const val NETIGEN_PRIVACY_FOR_PACKAGE_NAME_URL = "https://www.netigen.pl/privacy/only-for-mobile-apps-name?app="
        private const val NETIGEN_PRIVACY_MOBILE_URL = "https://www.netigen.pl/privacy/only-for-mobile-apps"

        fun newInstance(): GDPRDialogFragment {
            val dialogFragment = GDPRDialogFragment()
            dialogFragment.isCancelable = false
            return dialogFragment
        }
    }


}