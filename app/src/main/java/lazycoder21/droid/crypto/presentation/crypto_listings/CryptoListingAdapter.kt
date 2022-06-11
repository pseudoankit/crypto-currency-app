package lazycoder21.droid.crypto.presentation.crypto_listings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.crypto.databinding.RvItemCryptoListingItemBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail

class CryptoListingAdapter :
    ListAdapter<CryptoDetail, CryptoListingAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: RvItemCryptoListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CryptoDetail) {

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