package pl.netigen.core.newlanguage

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.netigen.core.R
import pl.netigen.core.databinding.ItemNameLanguageNetigenApiBinding

class LanguageListAdapter(private val onItemClicked: (LanguageModelDisplayable) -> Unit) :
    ListAdapter<LanguageModelDisplayable, LanguageListAdapter.LanguageViewHolder>(LanguageDiffCallback()) {

    inner class LanguageViewHolder(private val binding: ItemNameLanguageNetigenApiBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(getItem(adapterPosition) as LanguageModelDisplayable)
            }
        }

        fun bind(item: LanguageModelDisplayable) {
            binding.run {
                languageName.text = item.language
                if (!item.isSelected) {
                    languageChecked.visibility = GONE
                    languageName.setTypeface(null, Typeface.NORMAL)
                    languageName.background = null
                } else {
                    languageChecked.visibility = VISIBLE
                    languageName.setTypeface(null, Typeface.BOLD)
                    languageName.background = itemView.resources.getDrawable(R.drawable.checked_item_netigen_api, null)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        return LanguageViewHolder(ItemNameLanguageNetigenApiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}

class LanguageDiffCallback : DiffUtil.ItemCallback<LanguageModelDisplayable>() {
    override fun areItemsTheSame(oldLanguageItem: LanguageModelDisplayable, newLanguageItem: LanguageModelDisplayable): Boolean {
        return oldLanguageItem.id == newLanguageItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldLanguageItem: LanguageModelDisplayable, newLanguageItem: LanguageModelDisplayable): Boolean {
        return oldLanguageItem == newLanguageItem
    }
}
