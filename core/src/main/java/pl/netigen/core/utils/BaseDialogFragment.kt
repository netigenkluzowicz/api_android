package pl.netigen.core.utils

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import pl.netigen.extensions.toPx

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    companion object {
        private const val DIALOG_WIDTH_DP_PORTRAIT = 280
        private const val DIALOG_WIDTH_DP_LANDSCAPE = 420
    }

    private val activity: AppCompatActivity?
        get() = if (activity == null) null else activity as AppCompatActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogWindow()
    }

    private fun setDialogWindow() {
        dialog?.window?.let { window ->
            window.requestFeature(Window.FEATURE_NO_TITLE)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onStart() {
        super.onStart()
        manageDialogSize()
    }

    private fun setDialogSize(dp: Int) {
        dialog?.window?.let {
            val width = DIALOG_WIDTH_DP_PORTRAIT.toPx()
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

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

}