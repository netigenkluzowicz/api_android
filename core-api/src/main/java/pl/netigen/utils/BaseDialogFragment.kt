package pl.netigen.utils

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    companion object {
        private const val DIALOG_WIDTH_DP = 280
        private const val DIALOG_WIDTH_DP_LANDSCAPE = 410
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogWindow()
    }

    private fun setDialogWindow() {
        dialog?.window?.let { window ->
            window.requestFeature(Window.FEATURE_NO_TITLE)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onStart() {
        super.onStart()
        manageDialogOrientation(resources)
    }

    private fun manageDialogOrientation(resources: Resources) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setDialogSize(DIALOG_WIDTH_DP_LANDSCAPE)
        else
            setDialogSize(DIALOG_WIDTH_DP)
    }


    private fun setDialogSize(dp: Int) {
        dialog?.window?.let {
            val width = convertDpToPixel(dp).toInt()
            it.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
        }
    }

    private fun convertDpToPixel(dp: Int): Float {
        return dp.toFloat() * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
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