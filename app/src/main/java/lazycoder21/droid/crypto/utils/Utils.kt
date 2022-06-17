package lazycoder21.droid.crypto.utils

import android.content.Context
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object Utils {

    val View.hide get() = run { visibility = View.GONE }

    val View.show get() = run { visibility = View.VISIBLE }

    fun <T> fastLazy(block: () -> T) = lazy(LazyThreadSafetyMode.NONE, block)

    fun Context.mDimension(@DimenRes id: Int) = resources.getDimension(id)

    fun Context.mColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

    fun Context.spanColor(@ColorRes id: Int) = ForegroundColorSpan(this.mColor(id))

    fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit) =
        launch(Dispatchers.IO, block = block)

    suspend inline fun <reified T> String.parse(): T? {
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