package lazycoder21.droid.crypto.domain.repository

import androidx.lifecycle.LiveData
import lazycoder21.droid.crypto.domain.model.CryptoDetail

interface CryptoRepository {
    fun getCryptoListings(symbol: String = ""): LiveData<List<CryptoDetail>>

    fun getFavouriteCryptoListings(query: String = ""): LiveData<List<CryptoDetail>>

    fun getCryptoDetail(symbol: String): LiveData<CryptoDetail>

    suspend fun syncListing()
    suspend fun insertListings(list: List<CryptoDetail>)
    suspend fun favouriteCrypto(item: CryptoDetail)
}