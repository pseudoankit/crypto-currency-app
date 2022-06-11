package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    fun fetchListings(query: String = ""): LiveData<List<CryptoDetail>> {
        //todo transformation
        return cryptoRepository.getCryptoListings(query).asLiveData()
    }

}