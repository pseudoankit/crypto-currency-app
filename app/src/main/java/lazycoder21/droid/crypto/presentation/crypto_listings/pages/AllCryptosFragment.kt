package lazycoder21.droid.crypto.presentation.crypto_listings.pages

import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.presentation.crypto_listings.CryptoListingsBaseFragment
import lazycoder21.droid.crypto.utils.CryptoFilter

@AndroidEntryPoint
class AllCryptosFragment : CryptoListingsBaseFragment(CryptoFilter.All)