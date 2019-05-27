package pl.netigen.views;

import android.graphics.RectF;
import androidx.annotation.NonNull;

public class TouchArea {
    private RectF touchRectF;

    public TouchArea(@NonNull RectF touchRectF) {
        this.touchRectF = touchRectF;
    }

    public TouchArea(float left, float top, float right, float bottom) {
        touchRectF = new RectF();
        touchRectF.set(left, top, right, bottom);
    }

    public boolean isTouched(float x, float y) {
        return touchRectF.contains(x, y);
    }
}
