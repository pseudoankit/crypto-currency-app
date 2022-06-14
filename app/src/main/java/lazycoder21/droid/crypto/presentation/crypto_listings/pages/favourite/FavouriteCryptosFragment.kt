package lazycoder21.droid.crypto.presentation.crypto_listings.pages.favourite

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseFragment

@AndroidEntryPoint
class FavouriteCryptosFragment : CryptoListingsBaseFragment() {

    private val viewModel: FavouriteCryptosViewModel by viewModels()

    private fun fetchListings() {
        viewModel.fetchFavouriteListings().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun searchQueryChanged() {
        fetchListings()
    }

    override fun provideViewModel() = viewModel
}