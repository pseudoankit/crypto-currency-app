package lazycoder21.droid.crypto.presentation.crypto_listings.pages.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.CryptoDetail.Companion.sort
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.Utils.launchIO

abstract class CryptoListingsBaseViewModel(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    init {
        print("fatal" + this)
    }

    var query = ""

    @SortOptions
    var sortOptions: Int = SortOptions.ALPHABETIC

    fun sort(list: List<CryptoDetail>, @SortOptions sortOptions: Int): List<CryptoDetail> {
        this.sortOptions = sortOptions
        return list.sort(sortOptions)
    }

    fun favouriteCrypto(item: CryptoDetail) = viewModelScope.launchIO {
        cryptoRepository.favouriteCrypto(item)
    }

    fun syncData() {
        viewModelScope.launchIO {
            cryptoRepository.syncListing()
        }
    }

}