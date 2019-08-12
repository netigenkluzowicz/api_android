package pl.netigen.rodo

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.*
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_gdpr.*
import pl.netigen.netigenapi.R

class GDPRDialogFragment : AppCompatDialogFragment() {

    var isNoAdsAvailable = false

    val HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS_NAME_APP = "https://www.netigen.pl/privacy/only-for-mobile-apps-name?app="
    val HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS = "https://www.netigen.pl/privacy/only-for-mobile-apps"

    private var callback: ClickListener? = null
    private var admobText: Boolean = false

    override fun onStart() {
        super.onStart()
        setDialogSize(0.9, 0.9)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (dialog != null) {
            val window = dialog.window
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE)
                dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
        addTextAdmob()
    }

    private fun setButtons() {
        buttonYes.setOnClickListener { v -> callback?.clickYes() }
        buttonNo.setOnClickListener { v ->
            callback!!.clickNo()
            addPolityText()
        }
        buttonBack.setOnClickListener { v ->
            showAdmobText()
        }
        if (isNoAdsAvailable) {
            buttonPay.setOnClickListener { v -> callback?.clickPay() }
        } else {
            buttonPay.visibility = View.GONE
        }
        buttonPolicy.setOnClickListener { v -> callback?.clickAcceptPolicy() }
    }

    private fun setIcon() {
        try {
            val icon = activity?.packageManager?.getApplicationIcon(activity!!.packageName)
            appIconImageViewGdpr.setImageDrawable(icon)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    fun getApplicationName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }

    fun setIsPayOptions(isNoAdsAvailable: Boolean) {
        this.isNoAdsAvailable = isNoAdsAvailable
    }

    fun isNetworkOn(): Boolean {
        val context = context
        val cm: ConnectivityManager?
        if (context == null) {
            return false
        }
        cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo?
        if (cm == null) {
            return false
        }
        netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun addTextAdmob() {
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
            webViewGdpr.loadUrl(HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS_NAME_APP + getApplicationName(context!!))
        } else {
            webViewGdpr.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            noInternetAdmob()
    private fun isNetworkOn(): Boolean {
        if (context == null) {
            return false
        }
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                ?: return false
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun noInternetAdmob() {
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

    private fun addPolityText() {
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
            webViewGdpr.loadUrl(HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS)
        } else {
            webViewGdpr.visibility = View.GONE
            offlinePrivacyPolicyTextView.visibility = View.VISIBLE
            noInternetPolicy()
        }
    }

    private fun noInternetPolicy() {
        offlinePrivacyPolicyTextView.text = ""
        offlinePrivacyPolicyTextView.append(ConstRodo.textPolicy1 + "\n")
        offlinePrivacyPolicyTextView.append(ConstRodo.textPolicy2)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ClickListener) {
            callback = context
        } else {
            throw RuntimeException(context.toString() + " must implement InitAdmobAds")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    fun showAdmobText() {
        if (!admobText) {
            addTextAdmob()
        }
    }

    interface ClickListener {
        fun clickYes()

        fun clickNo()

        fun clickPay()

        fun clickAcceptPolicy()
    }

    companion object {

        fun newInstance(): GDPRDialogFragment {
            val dialogFragment = GDPRDialogFragment()
            dialogFragment.isCancelable = false
            return dialogFragment
        }
    }


}