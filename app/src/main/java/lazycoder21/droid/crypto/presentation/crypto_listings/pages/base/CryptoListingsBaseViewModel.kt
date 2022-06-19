package lazycoder21.droid.crypto.presentation.crypto_listings.pages.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.FilterTransformation
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder
import lazycoder21.droid.crypto.utils.Utils.launchIO

abstract class CryptoListingsBaseViewModel(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    protected val filterTransformation = FilterTransformation()
    protected val transformationParams = MutableLiveData(filterTransformation)

    fun updateSearchQuery(query: String) {
        filterTransformation.searchQuery = query
        transformationParams.value = filterTransformation
    }

    fun sort(
        @SortOptions sortOptions: Int, @SortOrder sortOrder: Int,
    ) {
        filterTransformation.sortOrder = sortOrder
        filterTransformation.sortOption = sortOptions
        transformationParams.value = filterTransformation
    }

    fun favouriteCrypto(item: CryptoDetail) = viewModelScope.launchIO {
        cryptoRepository.favouriteCrypto(item)
    }

    private var syncDataJob: Job? = null
    fun syncData() {
        syncDataJob?.cancel()
        syncDataJob = viewModelScope.launchIO {
            cryptoRepository.syncListing()
        }
    }

    abstract fun fetchCryptoListings(): LiveData<List<CryptoDetail>>

    override fun onCleared() {
        super.onCleared()
        syncDataJob?.cancel()
    }
}