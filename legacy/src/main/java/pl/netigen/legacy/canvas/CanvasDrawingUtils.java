package pl.netigen.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;


public class CanvasDrawingUtils {
    public static float getFontPixelsHeight(Paint paint, float lettersTextSize) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return lettersTextSize - fontMetrics.descent;
    }

    public static float getFontPixelsWidth(Paint paint, String lettersText) {
        return paint.measureText(lettersText);
    }

    public static void drawCenterXOnCanvas(Canvas canvas, float canvasWidth, float canvasHeight, int white) {
        CanvasLine canvasLine = new CanvasLine();
        canvasLine.setColor(white);
        canvasLine.setUp(0, canvasWidth, 0, canvasHeight, 2f);
        canvasLine.draw(canvas);
        canvasLine.setUp(0, canvasWidth, canvasHeight, 0, 2f);
        canvasLine.draw(canvas);
    }
}
