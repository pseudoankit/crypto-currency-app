package lazycoder21.droid.crypto.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.presentation.crypto_detail.CryptoDetailFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.CryptoListingsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(
            CryptoListingsFragment(),
            false,
            tag = CryptoListingsFragment.TAG
        )

        viewModel.sync()
    }

    private fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        tag: String? = null,
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(tag)
        transaction.replace(R.id.fragment_container_view, fragment, tag)
        transaction.commit()
    }

    fun navigateToDetailScreen(symbol: String) {
        addFragment(
            fragment = CryptoDetailFragment.newInstance(symbol),
            tag = CryptoDetailFragment.TAG
        )
    }
}