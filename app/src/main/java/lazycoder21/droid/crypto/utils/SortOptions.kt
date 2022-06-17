package lazycoder21.droid.crypto.utils

import androidx.annotation.IntDef
import lazycoder21.droid.crypto.utils.SortOptions.Companion.ALPHABETIC
import lazycoder21.droid.crypto.utils.SortOptions.Companion.ASCENDING
import lazycoder21.droid.crypto.utils.SortOptions.Companion.DESCENDING
import lazycoder21.droid.crypto.utils.SortOptions.Companion.PRICE_CHANGE
import lazycoder21.droid.crypto.utils.SortOptions.Companion.VOLUME

@IntDef(ALPHABETIC, VOLUME, PRICE_CHANGE, ASCENDING, DESCENDING)
@Retention(AnnotationRetention.SOURCE)
annotation class SortOptions {
    companion object {
        const val ALPHABETIC = 3
        const val VOLUME = 4
        const val PRICE_CHANGE = 2
        const val ASCENDING = 0
        const val DESCENDING = 1
    }
}