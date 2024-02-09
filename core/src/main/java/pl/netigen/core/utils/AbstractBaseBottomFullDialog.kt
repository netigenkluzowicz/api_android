package pl.netigen.core.utils

import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import pl.netigen.core.R

open class AbstractBaseBottomFullDialog : BaseDialogFragment() {

    override fun setDialogSize(widthDp: Int, window: Window) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.BOTTOM)
    }

    override val dialogStyle: Int = R.style.CoffeeDialogCloseOutside
}
