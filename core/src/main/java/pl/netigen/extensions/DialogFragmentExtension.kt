package pl.netigen.extensions

import android.graphics.Point
import android.view.Display
import android.view.Gravity
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

fun DialogFragment.setDialogSize(widthMultiplier: Double, heightMultiplier: Double) {
    val window = dialog?.window
    val size = Point()
    val display: Display
    if (window != null) {
        display = window.windowManager.defaultDisplay
        display.getSize(size)
        val maxWidth = size.x
        val maxHeight = size.y
        when {
            heightMultiplier == 0.0 && widthMultiplier == 0.0 -> {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            widthMultiplier != 0.0 && heightMultiplier != 0.0 -> {
                window.setLayout((maxWidth * widthMultiplier).toInt(), (maxHeight * heightMultiplier).toInt())
            }
            widthMultiplier != 0.0 -> {
                window.setLayout((maxWidth * widthMultiplier).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            heightMultiplier != 0.0 -> {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (maxHeight * heightMultiplier).toInt())
            }
        }
        window.setGravity(Gravity.CENTER)
    }
}

fun DialogFragment.setDialogSize(widthDp: Int, heightDp: Int) {
    val window = dialog?.window
    window?.setLayout(widthDp.toPx(), heightDp.toPx())
}


fun DialogFragment.setDialogSizeAsMatchParent() {
    val window = dialog?.window
    if (window != null) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.CENTER)
    }
}