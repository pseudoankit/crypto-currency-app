package lazycoder21.droid.crypto.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lostankit7.droid.customview.FontAwesomeIcon
import lostankit7.droid.customview.FontAwesomeIconType
import lostankit7.droid.utils.updateTypeface
import java.util.*


object Utils {

    fun FontAwesomeIcon.updateFavouriteIcon(item: CryptoDetail) {
        if (item.favourite) {
            updateTypeface(FontAwesomeIconType.SOLID)
        } else {
            updateTypeface(FontAwesomeIconType.REGULAR)
        }
        invalidate()
    }

    fun Context.buildSymbolConversionText(item: CryptoDetail): SpannedString {
        val context = this
        var start = 0
        var end = 0
        val flag = Spannable.SPAN_INCLUSIVE_INCLUSIVE
        return buildSpannedString {
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

    val View.hide get() = run { visibility = View.GONE }
    val View.show get() = run { visibility = View.VISIBLE }

    fun <T> fastLazy(block: () -> T) = lazy(LazyThreadSafetyMode.NONE, block)

    fun Context.mColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

    fun Context.spanColor(@ColorRes id: Int) = ForegroundColorSpan(this.mColor(id))

    fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit) =
        launch(Dispatchers.IO, block = block)

    inline fun <reified T> String.parse(): T? {
        return Gson().fromJson(this, T::class.java)
    }

    suspend fun safeApiCall(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {

        }
    }

    val View.hasFocus: Boolean
        get() = run {
            val view = this
            if (view.isFocused) return true
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    if (view.getChildAt(i).hasFocus) return true
                }
            }
            return false
        }
}