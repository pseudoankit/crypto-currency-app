package lazycoder21.droid.crypto.presentation.crypto_listings.pages.all

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseFragment

@AndroidEntryPoint
class AllCryptosFragment : CryptoListingsBaseFragment() {

    private val viewModel: AllCryptoViewModel by viewModels()

    override fun provideViewModel() = viewModel
}