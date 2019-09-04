package pl.netigen.language;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.netigen.netigenapi.R;

public class ChangeLanguageDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = "ChangeLanguageDialog";

    private AppCompatTextView textViewChooseLanguageTitle;
    private RecyclerView recyclerViewLanguages;
    private Button buttonChangeLanguageOk;
    private Button buttonChangeLanguageDismiss;

    private List<String> languageCodes;
    private String selectedLanguageCode;
    private LanguageClickListener languageClickListener;
    private LanguagesRecyclerViewAdapter languagesRecyclerViewAdapter;
    private ArrayList<LanguageModel> languageModels;
    private ChangeLanguageParams changeLanguageParams;

    public ChangeLanguageDialogFragment() {

    }

    private static ChangeLanguageDialogFragment newInstance(LanguageClickListener languageClickListener, List<String> languageCodes, ChangeLanguageParams changeLanguageParams) {
        ChangeLanguageDialogFragment fragment = new ChangeLanguageDialogFragment();
        fragment.bindListener(languageClickListener);
        fragment.languageCodes = languageCodes;
        fragment.changeLanguageParams = changeLanguageParams;
        return fragment;
    }

    private static ChangeLanguageDialogFragment newInstance(LanguageClickListener languageClickListener, String jsonLanguageCodes, ChangeLanguageParams changeLanguageParams) {
        ChangeLanguageDialogFragment fragment = new ChangeLanguageDialogFragment();
        fragment.bindListener(languageClickListener);
        fragment.setLanguageCodesList(jsonLanguageCodes);
        fragment.changeLanguageParams = changeLanguageParams;
        return fragment;
    }

    private void setLanguageCodesList(String jsonLanguageCodes) {
        Gson gson = new Gson();
        languageCodes = gson.fromJson(jsonLanguageCodes, new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    public void bindListener(LanguageClickListener languageClickListener) {
        this.languageClickListener = languageClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (changeLanguageParams.languageCodes != null) {
            languageCodes = changeLanguageParams.languageCodes;
        } else if (changeLanguageParams.jsonLanguageCodes != null) {
            setLanguageCodesList(changeLanguageParams.jsonLanguageCodes);
        } else {
            Log.e(TAG, "onCreate: Problem loading languages, seems like you didn't pass any");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Window window = getDialog().getWindow();

        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);

        View view = inflater.inflate(R.layout.dialog_fragment_change_language, container, false);

        if (changeLanguageParams == null || languageClickListener == null) {
            dismiss();
            return view;
        }

        recyclerViewLanguages = view.findViewById(R.id.recyclerView_languages);

        setTitle(view);

        setPossitiveButton(view);

        setNegativeButton(view);

        setLanguageModelsArrayList();

        initiateList();

        setPositiveButton();
        return view;
    }

    private void setNegativeButton(View view) {
        buttonChangeLanguageDismiss = view.findViewById(R.id.button_change_language_dismiss);
        buttonChangeLanguageDismiss.setText(changeLanguageParams.negativeButtonResId);
        buttonChangeLanguageDismiss.setOnClickListener(v -> getDialog().dismiss());
    }

    private void setPossitiveButton(View view) {
        buttonChangeLanguageOk = view.findViewById(R.id.button_change_language_ok);
        buttonChangeLanguageOk.setText(changeLanguageParams.positiveButtonResId);
        buttonChangeLanguageOk.setOnClickListener(v -> {
            buttonChangeLanguageOk.setText(android.R.string.ok);
            selectedLanguageCode = languagesRecyclerViewAdapter.getSelectedItem();
            languageClickListener.onOkClicked(selectedLanguageCode);
            getDialog().dismiss();
        });
    }

    private void setTitle(View view) {
        textViewChooseLanguageTitle = view.findViewById(R.id.textView_choose_language_title);
        textViewChooseLanguageTitle.setText(changeLanguageParams.titleResId);
    }

    private void setPositiveButton() {
        if (languageCodes.contains(Locale.getDefault().getLanguage())) {
            buttonChangeLanguageOk.setText(getString(android.R.string.ok));
        } else {
            buttonChangeLanguageOk.setText(getString(R.string.ok_netigen));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize(0.55, 0.75);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void initiateList() {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "roboto.ttf");
        languagesRecyclerViewAdapter = new LanguagesRecyclerViewAdapter(languageModels, font);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewLanguages.setAdapter(languagesRecyclerViewAdapter);
        recyclerViewLanguages.setLayoutManager(layoutManager);
        recyclerViewLanguages.setItemViewCacheSize(100);
    }

    private void setLanguageModelsArrayList() {
        languageModels = new ArrayList<>();
        for (int i = 0; i < languageCodes.size(); i++) {
            Locale nextLocale = new Locale(languageCodes.get(i));
            String name = nextLocale.getDisplayName(nextLocale);
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            languageModels.add(new LanguageModel(languageCodes.get(i), name, false));
        }
    }

    public void setDialogSize(double heightMultiplier, double widthMultiplier) {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int maxWidth = size.x;
            int maxHeight = size.y;
            if (heightMultiplier == 0.0 && widthMultiplier == 0.0) {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (widthMultiplier != 0.0 && heightMultiplier != 0.0) {
                window.setLayout((int) (maxWidth * widthMultiplier), (int) (maxHeight * heightMultiplier));
            } else if (widthMultiplier != 0.0) {
                window.setLayout((int) (maxWidth * widthMultiplier), ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (maxHeight * heightMultiplier));
            }
            window.setGravity(Gravity.CENTER);
        }
    }

    public interface LanguageClickListener {
        void onOkClicked(String selectedLanguageCode);
    }

    public static class Builder {

        private ChangeLanguageParams changeLanguageParams;
        private AppCompatActivity appCompatActivity;

        public Builder(AppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
            this.changeLanguageParams = new ChangeLanguageParams();
        }

        public Builder setTitleResId(int titleResId) {
            changeLanguageParams.titleResId = titleResId;
            return this;
        }

        public Builder setNegativeButtonResId(int negativeButtonResId) {
            changeLanguageParams.negativeButtonResId = negativeButtonResId;
            return this;
        }

        public Builder setPositiveButtonResId(int positiveButtonResId) {
            changeLanguageParams.positiveButtonResId = positiveButtonResId;
            return this;
        }

        public Builder setJsonLanguageCodes(String jsonLanguageCodes) {
            changeLanguageParams.jsonLanguageCodes = jsonLanguageCodes;
            return this;
        }

        public Builder setLanguageCodes(List<String> languageCodes) {
            changeLanguageParams.languageCodes = languageCodes;
            return this;
        }

        public Builder setLanguageClickListener(ChangeLanguageDialogFragment.LanguageClickListener languageClickListener) {
            changeLanguageParams.languageClickListener = languageClickListener;
            return this;
        }

        public ChangeLanguageDialogFragment create() {
            if (changeLanguageParams.languageCodes != null) {
                return ChangeLanguageDialogFragment.newInstance(changeLanguageParams.languageClickListener, changeLanguageParams.languageCodes, changeLanguageParams);
            } else {
                return ChangeLanguageDialogFragment.newInstance(changeLanguageParams.languageClickListener, changeLanguageParams.jsonLanguageCodes, changeLanguageParams);
            }
        }

        public void show() {
            create().show(appCompatActivity.getSupportFragmentManager(), "change_language");
        }
    }
}
