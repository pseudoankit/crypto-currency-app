package lazycoder21.droid.crypto.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import lazycoder21.droid.crypto.data.local.LocalDataBase
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapRemoteToLocal
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapToDomain
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapToLocal
import lazycoder21.droid.crypto.data.remote.CryptoApi
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepositoryImpl @Inject constructor(
    db: LocalDataBase,
    private val cryptoApi: CryptoApi,
) : CryptoRepository {

    private val dao = db.cryptoDao

    override fun getCryptoListings(
        symbol: String,
        sortOrder: Int,
        sortOptions: Int,
    ): LiveData<List<CryptoDetail>> {
        return dao.getListings(symbol = symbol).map { it.mapToDomain }
    }

    override fun getCryptoDetail(symbol: String): LiveData<CryptoDetail> {
        return dao.getDetail(symbol = symbol).map { it.mapToDomain }
    }

    override suspend fun insertListings(list: List<CryptoDetail>) {
        _insertListings(list.mapToLocal)
    }

    override suspend fun syncListing() {
        safeApiCall {
            val response = cryptoApi.getListings()
            val listings = response.body()?.mapRemoteToLocal
            if (response.isSuccessful && listings != null) {
                _insertListings(listings)
            }
        }
    }

    private suspend fun _insertListings(list: List<CryptoDetailLocal>) {
        dao.insertOrUpdate(list)
    }

    override suspend fun favouriteCrypto(item: CryptoDetail) {
        item.favourite = !item.favourite
        dao.update(item = item.mapToLocal)
    }

    override fun getFavouriteCryptoListings(
        query: String,
        sortOrder: Int,
        sortOptions: Int,
    ): LiveData<List<CryptoDetail>> {
        return dao.getFavouriteListings(query).map { it.mapToDomain }
    }
}