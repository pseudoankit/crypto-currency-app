package lazycoder21.droid.crypto.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.Utils.launchIO
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    companion object {
        private const val SYNC_DURATION = 1000L
    }

    fun sync() {
        viewModelScope.launchIO {
            while (true) {
                delay(SYNC_DURATION)
                cryptoRepository.syncListing()
            }
        }
    }
}