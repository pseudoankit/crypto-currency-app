package lazycoder21.droid.crypto.domain.model

import lazycoder21.droid.crypto.utils.SortOptions

data class FilterView(
    val title: String,
    val sortOptions: Int,
    val ascIcon: String? = null,
    val descIcon: String? = null,
) {
    companion object {
        fun default() = FilterView("", SortOptions.NONE)
    }
}
