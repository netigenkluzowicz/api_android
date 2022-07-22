package pl.netigen.core.newlanguage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import pl.netigen.core.fragment.NetigenVMFragment
import java.util.*


abstract class BaseLanguageFragment<VB : ViewBinding> : NetigenVMFragment() {

    private var _binding: VB by autoCleaned()
    val binding: VB get() = _binding

    private val languageListAdapter = LanguageListAdapter(this::onItemClicked)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ChangeLanguagePreferences.setActivityLocale(requireActivity())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    private fun initList() {
        binding.run {
            recyclerView.adapter = languageListAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.itemAnimator = null
            languageListAdapter.submitList(getList())
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initList()
    }

    private fun getList(): List<LanguageModelDisplayable> {
        val languageCodes = getLanguage()
        val languageModels: MutableList<LanguageModelDisplayable> = mutableListOf()
        for ((id, i) in languageCodes.indices.withIndex()) {
            val nextLocale = Locale(languageCodes[i])
            var name = nextLocale.getDisplayName(nextLocale)
            name = name.substring(0, 1).uppercase() + name.substring(1)
            val isSelected = languageCodes[i] == ChangeLanguagePreferences.preferencesLocale
            languageModels.add(LanguageModelDisplayable(id, languageCodes[i], name, isSelected))
        }
        return languageModels
    }

    private fun onItemClicked(languageModel: LanguageModelDisplayable) {
        context?.let { ChangeLanguagePreferences.setLocale(languageModel.code, it) }
        languageListAdapter.submitList(getList())

    }

    abstract fun initView()
    abstract val recyclerView: RecyclerView
    abstract fun getLanguage(): List<String>
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}
