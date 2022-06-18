package lazycoder21.droid.crypto.utils

import lazycoder21.droid.crypto.domain.model.FilterView

object Constants {

    fun getFilterList() = listOf(
        FilterView("A-Z", SortOptions.ALPHABETIC),
        FilterView("Volume", SortOptions.VOLUME),
        FilterView("Price Change", SortOptions.PRICE_CHANGE)
    )
}