package pl.netigen.language;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;

import pl.netigen.netigenapi.R;

public class LanguagesRecyclerViewAdapter extends RecyclerView.Adapter<LanguagesRecyclerViewAdapter.LanguageRowViewHolder> {

    private ArrayList<LanguageModel> languageModelArrayList;
    private RadioButton lastCheckedRB;
    private LanguageModel selectedLanguageModel;
    private Typeface typeface;

    public LanguagesRecyclerViewAdapter(ArrayList<LanguageModel> languageModelArrayList, Typeface typeface) {
        this.typeface = typeface;
        this.languageModelArrayList = languageModelArrayList;
    }

    @NonNull
    @Override
    public LanguageRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
        return new LanguageRowViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return languageModelArrayList.get(position).hashCode();
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageRowViewHolder holder, int position) {
        holder.bind(languageModelArrayList.get(position), typeface);
        holder.radioButtonSelectLanguage.setOnCheckedChangeListener(ls);
        holder.radioButtonSelectLanguage.setTag(position);
    }

    private CompoundButton.OnCheckedChangeListener ls = (new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            int tag = (int) buttonView.getTag();
            selectedLanguageModel = languageModelArrayList.get(tag);
            if (lastCheckedRB == null) {
                lastCheckedRB = (RadioButton) buttonView;
            } else if (tag != (int) lastCheckedRB.getTag()) {
                lastCheckedRB.setChecked(false);
                lastCheckedRB = (RadioButton) buttonView;
            }
        }
    });

    @Override
    public int getItemCount() {
        return languageModelArrayList.size();
    }

    public String getSelectedItem() {
        if (selectedLanguageModel != null) {
            return selectedLanguageModel.getCode();
        }
        return null;
    }

    public static class LanguageRowViewHolder extends RecyclerView.ViewHolder {
        public AppCompatRadioButton radioButtonSelectLanguage;
        private View itemView;

        public LanguageRowViewHolder(View itemView) {
            super(itemView);
            radioButtonSelectLanguage = itemView.findViewById(R.id.radio_button_select_language);
            this.itemView = itemView;
        }

        public void bind(LanguageModel languageModel, Typeface typeface) {
            radioButtonSelectLanguage.setSelected(languageModel.isSelected());
            radioButtonSelectLanguage.setText(languageModel.getLanguage());
            radioButtonSelectLanguage.setTypeface(typeface);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                radioButtonSelectLanguage.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                    },
                    new int[]{
                            itemView.getResources().getColor(R.color.radio_button_color_checked)
                            , itemView.getResources().getColor(R.color.radio_button_color_not_checked)
                    }
            );
            CompoundButtonCompat.setButtonTintList(radioButtonSelectLanguage, colorStateList);
        }
    }

    public interface ItemClickListener {
        public void OnItemClicked(LanguageModel languageModel);
    }
}
