package lazycoder21.droid.crypto.presentation.crypto_listings.pages.base

import android.os.Bundle
import android.view.*
import androidx.lifecycle.LiveData
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingBaseBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.CryptoListingAdapter
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.FilterAdapter
import lazycoder21.droid.crypto.presentation.main.MainActivity
import lazycoder21.droid.crypto.utils.SortOptions
import lazycoder21.droid.crypto.utils.SortOrder
import lazycoder21.droid.crypto.utils.Utils.fastLazy

abstract class CryptoListingsBaseFragment : BaseFragment<FragmentCryptoListingBaseBinding>() {

    val TAG get() = javaClass.simpleName

    private val viewModel: CryptoListingsBaseViewModel by fastLazy { provideViewModel() }

    private val filterAdapter by fastLazy { FilterAdapter(onFilterChanged = ::onFilterChanged) }
    private val adapter by fastLazy { CryptoListingAdapter(::itemClicked, ::itemFavourite) }

    private var lastObservedLiveData: LiveData<List<CryptoDetail>>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        initListener()
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
            adapter = filterAdapter
        }
    }

    private fun onFilterChanged(@SortOptions sortOptions: Int, @SortOrder sortOrder: Int) {
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

    fun updateListing() {
        lastObservedLiveData?.removeObservers(viewLifecycleOwner)
        lastObservedLiveData = viewModel.fetchCryptoListings().apply {
            removeObservers(viewLifecycleOwner)
            observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    abstract fun provideViewModel(): CryptoListingsBaseViewModel
}