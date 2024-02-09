package pl.netigen.core.rateus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import pl.netigen.core.R
import pl.netigen.core.utils.AbstractBaseBottomFullDialog
import pl.netigen.core.utils.BaseDialogFragment

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class RateUsFragment : AbstractBaseBottomFullDialog() {
    private var onRateStarsCountClick: ((Int) -> Unit)? = null
    private var onClose: (() -> Unit)? = null

    private var clickedStarIndex: Int = -1

    companion object {
        fun newInstance(onClickYes: (Int) -> Unit = {}, onClose: (() -> Unit) = {}): RateUsFragment {
            val fragment = RateUsFragment()
            fragment.onRateStarsCountClick = onClickYes
            fragment.onClose = onClose
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onRateStarsCountClick == null) {
            dismissAllowingStateLoss()
            return
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.new_rate_us_dialog_netigen, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        setStarsLogic()
        requireView().findViewById<View>(R.id.closeImage).setOnClickListener {
            dismissAllowingStateLoss()
            onClose?.invoke()
        }
    }

    @Suppress("DEPRECATION")
    private fun setStarsLogic() {
        val confirmBtn = requireView().findViewById<TextView>(R.id.confirmButton)
        val stars = requireView().findViewById<LinearLayout>(R.id.starsLayout)

        for (i in 0 until stars.childCount) {
            stars[i].setOnClickListener {
                clickedStarIndex = i
                refreshStars(stars, clickedStarIndex)
                confirmBtn.setTextColor(resources.getColor(R.color.netigen_button_color_text))
                confirmBtn.setBackgroundResource(R.drawable.rate_main_button)
                val clickedStarsCount = clickedStarIndex + 1
                confirmBtn.setOnClickListener {
                    onRateClick(clickedStarsCount)
                    dismissAllowingStateLoss()
                }
            }
        }
    }

    private fun onRateClick(clickedStarsCount: Int) {
        onRateStarsCountClick?.invoke(clickedStarsCount)
    }

    private fun refreshStars(stars: LinearLayout, clickedStarIndex: Int) {
        for (i in 0 until stars.childCount) {
            (stars[i] as ImageView).setImageResource(if (clickedStarIndex >= i) (R.drawable.star_netigen_on) else (R.drawable.star_netigen_off))
        }
    }
}
