package lazycoder21.droid.crypto.presentation.crypto_listings.utils

import androidx.paging.PagedList

object Paging {
    private const val PAGE_SIZE = 5
    private const val PAGE_INITIAL_LOAD_SIZE_HINT = 15
    private const val PAGE_PREFETCH_DISTANCE = 3

    val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_INITIAL_LOAD_SIZE_HINT)
        .setPrefetchDistance(PAGE_PREFETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()
}
