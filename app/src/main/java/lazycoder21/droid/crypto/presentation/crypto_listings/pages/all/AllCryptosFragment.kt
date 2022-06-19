package lazycoder21.droid.crypto.presentation.crypto_listings.pages.all

import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseFragment

@AndroidEntryPoint
class AllCryptosFragment : CryptoListingsBaseFragment() {

    private val viewModel: AllCryptoViewModel by viewModels()

    override fun onCreateView() {
        super.onCreateView()
        val searchView = context?.let {
            SearchView(it).apply {
                setUpSearchView(this)
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }
        binding.layoutTopSection.addView(searchView)
    }

    private fun setUpSearchView(searchView: SearchView) = with(searchView) {
        queryHint = resources.getString(R.string.crypto_search_hint)
        isSubmitButtonEnabled = false
        setOnQueryTextListener(searchBarListener())
    }

    private fun searchBarListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.searchQuery = query
                updateListing()
                return true
            }
        }
    }

    override fun provideViewModel() = viewModel
}