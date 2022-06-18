package lazycoder21.droid.crypto.presentation.crypto_listings.pages.favourite

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteCryptosViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : CryptoListingsBaseViewModel(cryptoRepository) {

    override val fetchCryptoListings: LiveData<List<CryptoDetail>>
        get() = cryptoRepository.getFavouriteCryptoListings(searchQuery)

}