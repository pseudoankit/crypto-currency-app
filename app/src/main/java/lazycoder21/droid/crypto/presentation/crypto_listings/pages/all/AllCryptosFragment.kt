package lazycoder21.droid.crypto.presentation.crypto_listings.pages.all

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseFragment

@AndroidEntryPoint
class AllCryptosFragment : CryptoListingsBaseFragment() {

    private val viewModel: AllCryptoViewModel by viewModels()

    private fun fetchListings() {
        viewModel.fetchAllListings().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun searchQueryChanged() {
        fetchListings()
    }

    override fun provideViewModel() = viewModel
}