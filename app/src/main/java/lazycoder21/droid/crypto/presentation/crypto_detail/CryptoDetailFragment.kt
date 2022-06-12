package lazycoder21.droid.crypto.presentation.crypto_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.databinding.FragmentCryptoDetailBinding
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.utils.buildSymbolConversionText
import lazycoder21.droid.crypto.utils.updateFavouriteIcon
import lazycoder21.droid.crypto.utils.updatePriceChange

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
        drawGraph()
    }

    private fun drawGraph() = with(binding.graph) {
        //todo change data with actual one
        val series = LineGraphSeries(arrayOf(
            DataPoint(0.0, 1.0),
            DataPoint(1.0, 3.0),
            DataPoint(2.0, 4.0),
            DataPoint(3.0, 9.0),
            DataPoint(5.0, 11.0),
        ))
        addSeries(series)
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
        tvPriceChange.updatePriceChange(cryptoDetail)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoDetailBinding.inflate(layoutInflater)
}