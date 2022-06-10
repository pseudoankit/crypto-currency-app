package lazycoder21.droid.crypto.data.repository

import kotlinx.coroutines.flow.Flow
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.CryptoListings
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Resource

class CryptoRepositoryImpl : CryptoRepository {

    override suspend fun getCryptoListings(): Flow<Resource<CryptoListings>> {
        TODO("caching")
    }

    override suspend fun getCryptoDetail(symbol: String): Flow<Resource<CryptoDetail>> {
        TODO("Not yet implemented")
    }
}