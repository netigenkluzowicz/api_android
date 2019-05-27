package pl.netigen.views;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * This class represents the basic building block for drawing dynamic views.
 * <h1>Implementing</h1>
 * <p>{@link #onNonZeroCanvasSizeChanged()} This called always when view size was changed. Use for measure Your drawings here.</p>
 * <p>{@link #initNormal(AttributeSet attrs)} Canvas size here is zero. Use for initialization when size is not needed, this is called in constructor when view is not in edit mode.</p>
 * <p>{@link #initInEditMode(AttributeSet attrs)} Canvas size here is zero. Use for initialization in editor mode.</p>
 * <p>{@link #drawNormal(Canvas canvas)} Canvas size here is zero. Use for initialization in editor mode.</p>
 */
public abstract class DynamicDrawingView extends View {
    private float canvasWidth;
    private float canvasHeight;
    private boolean isZeroSize;

    /**
     * Simple constructor to use when creating a view from code.
     * <br>
     * Calls {@link #initNormal(AttributeSet)} where You should make Your initialisation.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     */
    public DynamicDrawingView(Context context) {
        super(context);
        if (!isInEditMode()) {
            initNormal(null);
        } else {
            initInEditMode(null);
        }
    }

    /**
     * Constructor that is called when inflating a view from XML.
     * This is called when a view is being constructed from an XML file, supplying attributes that were specified in the XML file.
     * This version uses a default style of 0, so the only attribute values applied are those in the Context's Theme and the given AttributeSet.
     * <br>
     * Calls {@link #initNormal(AttributeSet)} where You should make Your initialisation.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs   AttributeSet: The attributes of the XML tag that is inflating the view.
     */
    public DynamicDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initNormal(null);
        } else {
            initInEditMode(attrs);
        }
    }

    /**
     * This method is called in constructor. Use this for initialization.
     *
     * @param attrs AttributeSet: The attributes of the XML tag that is inflating the view.
     */
    protected abstract void initNormal(@Nullable AttributeSet attrs);

    /**
     * This is called when constructor is called in edit mode
     *
     * @param attrs The attributes of the XML tag that is inflating the view.
     */
    protected void initInEditMode(@Nullable AttributeSet attrs) {

    }

    /**
     * This is called when canvasWidth or canvasHeight changes
     * You should recalculate sizes of drawing objects in this methods
     */
    protected abstract void onNonZeroCanvasSizeChanged();

    private void checkIsCanvasSizeChanged(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        if (width != canvasWidth || height != canvasHeight) {
            canvasWidth = width;
            canvasHeight = height;
            if (canvasWidth > 0 && canvasHeight > 0) {
                isZeroSize = false;
                onNonZeroCanvasSizeChanged();
            } else {
                isZeroSize = true;
            }
        }
    }

    /**
     * @return current canvasWidth
     */
    public float getCanvasWidth() {
        return canvasWidth;
    }

    /**
     * @return current canvasHeight
     */
    public float getCanvasHeight() {
        return canvasHeight;
    }

    /**
     * This method checks if canvas size was changed and invokes {@link #onNonZeroCanvasSizeChanged()} then calls {@link #drawNormal(Canvas)}
     * or {@link #drawInEditMode(Canvas)}
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        checkIsCanvasSizeChanged(canvas);
        if (!isZeroSize) {
            if (!isInEditMode()) {
                drawNormal(canvas);
            } else {
                drawInEditMode(canvas);
            }
        }
    }

    /**
     * This is called when draw is making on device or emulator (not in editor)
     * Implement this to do your drawing
     *
     * @param canvas the canvas on which the background will be drawn
     */
    protected abstract void drawNormal(Canvas canvas);

    /**
     * This called when draw is making in editor
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    protected void drawInEditMode(Canvas canvas) {

    }

}
