package lazycoder21.droid.crypto.utils

import androidx.fragment.app.Fragment

object Utils {
    val Fragment.mTag: String? get() = javaClass.canonicalName

    fun <T> fastLazy(block: () -> T) = lazy(LazyThreadSafetyMode.NONE, block)
}