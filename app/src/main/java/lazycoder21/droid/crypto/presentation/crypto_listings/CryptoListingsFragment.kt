package lazycoder21.droid.crypto.presentation.crypto_listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingsBinding
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.utils.Utils.fastLazy

@AndroidEntryPoint
class CryptoListingsFragment : BaseFragment<FragmentCryptoListingsBinding>() {

    companion object {
        fun newInstance() = CryptoListingsFragment()
    }

    private val viewModel: CryptoListingsViewModel by viewModels()
    private val adapter by fastLazy {
        CryptoListingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()

    }

    private fun initObserver() {
        viewModel.listings().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingsBinding.inflate(layoutInflater)
}