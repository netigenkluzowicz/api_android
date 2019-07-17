package pl.netigen.rewards;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

public class LayoutMargins {

    private Integer left;
    private Integer top;
    private Integer right;
    private Integer bottom;

    private LayoutMargins() {
    }

    void setNewLayoutParamsForView(View view){
        ConstraintLayout.MarginLayoutParams layoutParams = (ConstraintLayout.MarginLayoutParams) view.getLayoutParams();
        if(left!=null){
            layoutParams.leftMargin = left;
        }
        if(right!=null){
            layoutParams.rightMargin = right;
        }
        if(top!=null){
            layoutParams.topMargin = top;
        }
        if(bottom!=null){
            layoutParams.bottomMargin = bottom;
        }
    }

    public static class Builder {

        private LayoutMargins layoutMargins;

        public Builder() {
            layoutMargins = new LayoutMargins();
        }

        public Builder setLeftMargin(Integer left) {
            layoutMargins.left = left;
            return this;
        }

        public Builder setTopMargin(Integer top) {
            layoutMargins.top = top;
            return this;
        }

        public Builder setRightMargin(Integer right) {
            layoutMargins.right = right;
            return this;
        }

        public Builder setBottomMargin(Integer bottom) {
            layoutMargins.bottom = bottom;
            return this;
        }

        public LayoutMargins create(){
            return this.layoutMargins;
        }

    }
}