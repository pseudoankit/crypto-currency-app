package lazycoder21.droid.crypto.utils

import androidx.annotation.IntDef
import lazycoder21.droid.crypto.utils.SortOptions.Companion.ALPHABETIC
import lazycoder21.droid.crypto.utils.SortOptions.Companion.PRICE_CHANGE
import lazycoder21.droid.crypto.utils.SortOptions.Companion.VOLUME

@IntDef(ALPHABETIC, VOLUME, PRICE_CHANGE)
@Retention(AnnotationRetention.SOURCE)
annotation class SortOptions {
    companion object {
        const val ALPHABETIC = 0
        const val VOLUME = 1
        const val PRICE_CHANGE = 2
    }
}