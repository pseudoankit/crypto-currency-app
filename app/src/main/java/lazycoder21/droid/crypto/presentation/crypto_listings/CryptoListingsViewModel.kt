package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    fun listings(symbol: String) = cryptoRepository.getCryptoListings(symbol)

}