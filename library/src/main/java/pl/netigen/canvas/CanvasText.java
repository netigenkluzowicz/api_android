package pl.netigen.canvas;

import android.graphics.Canvas;
import android.graphics.Typeface;

import androidx.annotation.Nullable;


public class CanvasText extends CanvasObjectDrawing {
    private String text;
    private float textSize;
    private float textHeight;
    private float textWidth;
    private float textX;
    private float textY;

    public CanvasText(Typeface typeface, int color) {
        setColor(color);
        if (typeface != null) {
            getPaint().setTypeface(typeface);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        getPaint().setTextSize(textSize);
        if (text != null) {
            canvas.drawText(text, textX, textY, getPaint());
        }
    }

    public void setRelativeLettersTextSize(float textSizeFactor) {
        textSize = getCanvasHeight() * textSizeFactor;
        refreshSizes();
    }

    protected void refreshSizes() {
        getPaint().setTextSize(textSize);
        if (text != null) {
            textHeight = CanvasDrawingUtils.getFontPixelsHeight(getPaint(), textSize);
            textWidth = CanvasDrawingUtils.getFontPixelsWidth(getPaint(), text);
            textX = getCanvasX() - textWidth / 2f;
            textY = getCanvasY() + textHeight / 2f;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(@Nullable String text) {
        this.text = text;
        refreshSizes();
    }

    protected float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        refreshSizes();
    }

    protected float getTextHeight() {
        return textHeight;
    }

    protected float getLettersWidth() {
        return textWidth;
    }

    protected float getLettersX() {
        return textX;
    }

    protected float getLettersY() {
        return textY;
    }

    public void clear() {
        text = null;
    }
}
