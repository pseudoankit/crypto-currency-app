package lazycoder21.droid.crypto.domain.repository

import androidx.lifecycle.LiveData
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.utils.Resource

interface CryptoRepository {
    suspend fun getCryptoListings(symbol: String = ""): LiveData<List<CryptoDetail>>
    suspend fun getCryptoDetail(symbol: String): LiveData<CryptoDetail>
}