package lazycoder21.droid.crypto.presentation.crypto_listings.pages.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.CryptoDetail.Companion.sort
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteCryptosViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : CryptoListingsBaseViewModel(cryptoRepository) {

    fun fetchFavouriteListings(): LiveData<List<CryptoDetail>> {
        //todo transformation
        return cryptoRepository.getFavouriteCryptoListings(query).map {
            it.sort(sortOptions)
        }
    }

}