package lazycoder21.droid.crypto.utils

import androidx.annotation.IntDef
import lazycoder21.droid.crypto.domain.model.SortingModel
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions.Companion.ALPHABETIC
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions.Companion.PRICE_CHANGE
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions.Companion.VOLUME
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder.Companion.ASCENDING
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder.Companion.DESCENDING

object CryptoSortingUtils {

    fun sortingOptions() = listOf(
        SortingModel("A-Z", SortOptions.ALPHABETIC),
        SortingModel("Volume", SortOptions.VOLUME),
        SortingModel("Price Change", SortOptions.PRICE_CHANGE)
    )

    @Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
    @IntDef(ALPHABETIC, VOLUME, PRICE_CHANGE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class SortOptions {
        companion object {
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
}