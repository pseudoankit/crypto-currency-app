package lazycoder21.droid.crypto.presentation.crypto_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Utils.launchIO
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    var symbol: String = ""

    val cryptoDetail get() = cryptoRepository.getCryptoDetail(symbol)

    fun favouriteCrypto(item: CryptoDetail) = viewModelScope.launchIO {
        cryptoRepository.favouriteCrypto(item)
    }
}