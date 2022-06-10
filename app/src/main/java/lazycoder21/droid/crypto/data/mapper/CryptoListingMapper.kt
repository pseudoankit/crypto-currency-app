package lazycoder21.droid.crypto.data.mapper

import lazycoder21.droid.crypto.data.remote.model.CryptoDetailRemote
import lazycoder21.droid.crypto.data.remote.model.CryptoListingsRemote
import lazycoder21.droid.crypto.domain.model.CryptoDetail
import lazycoder21.droid.crypto.domain.model.CryptoListings

object CryptoListingMapper {

    val CryptoListingsRemote.toCryptoListings: CryptoListings
        get() = run {
            CryptoListings(cryptoDetail = cryptoDetailRemote?.toCryptoDetail)
        }

    val List<CryptoDetailRemote?>.toCryptoDetail: List<CryptoDetail?>
        get() = run { map { it?.toCryptoDetail } }

    val CryptoDetailRemote.toCryptoDetail: CryptoDetail
        get() = run {
            CryptoDetail(
                volume = volume,
                symbol = symbol,
                askPrice = askPrice,
                at = at,
                lowPrice = lowPrice,
                highPrice = highPrice,
                openPrice = openPrice,
                baseAsset = baseAsset,
                quoteAsset = quoteAsset,
                bidPrice = bidPrice
            )
        }
}