package pl.netigen.legacy.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;


public abstract class CanvasObjectDrawing {
    private float canvasHeight;
    private float canvasWidth;
    private float factorPositionX;
    private float factorPositionY;
    private Paint paint;

    public CanvasObjectDrawing() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1f);
        paint.setFilterBitmap(true);
        paint.setDither(true);
    }

    public void setRelativeCanvasPosition(float canvasWidth, float canvasHeight, float factorPositionX, float factorPositionY) {
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        this.factorPositionX = factorPositionX;
        this.factorPositionY = factorPositionY;
    }

    public abstract void draw(Canvas canvas);

    public void setColor(int color) {
        getPaint().setColor(color);
    }

    protected float getCanvasY() {
        return getCanvasHeight() * getFactorPositionY();
    }

    protected float getCanvasX() {
        return getCanvasWidth() * getFactorPositionX();
    }

    protected float getCanvasHeight() {
        return canvasHeight;
    }

    protected float getCanvasWidth() {
        return canvasWidth;
    }

    protected float getFactorPositionX() {
        return factorPositionX;
    }

    protected float getFactorPositionY() {
        return factorPositionY;
    }

    protected Paint getPaint() {
        return paint;
    }
}
