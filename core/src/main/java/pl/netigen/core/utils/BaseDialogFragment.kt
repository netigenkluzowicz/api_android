package pl.netigen.core.utils

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
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
        setDialogWindow()
        super.onCreate(savedInstanceState)
    }

    private fun setDialogWindow() {
        val dialog = dialog
        if (dialog == null || dialog.window == null) return
        else {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onStart() {
        super.onStart()
        manageDialogSize()
    }

    protected fun setDialogSize(dp: Int) {
        dialog?.window?.let {
            val width = dp.toPx()
            it.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }
    }

    private fun manageDialogSize() {
        val resources = context?.resources

        if (resources != null)
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> setDialogSize(DIALOG_WIDTH_DP_LANDSCAPE)
                else -> setDialogSize(DIALOG_WIDTH_DP_PORTRAIT)
            }
    }
}