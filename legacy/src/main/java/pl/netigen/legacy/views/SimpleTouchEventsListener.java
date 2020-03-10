package pl.netigen.legacy.views;


public interface SimpleTouchEventsListener {
    void onPress(float x, float y);

    void onRelease(float x, float y);

    void onClick();
}
