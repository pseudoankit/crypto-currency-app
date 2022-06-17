package lazycoder21.droid.crypto.utils

import androidx.annotation.IntDef
import lazycoder21.droid.crypto.utils.SortOptions.Companion.ALPHABETIC
import lazycoder21.droid.crypto.utils.SortOptions.Companion.NONE
import lazycoder21.droid.crypto.utils.SortOptions.Companion.PRICE_CHANGE
import lazycoder21.droid.crypto.utils.SortOptions.Companion.VOLUME
import lazycoder21.droid.crypto.utils.SortOrder.Companion.ASCENDING
import lazycoder21.droid.crypto.utils.SortOrder.Companion.DESCENDING

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@IntDef(ALPHABETIC, VOLUME, PRICE_CHANGE, NONE)
@Retention(AnnotationRetention.SOURCE)
annotation class SortOptions {
    companion object {
        const val NONE = 0
        const val ALPHABETIC = 1
        const val VOLUME = 2
        const val PRICE_CHANGE = 3
    }
}

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@IntDef(ASCENDING, DESCENDING)
@Retention(AnnotationRetention.SOURCE)
annotation class SortOrder {
    companion object {
        const val ASCENDING = 0
        const val DESCENDING = 1
    }
}