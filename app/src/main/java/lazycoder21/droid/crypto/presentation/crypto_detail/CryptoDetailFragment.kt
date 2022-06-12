package lazycoder21.droid.crypto.presentation.crypto_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.databinding.FragmentCryptoDetailBinding
import lazycoder21.droid.crypto.presentation.base.BaseFragment

@AndroidEntryPoint
class CryptoDetailFragment : BaseFragment<FragmentCryptoDetailBinding>() {

    companion object {
        const val TAG = "crypto_detail"
        fun newInstance(symbol: String) = CryptoDetailFragment().apply {
            arguments = Bundle().also {
                it.putString(ARG_SYMBOL, symbol)
            }
        }

        private const val ARG_SYMBOL = "symbol"
    }

    private val viewModel: CryptoDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoDetailBinding.inflate(layoutInflater)
}