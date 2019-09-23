package pl.netigen.legacy.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CanvasCircle extends CanvasObjectDrawing {
    private float radius;

    public CanvasCircle() {
        super();
        getPaint().setStrokeWidth(0f);
        getPaint().setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getCanvasX(), getCanvasY(), radius, getPaint());
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
