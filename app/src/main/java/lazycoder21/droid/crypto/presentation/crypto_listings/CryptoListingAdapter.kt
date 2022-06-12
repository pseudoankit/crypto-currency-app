package lazycoder21.droid.crypto.presentation.crypto_listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.crypto.databinding.RvItemCryptoListingItemBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.buildSymbolConversionText
import lazycoder21.droid.crypto.utils.updateFavouriteIcon
import lazycoder21.droid.crypto.utils.updatePriceChange

class CryptoListingAdapter(
    private val onItemClick: (CryptoDetail) -> Unit,
    private val itemFavourite: (CryptoDetail) -> Unit,
) : ListAdapter<CryptoDetail, CryptoListingAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: RvItemCryptoListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CryptoDetail) = with(binding) {
            //todo
            tvSymbolConv.buildSymbolConversionText(item)
            tvPrice.text = "₹${item.lastPrice}"
            tvPriceChange.updatePriceChange(item)
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
            btnFavourite.apply {
                updateFavouriteIcon(item)
                setOnClickListener {
                    itemFavourite.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemCryptoListingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<CryptoDetail>() {
        override fun areItemsTheSame(oldItem: CryptoDetail, newItem: CryptoDetail): Boolean {
            return oldItem.symbol == newItem.symbol
        }

        override fun areContentsTheSame(oldItem: CryptoDetail, newItem: CryptoDetail): Boolean {
            return oldItem == newItem
        }
    }

}