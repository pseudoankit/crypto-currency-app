package lazycoder21.droid.crypto.presentation.crypto_listings

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingBaseBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.CryptoListingAdapter
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.CryptoSortRvAdapter
import lazycoder21.droid.crypto.presentation.main.MainActivity
import lazycoder21.droid.crypto.utils.CryptoFilter
import lazycoder21.droid.crypto.utils.CryptoSortingUtils
import lazycoder21.droid.crypto.utils.Utils.fastLazy

abstract class CryptoListingsBaseFragment(
    private val filter: CryptoFilter
) : BaseFragment<FragmentCryptoListingBaseBinding>() {

    private val viewModel: CryptoListingsViewModel by viewModels()

    private val sortingRvAdapter by fastLazy { CryptoSortRvAdapter(onFilterChanged = ::onFilterChanged) }
    private val adapter by fastLazy { CryptoListingAdapter(::itemClicked, ::itemFavourite) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        initListener()
        setUpSearchView(binding.searchBar)
        updateListing()
    }

    private fun initListener() = with(binding) {
        swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.syncData()
                isRefreshing = false
            }
        }
        binding.scrollToTop.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@CryptoListingsBaseFragment.adapter
        }

        binding.rvFilter.apply {
            adapter = sortingRvAdapter
        }
    }

    private fun onFilterChanged(
        @CryptoSortingUtils.SortOptions sortOptions: Int,
        @CryptoSortingUtils.SortOrder sortOrder: Int
    ) {
        viewModel.sort(sortOptions, sortOrder)
        updateListing()
    }

    private fun itemFavourite(item: CryptoDetail) {
        viewModel.favouriteCrypto(item)
    }

    private fun itemClicked(item: CryptoDetail) {
        (activity as? MainActivity)?.navigateToDetailScreen(item.symbol)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingBaseBinding.inflate(layoutInflater)

    private var lastObservedLiveData: LiveData<List<CryptoDetail>>? = null
    private fun updateListing() {
        lastObservedLiveData?.removeObservers(viewLifecycleOwner)
        lastObservedLiveData = viewModel.cryptoListing(filter = filter).apply {
            removeObservers(viewLifecycleOwner)
            observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
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
                viewModel.updateSearchQuery(query)
                updateListing()
                return true
            }
        }
    }
}