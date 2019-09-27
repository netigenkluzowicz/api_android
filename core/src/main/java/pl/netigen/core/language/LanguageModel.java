package pl.netigen.core.language;

public class LanguageModel {
    private String code;
    private String language;
    private boolean isSelected;

    public LanguageModel(String code, String language, boolean isSelected) {
        this.code = code;
        this.language = language;
        this.isSelected = isSelected;
    }

    public LanguageModel() {

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
