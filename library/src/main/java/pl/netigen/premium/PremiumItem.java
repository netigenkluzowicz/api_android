package pl.netigen.premium;

public class PremiumItem {
    private int drawable;
    private String title;
    private String note;

    public PremiumItem(int drawable, String title, String note) {
        this.drawable = drawable;
        this.title = title;
        this.note = note;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
