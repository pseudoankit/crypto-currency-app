package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingsBinding
import lazycoder21.droid.crypto.presentation.base.BaseFragment

@AndroidEntryPoint
class CryptoListingsFragment : BaseFragment<FragmentCryptoListingsBinding>() {

    companion object {
        fun newInstance() = CryptoListingsFragment()
    }

    private val viewModel: CryptoListingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        viewModel.listings("").observe(viewLifecycleOwner) {

        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingsBinding.inflate(layoutInflater)
}