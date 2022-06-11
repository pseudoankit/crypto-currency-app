package lazycoder21.droid.crypto.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoDetailLocal(
    @PrimaryKey val symbol: String? = null,
    val volume: String? = null,
    val askPrice: String? = null,
    val at: Int? = null,
    val lowPrice: String? = null,
    val highPrice: String? = null,
    val openPrice: String? = null,
    val baseAsset: String? = null,
    val quoteAsset: String? = null,
    val bidPrice: String? = null,
    val lastPrice: String? = null,
)

