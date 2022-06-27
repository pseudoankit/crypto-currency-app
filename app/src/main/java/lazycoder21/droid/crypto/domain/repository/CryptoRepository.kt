package lazycoder21.droid.crypto.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder

interface CryptoRepository {
    fun getCryptoListings(
        symbol: String,
        @SortOrder sortOrder: Int,
        @SortOptions sortOption: Int,
        config: PagedList.Config
    ): LiveData<PagedList<CryptoDetail>>

    fun getFavouriteCryptoListings(
        symbol: String,
        @SortOrder sortOrder: Int,
        @SortOptions sortOption: Int,
        config: PagedList.Config
    ): LiveData<PagedList<CryptoDetail>>

    fun getCryptoDetail(symbol: String): LiveData<CryptoDetail>

    suspend fun syncListing()
    suspend fun favouriteCrypto(item: CryptoDetail)
}