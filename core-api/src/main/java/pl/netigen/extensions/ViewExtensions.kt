package pl.netigen.extensions

import android.view.View
import android.view.ViewTreeObserver

fun View.onLayoutMeasured(onGlobalLayout: () -> Any, measureOnlyOnce: Boolean = true): ViewTreeObserver.OnGlobalLayoutListener {
    val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            onGlobalLayout.invoke()
            if (measureOnlyOnce) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    }
    viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    return globalLayoutListener
}
