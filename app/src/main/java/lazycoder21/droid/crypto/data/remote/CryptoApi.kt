package lazycoder21.droid.crypto.data.remote

import lazycoder21.droid.crypto.data.remote.model.CryptoListingsRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("tickers/24hr")
    suspend fun getListings(): Response<List<CryptoListingsRemote.CryptoDetailRemote>>

    @GET("tickers/24hr")
    suspend fun getCryptoDetail(@Query("symbol") symbol: String)

    companion object {
        const val BASE_URL = "https://api.wazirx.com/sapi/v1/"
    }
}