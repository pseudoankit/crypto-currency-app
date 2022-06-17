package lazycoder21.droid.crypto.presentation.crypto_listings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.crypto.databinding.RvItemFilterBinding
import lazycoder21.droid.crypto.domain.model.FilterView
import lazycoder21.droid.crypto.utils.Constants
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.SortOrder

class FilterAdapter(
    private val list: List<FilterView> = Constants.getFilterList(),
    private val onFilterChanged: (@SortOptions Int, @SortOrder Int) -> Unit,
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAdapter.ViewHolder {
        val binding = RvItemFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterAdapter.ViewHolder, position: Int) {
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
            }
            iconCTA = { sortOrder ->
                onFilterChanged.invoke(item.sortOptions, sortOrder)
            }
        }
    }
}