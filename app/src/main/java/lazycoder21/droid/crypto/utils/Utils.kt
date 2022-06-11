package lazycoder21.droid.crypto.utils

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Utils {
    val Fragment.mTag: String? get() = javaClass.canonicalName

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
}