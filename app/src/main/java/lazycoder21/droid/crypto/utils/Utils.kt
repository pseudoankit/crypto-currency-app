package lazycoder21.droid.crypto.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Type


object Utils {
    val Fragment.mTag: String? get() = javaClass.canonicalName

    fun <T> fastLazy(block: () -> T) = lazy(LazyThreadSafetyMode.NONE, block)

    fun Context.mColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

    fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit) =
        launch(Dispatchers.IO, block = block)

    inline fun <reified T> String.parse(): T? {
        return Moshi.Builder().build().adapter(T::class.java).fromJson(this)
    }

    inline fun <reified T> String.parseList(): List<T>? {
        val moshi = Moshi.Builder().build()
        val listOfCardsType: Type =
            Types.newParameterizedType(List::class.java, T::class.java)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(listOfCardsType)
        return jsonAdapter.fromJson(this)
    }
}