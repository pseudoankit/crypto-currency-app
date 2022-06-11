package lazycoder21.droid.crypto.data.mapper

import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal
import lazycoder21.droid.crypto.data.remote.model.CryptoListingsRemote
import lazycoder21.droid.crypto.domain.model.CryptoDetail

object CryptoListingMapper {

    val List<CryptoDetailLocal>.mapToDomain: List<CryptoDetail>
        get() = run { map { it.mapToDomain } }

    val CryptoDetailLocal.mapToDomain: CryptoDetail
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
                bidPrice = bidPrice,
            )
        }

    val List<CryptoListingsRemote.CryptoDetailRemote?>.toCryptoDetail: List<CryptoDetail?>
        get() = run { map { it?.toCryptoDetail } }

    val CryptoListingsRemote.CryptoDetailRemote.toCryptoDetail: CryptoDetail
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