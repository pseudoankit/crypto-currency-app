package lazycoder21.droid.crypto.presentation.crypto_listings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.crypto.databinding.RvItemFilterBinding
import lazycoder21.droid.crypto.domain.model.FilterView
import lazycoder21.droid.crypto.utils.CryptoSortingUtils

class CryptoSortRvAdapter(
    private val list: List<FilterView> = CryptoSortingUtils.sortingOptions(),
    private val onFilterChanged: (@CryptoSortingUtils.SortOptions Int, @CryptoSortingUtils.SortOrder Int) -> Unit,
) : RecyclerView.Adapter<CryptoSortRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoSortRvAdapter.ViewHolder {
        val binding = RvItemFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoSortRvAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(
        private val binding: RvItemFilterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding.filterView) {
            val item = list[adapterPosition]
            item.apply {
                initialize(filterName = title, ascIcon, descIcon)
                updateTextColor(item.isSelected)
            }
            iconCTA = { sortOrder ->
                updateSelectedSortItem(position = adapterPosition)
                onFilterChanged.invoke(item.sortOptions, sortOrder)
            }
        }
    }

    private fun updateSelectedSortItem(position: Int) {
        list.forEachIndexed { idx, item ->
            item.isSelected = idx == position
        }
        notifyDataSetChanged()
    }
}