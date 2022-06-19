package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.Filter
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.CryptoFilter
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder
import lazycoder21.droid.crypto.utils.Utils.launchIO
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    private val filters = Filter()
    private val transformationParams = MutableLiveData(filters)
    fun cryptoListing(filter: CryptoFilter) =
        Transformations.switchMap(transformationParams) { params ->
            fetchCryptoListings(filter, params)
        }

    fun updateSearchQuery(query: String) {
        filters.searchQuery = query
        transformationParams.value = filters
    }

    fun sort(
        @SortOptions sortOptions: Int, @SortOrder sortOrder: Int,
    ) {
        filters.sortOrder = sortOrder
        filters.sortOption = sortOptions
        transformationParams.value = filters
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

    private fun fetchCryptoListings(
        filter: CryptoFilter,
        params: Filter
    ): LiveData<List<CryptoDetail>> {
        return when (filter) {
            CryptoFilter.All -> cryptoRepository.getCryptoListings(
                symbol = params.searchQuery,
                sortOrder = params.sortOrder,
                sortOption = params.sortOption
            )
            CryptoFilter.Favourite -> cryptoRepository.getFavouriteCryptoListings(
                symbol = params.searchQuery,
                sortOrder = params.sortOrder,
                sortOption = params.sortOption
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        syncDataJob?.cancel()
    }
}