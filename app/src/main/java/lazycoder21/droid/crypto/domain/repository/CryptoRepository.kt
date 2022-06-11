package lazycoder21.droid.crypto.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.crypto.domain.model.CryptoDetail

interface CryptoRepository {
    fun getCryptoListings(symbol: String = ""): Flow<List<CryptoDetail>>
    fun getCryptoDetail(symbol: String): LiveData<CryptoDetail>
    suspend fun insertListings(list: List<CryptoDetail>)
    suspend fun syncListing()
}