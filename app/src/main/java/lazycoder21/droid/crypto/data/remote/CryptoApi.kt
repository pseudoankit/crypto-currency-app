package lazycoder21.droid.crypto.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("tickers/24hr")
    suspend fun getCryptoListings()

    @GET("tickers/24hr")
    suspend fun getCryptoDetail(@Query("symbol") symbol: String)

    companion object {
        const val BASE_URL = "https://api.wazirx.com/sapi/v1/"
    }
}