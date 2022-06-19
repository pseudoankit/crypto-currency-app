package lazycoder21.droid.crypto.domain.model

import lazycoder21.droid.crypto.utils.CryptoSortingUtils

data class FilterTransformation(
    var sortOrder: Int = CryptoSortingUtils.SortOrder.ASCENDING,
    var sortOption: Int = CryptoSortingUtils.SortOptions.ALPHABETIC,
    var searchQuery: String = ""
)
