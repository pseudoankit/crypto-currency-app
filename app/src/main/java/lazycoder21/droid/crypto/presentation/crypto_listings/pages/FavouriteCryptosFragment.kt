package lazycoder21.droid.crypto.presentation.crypto_listings.pages

import android.os.Bundle
import android.view.View

class FavouriteCryptosFragment : CryptoListingsBaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchListings(query = viewModel.favouriteSearchQuery)
    }

    private fun fetchListings(query: String = "") {
        viewModel.fetchFavouriteListings(query = query).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun searchQueryChanged(query: String) {
        fetchListings(query = query)
    }
}