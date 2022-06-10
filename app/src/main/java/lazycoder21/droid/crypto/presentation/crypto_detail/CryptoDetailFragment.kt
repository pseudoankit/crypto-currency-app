package lazycoder21.droid.crypto.presentation.crypto_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lazycoder21.droid.crypto.R

class CryptoDetailFragment : Fragment() {

    companion object {
        fun newInstance(symbol: String) = CryptoDetailFragment().apply {
            arguments = Bundle().also {
                it.putString(ARG_SYMBOL, symbol)
            }
        }

        private const val ARG_SYMBOL = "symbol"
    }

    private lateinit var viewModel: CryptoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_detail, container, false)
    }


}