package pl.netigen.gdpr

import android.graphics.Point
import android.view.Display
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment

fun AppCompatDialogFragment.setDialogSize(widthMultiplier: Double, heightMultiplier: Double) {
    val window = dialog.window
    val size = Point()
    val display: Display
    if (window != null) {
        display = window.windowManager.defaultDisplay
        display.getSize(size)
        val maxWidth = size.x
        val maxHeight = size.y
        if (heightMultiplier == 0.0 && widthMultiplier == 0.0) {
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else if (widthMultiplier != 0.0 && heightMultiplier != 0.0) {
            window.setLayout((maxWidth * widthMultiplier).toInt(), (maxHeight * heightMultiplier).toInt())
        } else if (widthMultiplier != 0.0) {
            window.setLayout((maxWidth * widthMultiplier).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        } else if (heightMultiplier != 0.0) {
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (maxHeight * heightMultiplier).toInt())
        }
        window.setGravity(Gravity.CENTER)
    }
}