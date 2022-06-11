package lazycoder21.droid.crypto.presentation.crypto_listings

import android.content.Context
import android.text.Spannable
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.RvItemCryptoListingItemBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.Utils.mColor
import java.util.*

class CryptoListingAdapter :
    ListAdapter<CryptoDetail, CryptoListingAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: RvItemCryptoListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CryptoDetail) = with(binding) {
            val context = binding.root.context
            tvSymbolConv.text = buildSymbolConversionText(item, context)
            tvPrice.text = item.lastPrice
            tvPriceChange.text = calculatePriceChange(item)
        }

        private fun calculatePriceChange(item: CryptoDetail): String {
            return ""
        }

        private fun buildSymbolConversionText(item: CryptoDetail, context: Context): SpannedString {
            return buildSpannedString {
                append(item.baseAsset?.uppercase(Locale.ROOT))
                setSpan(ForegroundColorSpan(context.mColor(R.color.white)), 0,
                    this.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

                val stPoint = this.length
                append("/{${item.quoteAsset}}")
                setSpan(ForegroundColorSpan(context.mColor(R.color.sec_text_color)), stPoint,
                    this.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
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