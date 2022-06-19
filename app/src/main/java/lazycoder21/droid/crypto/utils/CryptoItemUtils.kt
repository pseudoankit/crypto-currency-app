package lazycoder21.droid.crypto.utils

import android.text.Spannable
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.core.text.buildSpannedString
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.Utils.mColor
import lazycoder21.droid.crypto.utils.Utils.spanColor
import lostankit7.droid.customview.FontAwesomeIcon
import lostankit7.droid.customview.FontAwesomeIconType
import lostankit7.droid.utils.updateTypeface
import java.util.*

fun TextView.updatePriceChange(item: CryptoDetail) {
    val change = item.priceChange

    text = String.format("%.2f", change) + "%"
    setBackgroundColor(context.mColor(if (change > 0) R.color.highlighted else R.color.red))
}

fun FontAwesomeIcon.updateFavouriteIcon(item: CryptoDetail) {
    if (item.favourite) {
        updateTypeface(FontAwesomeIconType.SOLID)
    } else {
        updateTypeface(FontAwesomeIconType.REGULAR)
    }
    invalidate()
}

fun TextView.buildSymbolConversionText(item: CryptoDetail) {
    var start = 0
    var end = 0
    val flag = Spannable.SPAN_INCLUSIVE_INCLUSIVE
    text = buildSpannedString {
        start = length
        append(item.baseAsset?.uppercase(Locale.ROOT))
        end = length
        setSpan(context.spanColor(R.color.black), start, end, flag)

        start = length
        append("/${item.quoteAsset}")
        end = length
        setSpan(context.spanColor(R.color.sec_text_color), start, end, flag)
        setSpan(RelativeSizeSpan(.9f), start, end, flag)
    }
}