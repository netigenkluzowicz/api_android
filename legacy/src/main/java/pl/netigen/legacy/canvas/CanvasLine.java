package pl.netigen.canvas;

import android.graphics.Canvas;

public class CanvasLine extends CanvasObjectDrawing {
    private float x1, x2;
    private float y1, y2;

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public void setUp(float x1, float x2, float y1, float y2, float thickness) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        getPaint().setStrokeWidth(thickness);
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(x1, y1, x2, y2, getPaint());
    }

}
