package lazycoder21.droid.crypto.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Utils.launchIO
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    fun sync() {
        viewModelScope.launchIO {
            cryptoRepository.syncListing()
        }
    }
}