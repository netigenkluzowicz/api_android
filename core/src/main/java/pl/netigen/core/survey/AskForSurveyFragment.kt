package pl.netigen.core.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.netigen.core.R
import pl.netigen.core.utils.BaseDialogFragment

/**
 * [BaseDialogFragment] used for show users "Ask For Survey" dialog, see [ISurvey]
 *
 */
class AskForSurveyFragment : BaseDialogFragment() {
    private var onClickYes: (() -> Unit)? = null

    companion object {
        fun newInstance(onClickYes: () -> Unit): AskForSurveyFragment {
            val fragment = AskForSurveyFragment()
            fragment.onClickYes = onClickYes
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (onClickYes == null) {
            dismissAllowingStateLoss()
            return
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_fragment_ask_for_survey_netigen_api, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        setNegativeButtonListeners()
        setPositiveButtonListener()
    }

    private fun setPositiveButtonListener() {
        requireView().findViewById<View>(R.id.confirmButton).setOnClickListener {
            dismissAllowingStateLoss()
            onClickYes?.let { it() }
        }
    }

    private fun setNegativeButtonListeners() {
        requireView().findViewById<View>(R.id.skip).setOnClickListener { dismissAllowingStateLoss() }
    }
}
