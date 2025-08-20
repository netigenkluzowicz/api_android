package pl.netigen.extensions

import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowMetrics
import androidx.fragment.app.DialogFragment

fun DialogFragment.setDialogSize(widthMultiplier: Double, heightMultiplier: Double) {
    val window: Window = dialog?.window ?: return

    val (maxWidth, maxHeight) = if (Build.VERSION.SDK_INT >= 30) {
        val metrics: WindowMetrics = window.windowManager.currentWindowMetrics
        val bounds = metrics.bounds
        bounds.width() to bounds.height()
    } else {
        val dm = resources.displayMetrics
        dm.widthPixels to dm.heightPixels
    }

    when {
        heightMultiplier == 0.0 && widthMultiplier == 0.0 -> {
            window.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        widthMultiplier != 0.0 && heightMultiplier != 0.0 -> {
            window.setLayout(
                (maxWidth * widthMultiplier).toInt(),
                (maxHeight * heightMultiplier).toInt()
            )
        }
        widthMultiplier != 0.0 -> {
            window.setLayout(
                (maxWidth * widthMultiplier).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        else /* heightMultiplier != 0.0 */ -> {
            window.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                (maxHeight * heightMultiplier).toInt()
            )
        }
    }
    window.setGravity(Gravity.CENTER)
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
