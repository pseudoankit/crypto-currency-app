package lazycoder21.droid.crypto.presentation.crypto_listings.pages.base

import android.os.Bundle
import android.view.*
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

    private val viewModel: CryptoListingsBaseViewModel by fastLazy { provideViewModel() }

    private val filterAdapter by fastLazy { FilterAdapter(onFilterChanged = ::onFilterChanged) }
    val adapter by fastLazy { CryptoListingAdapter(::itemClicked, ::itemFavourite) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        initListener()
        loadData()
        initObserver()
    }

    private fun initObserver() {
        viewModel.cryptoListingsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
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
    }

    private fun itemFavourite(item: CryptoDetail) {
        viewModel.favouriteCrypto(item)
    }

    private fun itemClicked(item: CryptoDetail) {
        (activity as? MainActivity)?.navigateToDetailScreen(item.symbol)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingBaseBinding.inflate(layoutInflater)

    abstract fun loadData()
    abstract fun provideViewModel(): CryptoListingsBaseViewModel
}