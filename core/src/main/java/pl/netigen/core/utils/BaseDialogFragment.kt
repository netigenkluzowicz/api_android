package pl.netigen.core.utils

import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import pl.netigen.core.fragment.NetigenDialogFragment
import pl.netigen.extensions.toPx

/**
 * see [NetigenDialogFragment]
 *
 */
abstract class BaseDialogFragment : NetigenDialogFragment() {

    companion object {
        private const val DIALOG_WIDTH_DP_PORTRAIT = 280
        private const val DIALOG_WIDTH_DP_LANDSCAPE = 420
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, dialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        manageDialogSize()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDialogSize(dp: Int) {
        dialog?.window?.let {
            setDialogSize(dp, it)
        }
    }

    open fun setDialogSize(widthDp: Int, window: Window) {
        val width = widthDp.toPx()
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }

    private fun manageDialogSize() {
        val resources = context?.resources

        if (resources != null) {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> setDialogSize(DIALOG_WIDTH_DP_LANDSCAPE)
                else -> setDialogSize(DIALOG_WIDTH_DP_PORTRAIT)
            }
        }
    }
}
