package lazycoder21.droid.crypto.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import lazycoder21.droid.crypto.R
import lazycoder21.droid.crypto.presentation.crypto_detail.CryptoDetailFragment
import lazycoder21.droid.crypto.presentation.crypto_listings.CryptoListingsFragment
import lazycoder21.droid.crypto.utils.Utils.mTag

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(CryptoListingsFragment.newInstance(), false)

    }

    fun openDetailFragment(symbol: String) {
        addFragment(CryptoDetailFragment.newInstance(symbol))
    }

    private fun addFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(fragment.mTag)
        transaction.add(R.id.fragment_container_view, fragment)
        transaction.commit()
    }
}