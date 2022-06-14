package lazycoder21.droid.crypto.presentation.crypto_listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import lazycoder21.droid.crypto.databinding.FragmentCryptoListingsBinding
import lazycoder21.droid.crypto.presentation.base.BaseFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.adapter.ViewPagerAdapter
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.all.AllCryptosFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.pages.favourite.FavouriteCryptosFragment
import lazycoder21.droid.crypto.utils.Utils.fastLazy

class CryptoListingsFragment : BaseFragment<FragmentCryptoListingsBinding>() {

    companion object {
        const val TAG = "crypto_listing"
    }

    private val viewPagerAdapter by fastLazy { buildViewPager }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()
    }

    private fun setUpViewPager() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private val buildViewPager
        get() = ViewPagerAdapter(childFragmentManager).apply {
            add(FavouriteCryptosFragment(), "Favourite")
            add(AllCryptosFragment(), "All")
        }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        FragmentCryptoListingsBinding.inflate(layoutInflater)
}