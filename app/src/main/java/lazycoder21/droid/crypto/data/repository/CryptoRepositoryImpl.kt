package lazycoder21.droid.crypto.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import lazycoder21.droid.crypto.data.local.LocalDataBase
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapToDomain
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapToLocal
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepositoryImpl @Inject constructor(
    db: LocalDataBase,
) : CryptoRepository {

    private val dao = db.cryptoDao

    override fun getCryptoListings(symbol: String): LiveData<List<CryptoDetail>> {
        return dao.getListings(symbol = symbol).map { it.mapToDomain }
    }

    override fun getCryptoDetail(symbol: String): LiveData<CryptoDetail> {
        return dao.getDetail(symbol = symbol).map { it.mapToDomain }
    }

    override suspend fun insertListings(list: List<CryptoDetail>) {
        dao.insertListings(list.mapToLocal)
    }

}