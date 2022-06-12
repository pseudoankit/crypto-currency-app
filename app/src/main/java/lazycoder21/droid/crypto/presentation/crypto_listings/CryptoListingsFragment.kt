package lazycoder21.droid.crypto.presentation.crypto_listings

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingsBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.presentation.main.MainActivity
import lazycoder21.droid.crypto.utils.Utils.fastLazy

@AndroidEntryPoint
class CryptoListingsFragment : BaseFragment<FragmentCryptoListingsBinding>() {

    companion object {
        const val TAG = "crypto_list"
        fun newInstance() = CryptoListingsFragment()
    }

    private val viewModel: CryptoListingsViewModel by viewModels()
    private val adapter by fastLazy {
        CryptoListingAdapter(::itemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        fetchListings()
    }

    private fun init() {
        setUpSearchView(binding.searchBar)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@CryptoListingsFragment.adapter
        }
    }

    private fun itemClicked(item: CryptoDetail) {
        (activity as? MainActivity)?.navigateToDetailScreen(item.symbol)
    }

    private fun searchBarListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                fetchListings(query)
                return true
            }
        }
    }

    private fun fetchListings(query: String = "") {
        viewModel.fetchListings(query = query).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setUpSearchView(searchView: SearchView) = with(searchView) {
        queryHint = resources.getString(R.string.crypto_search_hint)
        isSubmitButtonEnabled = true
        setOnQueryTextListener(searchBarListener())
        setOnSearchClickListener {
            this.setQuery(viewModel.searchQuery, false)
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingsBinding.inflate(layoutInflater)
}