package pl.netigen.rodo;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import pl.netigen.netigenapi.R;


public class RodoFragment extends Fragment {
    public static final String HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS_NAME_APP = "https://www.netigen.pl/privacy/only-for-mobile-apps-name?app=";
    public static final String HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS = "https://www.netigen.pl/privacy/only-for-mobile-apps";
    private android.support.v7.widget.AppCompatTextView rodoText;
    private android.support.v7.widget.AppCompatTextView buttonYes;
    private android.support.v7.widget.AppCompatTextView buttonNo;
    private android.support.v7.widget.AppCompatTextView buttonPay;
    private android.support.v7.widget.AppCompatTextView buttonPolicy;
    private WebView webView;
    private ClickListener callback;
    private boolean admobText;
    private boolean isPayOptions;

    public RodoFragment() {
    }

    public static RodoFragment newInstance() {
        return new RodoFragment();
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public void setIsPayOptions(boolean isPayOptions) {
        this.isPayOptions = isPayOptions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        rodoText = view.findViewById(R.id.textRodo1);
        try {
            Drawable icon = getActivity().getPackageManager().getApplicationIcon(getActivity().getPackageName());
            ImageView imageView = view.findViewById(R.id.iconApplication);
            imageView.setImageDrawable(icon);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        android.support.v7.widget.AppCompatTextView appsName = view.findViewById(R.id.appsName);
        appsName.setText(getApplicationName(getContext()));
        buttonYes = view.findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(v -> callback.clickYes());
        buttonNo = view.findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(v -> {
            callback.clickNo();
            addPolityText();
        });
        buttonPay = view.findViewById(R.id.buttonPay);
        if (isPayOptions) {
            buttonPay.setOnClickListener(v -> callback.clickPay());
        } else {
            buttonPay.setVisibility(View.GONE);
        }
        buttonPolicy = view.findViewById(R.id.buttonPolicy);
        buttonPolicy.setOnClickListener(v -> callback.clickAcceptPolicy());
        webView = view.findViewById(R.id.web);
        addTextAdmob();
        return view;
    }


    public boolean isOnline() {
        Context context = getContext();
        ConnectivityManager cm;
        if (context == null) {
            return false;
        }
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        if (cm == null) {
            return false;
        }
        netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void addTextAdmob() {
        if (isPayOptions) {
            buttonPay.setVisibility(View.VISIBLE);
        }
        buttonNo.setVisibility(View.VISIBLE);
        buttonYes.setVisibility(View.VISIBLE);
        buttonPolicy.setVisibility(View.GONE);
        admobText = true;
        if (isOnline()) {
            rodoText.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS_NAME_APP + getApplicationName(getContext()));
        } else {
            webView.setVisibility(View.GONE);
            rodoText.setVisibility(View.VISIBLE);
            noInternetAdmob();
        }
    }

    private void noInternetAdmob() {
        rodoText.setText("");
        SpannableString ss1 = new SpannableString(ConstRodo.text1);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        SpannableString ss2 = new SpannableString(ConstRodo.text3);
        ss2.setSpan(new StyleSpan(Typeface.BOLD), 0, ss2.length(), 0);
        rodoText.append(ss1);
        rodoText.append(ConstRodo.text2 + "\n");
        rodoText.append(ss2);
        rodoText.append(ConstRodo.text4 + "\n");
        rodoText.append(ConstRodo.text5 + "\n");
    }

    private void addPolityText() {
        if (isPayOptions) {
            buttonPay.setVisibility(View.GONE);
        }
        buttonYes.setVisibility(View.GONE);
        buttonNo.setVisibility(View.GONE);
        buttonPolicy.setVisibility(View.VISIBLE);
        admobText = false;
        if (isOnline()) {
            rodoText.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(HTTPS_WWW_NETIGEN_PL_PRIVACY_ONLY_FOR_MOBILE_APPS);
        } else {
            webView.setVisibility(View.GONE);
            rodoText.setVisibility(View.VISIBLE);
            noInternetPolicy();
        }


    }


    private void noInternetPolicy() {
        rodoText.setText("");
        rodoText.append(ConstRodo.textPolicy1 + "\n");
        rodoText.append(ConstRodo.textPolicy2);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClickListener) {
            callback = (ClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InitAdmobAds");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public void showAdmobText() {
        if (!admobText) {
            addTextAdmob();
        }
    }

    public interface ClickListener {
        void clickYes();

        void clickNo();

        void clickPay();

        void clickAcceptPolicy();
    }
}
