package lazycoder21.droid.crypto.domain.model

import lazycoder21.droid.crypto.utils.CryptoSortingUtils

data class Filter(
    var sortOrder: Int = CryptoSortingUtils.SortOrder.ASCENDING,
    var sortOption: Int = CryptoSortingUtils.SortOptions.ALPHABETIC,
    var searchQuery: String = ""
)
