package lazycoder21.droid.crypto.presentation.crypto_listings.pages.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.SortOrder
import lazycoder21.droid.crypto.utils.Utils.fastLazy
import lazycoder21.droid.crypto.utils.Utils.launchIO

abstract class CryptoListingsBaseViewModel(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    //    protected val _cryptoListingsLiveData = MutableLiveData<List<CryptoDetail>>()
    val cryptoListingsLiveData: LiveData<List<CryptoDetail>> by fastLazy {
        fetchCryptoListings
    }

    var searchQuery = ""

    @SortOptions
    var sortOptions: Int = SortOptions.ALPHABETIC
        private set

    @SortOrder
    var sortOrder: Int = SortOrder.ASCENDING
        private set

    private var syncDataJob: Job? = null

    fun sort(
        @SortOptions sortOptions: Int, @SortOrder sortOrder: Int,
    ) {
        this.sortOrder = sortOrder
        this.sortOptions = sortOptions
    }

    fun favouriteCrypto(item: CryptoDetail) = viewModelScope.launchIO {
        cryptoRepository.favouriteCrypto(item)
    }

    fun syncData() {
        syncDataJob?.cancel()
        syncDataJob = viewModelScope.launchIO {
            cryptoRepository.syncListing()
        }
    }

    abstract val fetchCryptoListings: LiveData<List<CryptoDetail>>

    override fun onCleared() {
        super.onCleared()
        syncDataJob?.cancel()
    }
}