package lazycoder21.droid.crypto.domain.repository

import androidx.lifecycle.LiveData
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.SortOrder

interface CryptoRepository {
    fun getCryptoListings(
        symbol: String = "",
        @SortOrder sortOrder: Int,
        @SortOptions sortOptions: Int,
    ): LiveData<List<CryptoDetail>>

    fun getFavouriteCryptoListings(
        query: String = "",
        @SortOrder sortOrder: Int,
        @SortOptions sortOptions: Int,
    ): LiveData<List<CryptoDetail>>

    fun getCryptoDetail(symbol: String): LiveData<CryptoDetail>

    suspend fun syncListing()
    suspend fun insertListings(list: List<CryptoDetail>)
    suspend fun favouriteCrypto(item: CryptoDetail)
}