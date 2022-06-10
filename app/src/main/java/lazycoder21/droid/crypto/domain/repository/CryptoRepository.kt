package lazycoder21.droid.crypto.domain.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.CryptoListings
import lazycoder21.droid.crypto.utils.Resource

interface CryptoRepository {
    suspend fun getCryptoListings(): Flow<Resource<CryptoListings>>
    suspend fun getCryptoDetail(symbol: String): Flow<Resource<CryptoDetail>>
}