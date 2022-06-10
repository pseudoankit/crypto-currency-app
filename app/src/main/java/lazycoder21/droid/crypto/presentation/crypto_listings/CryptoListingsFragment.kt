package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lazycoder21.droid.crypto.R

class CryptoListingsFragment : Fragment() {

    companion object {
        fun newInstance() = CryptoListingsFragment()
    }

    private lateinit var viewModel: CryptoListingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_listings, container, false)
    }



}