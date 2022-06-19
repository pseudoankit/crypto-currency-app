package lazycoder21.droid.crypto.presentation.crypto_listings.pages.favourite

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.base.CryptoListingsBaseFragment

@AndroidEntryPoint
class FavouriteCryptosFragment : CryptoListingsBaseFragment() {

    private val viewModel: FavouriteCryptosViewModel by viewModels()

    override fun provideViewModel() = viewModel
}