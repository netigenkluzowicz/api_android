package pl.netigen.legacy.views;

import android.view.MotionEvent;
import android.view.View;

public class TouchHelper {
    public static boolean isTouchInBounds(MotionEvent motionEvent, View view, int pointerIndex) {
        float x = motionEvent.getX(pointerIndex);
        float y = motionEvent.getY(pointerIndex);
        return x >= 0 && y >= 0 && x <= view.getWidth() && y <= view.getHeight();
    }
}
