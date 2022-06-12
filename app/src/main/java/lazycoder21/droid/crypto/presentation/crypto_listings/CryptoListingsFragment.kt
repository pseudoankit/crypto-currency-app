package lazycoder21.droid.crypto.presentation.crypto_listings

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingsBinding
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.utils.Utils.fastLazy

@AndroidEntryPoint
class CryptoListingsFragment : BaseFragment<FragmentCryptoListingsBinding>() {

    companion object {
        fun newInstance() = CryptoListingsFragment()
    }

    private var searchViewExpanded = false
    private var searchView: SearchView? = null
    private val viewModel: CryptoListingsViewModel by viewModels()
    private val adapter by fastLazy {
        CryptoListingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        fetchListings()
    }

    private fun init() {
        setHasOptionsMenu(true)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@CryptoListingsFragment.adapter
        }
    }

    private fun searchBarListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (searchViewExpanded)
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
            searchViewExpanded = true
            this.setQuery(viewModel.searchQuery, false)
        }
//        this.findViewById<ImageView>(R.id.search_go_btn)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_options, menu)

        searchView = (menu.findItem(R.id.menu_search)?.actionView as? SearchView)?.apply {
            setUpSearchView(this)
        }
        (menu.findItem(R.id.menu_sort))?.apply {
            handleMenuOptionSort()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                handleBackButton()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleMenuOptionSort() {

    }

    private fun closeSearchView() {
        searchViewExpanded = false
        searchView?.onActionViewCollapsed()
    }

    private fun handleBackButton() {
        if (searchViewExpanded) {
            closeSearchView()
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingsBinding.inflate(layoutInflater)
}