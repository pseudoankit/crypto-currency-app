package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Utils.launchIO
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    var allTabSearchQuery = ""
        private set
    var favouriteSearchQuery = ""
        private set

    fun fetchFavouriteListings(query: String = ""): LiveData<List<CryptoDetail>> {
        favouriteSearchQuery = query
        return cryptoRepository.getFavouriteCryptoListings(query)
    }

    fun fetchAllListings(query: String = ""): LiveData<List<CryptoDetail>> {
        //todo transformation
        allTabSearchQuery = query
        return cryptoRepository.getCryptoListings(query)
    }

    fun favouriteCrypto(item: CryptoDetail) = viewModelScope.launchIO {
        cryptoRepository.favouriteCrypto(item)
    }

    fun syncData() {
        viewModelScope.launchIO {
            cryptoRepository.syncListing()
        }
    }

}