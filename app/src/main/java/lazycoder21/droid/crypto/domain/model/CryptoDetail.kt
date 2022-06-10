package lazycoder21.droid.crypto.domain.model

data class CryptoDetail(
	val volume: String? = null,
	val symbol: String? = null,
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

