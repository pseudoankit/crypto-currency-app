package lazycoder21.droid.crypto.presentation.crypto_listings.pages

import android.os.Bundle
import android.view.View

class AllCryptosFragment : CryptoListingsBaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchListings(query = viewModel.allTabSearchQuery)
    }

    private fun fetchListings(query: String = "") {
        viewModel.fetchAllListings(query = query).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun searchQueryChanged(query: String) {
        fetchListings(query = query)
    }
}