package pl.netigen.views;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleTouchEventsDetector implements View.OnTouchListener {
    private final List<Integer> activePointsCount = new ArrayList<>();
    private SimpleTouchEventsListener simpleTouchEventsListener;
    private TouchState touchState;

    public SimpleTouchEventsDetector(SimpleTouchEventsListener simpleTouchEventsListener) {
        this.simpleTouchEventsListener = simpleTouchEventsListener;
    }

    private boolean checkViewState(MotionEvent motionEvent, View view) {
        int maskedAction = motionEvent.getActionMasked();
        int pointerIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(pointerIndex);
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                return actionDown(motionEvent, pointerIndex, pointerId);
            }
            case MotionEvent.ACTION_MOVE: {
                return actionMove(motionEvent, view, pointerIndex, pointerId);
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                return actionUp(motionEvent, view, pointerIndex, pointerId);
            }
            case MotionEvent.ACTION_CANCEL:
                return actionCancel(pointerId);
        }
        return true;
    }

    private boolean actionCancel(int pointerId) {
        if (pointerId >= 0 && pointerId < activePointsCount.size()) {
            activePointsCount.remove(pointerId);
        }
        return true;
    }

    private boolean actionUp(MotionEvent motionEvent, View view, int pointerIndex, Integer pointerId) {
        activePointsCount.remove(pointerId);
        if (activePointsCount.size() == 0 && touchState == TouchState.PRESSED) {
            releaseTouch(motionEvent, pointerIndex);
            if (TouchHelper.isTouchInBounds(motionEvent, view, pointerIndex)) {
                if (simpleTouchEventsListener != null) {
                    simpleTouchEventsListener.onClick();
                }
            }
        }
        return true;
    }

    private boolean actionMove(MotionEvent motionEvent, View view, int pointerIndex, Integer pointerId) {
        if (!TouchHelper.isTouchInBounds(motionEvent, view, pointerIndex)) {
            activePointsCount.remove(pointerId);
            if (activePointsCount.size() == 0 && touchState == TouchState.PRESSED) {
                releaseTouch(motionEvent, pointerIndex);
            }
        }
        return true;
    }

    private boolean actionDown(MotionEvent motionEvent, Integer pointerIndex, int pointerId) {
        if (touchState != TouchState.PRESSED) {
            touchState = TouchState.PRESSED;
            if (simpleTouchEventsListener != null) {
                simpleTouchEventsListener.onPress(motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex));
            }
        }
        activePointsCount.add(pointerId);
        return true;
    }

    private void releaseTouch(MotionEvent motionEvent, Integer pointerIndex) {
        if (simpleTouchEventsListener != null) {
            simpleTouchEventsListener.onRelease(motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex));
        }
        touchState = TouchState.RELEASED;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return checkViewState(event, view);
    }

    public void setSimpleTouchEventsListener(SimpleTouchEventsListener simpleTouchEventsListener) {
        this.simpleTouchEventsListener = simpleTouchEventsListener;
    }

    public enum TouchState {PRESSED, RELEASED}
}
