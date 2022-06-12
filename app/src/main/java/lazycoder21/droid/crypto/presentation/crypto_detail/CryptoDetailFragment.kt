package lazycoder21.droid.crypto.presentation.crypto_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.databinding.FragmentCryptoDetailBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.utils.buildSymbolConversionText
import lazycoder21.droid.crypto.utils.updateFavouriteIcon

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

        init()
        initObserver()
    }

    private fun init() {
        val symbol = arguments?.getString(ARG_SYMBOL) ?: return
        viewModel.symbol = symbol
    }

    private fun initObserver() {
        viewModel.cryptoDetail.observe(viewLifecycleOwner) {
            setActionBarData(it)
        }
    }

    private fun setActionBarData(cryptoDetail: CryptoDetail) = with(binding.actionBar) {
        btnBack.setOnClickListener { activity?.onBackPressed() }
        tvSymbolConv.buildSymbolConversionText(cryptoDetail)
        tvPrice.text = "₹${cryptoDetail.lastPrice}"
        btnFavourite.apply {
            updateFavouriteIcon(cryptoDetail)
            setOnClickListener {
                viewModel.favouriteCrypto(cryptoDetail)
            }
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoDetailBinding.inflate(layoutInflater)
}