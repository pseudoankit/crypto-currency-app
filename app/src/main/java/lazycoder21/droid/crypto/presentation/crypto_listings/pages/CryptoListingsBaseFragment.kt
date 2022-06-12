package lazycoder21.droid.crypto.presentation.crypto_listings.pages

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingBaseBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.CryptoListingsViewModel
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.CryptoListingAdapter
import lazycoder21.droid.crypto.presentation.main.MainActivity
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.Utils.fastLazy

@AndroidEntryPoint
abstract class CryptoListingsBaseFragment : BaseFragment<FragmentCryptoListingBaseBinding>() {

    val viewModel: CryptoListingsViewModel by viewModels()
    val adapter by fastLazy {
        CryptoListingAdapter(::itemClicked, ::itemFavourite)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpSearchView(binding.searchBar)
        initRecyclerView()
        initListener()
    }

    private fun initListener() = with(binding) {
        swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.syncData()
                isRefreshing = false
            }
        }

        btnSort.setOnClickListener {
            showSortOptionMenu()
        }
    }

    private fun showSortOptionMenu() {
        PopupMenu(context, binding.btnSort).apply {
            menuInflater.inflate(R.menu.menu_filter_options, menu)
            setOnMenuItemClickListener {
                adapter.submitList(
                    viewModel.sort(
                        adapter.currentList,
                        when (it.itemId) {
                            R.id.sortByAlphabetic -> SortOptions.ALPHABETIC
                            R.id.sortByVolume -> SortOptions.VOLUME
                            R.id.sortByPriceChange -> SortOptions.PRICE_CHANGE
                            else -> 0
                        }
                    )
                )
                true
            }
        }.show()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@CryptoListingsBaseFragment.adapter
        }
        binding.scrollToTop.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun itemFavourite(item: CryptoDetail) {
        viewModel.favouriteCrypto(item)
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
                searchQueryChanged(query)
                return true
            }
        }
    }

    private fun setUpSearchView(searchView: SearchView) = with(searchView) {
        queryHint = resources.getString(R.string.crypto_search_hint)
        isSubmitButtonEnabled = true
        setOnQueryTextListener(searchBarListener())
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingBaseBinding.inflate(layoutInflater)

    abstract fun searchQueryChanged(query: String)

}