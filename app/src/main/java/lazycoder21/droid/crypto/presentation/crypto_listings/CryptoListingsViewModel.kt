package lazycoder21.droid.crypto.presentation.crypto_listings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import javax.inject.Inject

@HiltViewModel
class CryptoListingsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {

    fun listings(symbol: String = "") = cryptoRepository.getCryptoListings(symbol)

}

private val foo = """
    [
      {
        "symbol": "btcinr",
        "baseAsset": "btc",
        "quoteAsset": "inr",
        "openPrice": "2450121",
        "lowPrice": "2353635.0",
        "highPrice": "2457129.0",
        "lastPrice": "2365103.0",
        "volume": "74.5535",
        "bidPrice": "2360002.0",
        "askPrice": "2365052.0",
        "at": 1654948403000
      },
      {
        "symbol": "xrpinr",
        "baseAsset": "xrp",
        "quoteAsset": "inr",
        "openPrice": "32.6992",
        "lowPrice": "30.7995",
        "highPrice": "32.6992",
        "lastPrice": "30.8",
        "volume": "279429.8",
        "bidPrice": "30.7683",
        "askPrice": "30.8",
        "at": 1654948403000
      },
      {
        "symbol": "ethinr",
        "baseAsset": "eth",
        "quoteAsset": "inr",
        "openPrice": "145076.4",
        "lowPrice": "128645.4",
        "highPrice": "145076.4",
        "lastPrice": "129401.6",
        "volume": "231.0278",
        "bidPrice": "129400.0",
        "askPrice": "129705.9",
        "at": 1654948403000
      },
      {
        "symbol": "trxinr",
        "baseAsset": "trx",
        "quoteAsset": "inr",
        "openPrice": "6.5821",
        "lowPrice": "6.3805",
        "highPrice": "6.5998",
        "lastPrice": "6.3909",
        "volume": "4088914.0",
        "bidPrice": "6.3805",
        "askPrice": "6.3909",
        "at": 1654948403000
      },
      {
        "symbol": "eosinr",
        "baseAsset": "eos",
        "quoteAsset": "inr",
        "openPrice": "100.62",
        "lowPrice": "96.0",
        "highPrice": "101.14",
        "lastPrice": "96.0",
        "volume": "912.79",
        "bidPrice": "95.1",
        "askPrice": "99.9",
        "at": 1654948403000
      },
      {
        "symbol": "zilinr",
        "baseAsset": "zil",
        "quoteAsset": "inr",
        "openPrice": "3.9",
        "lowPrice": "3.5",
        "highPrice": "3.93",
        "lastPrice": "3.52",
        "volume": "834601.0",
        "bidPrice": "3.51",
        "askPrice": "3.52",
        "at": 1654948403000
      },
      {
        "symbol": "batinr",
        "baseAsset": "bat",
        "quoteAsset": "inr",
        "openPrice": "31.67",
        "lowPrice": "28.5",
        "highPrice": "31.997",
        "lastPrice": "28.5",
        "volume": "5185.52",
        "bidPrice": "28.001",
        "askPrice": "29.649",
        "at": 1654948403000
      },
      {
        "symbol": "zrxinr",
        "baseAsset": "zrx",
        "quoteAsset": "inr",
        "openPrice": "31.99",
        "lowPrice": "28.88",
        "highPrice": "31.99",
        "lastPrice": "28.88",
        "volume": "4422.42",
        "bidPrice": "28.5",
        "askPrice": "29.47",
        "at": 1654948403000
      },
      {
        "symbol": "reqinr",
        "baseAsset": "req",
        "quoteAsset": "inr",
        "openPrice": "9.9696",
        "lowPrice": "9.0",
        "highPrice": "9.9696",
        "lastPrice": "9.0001",
        "volume": "28348.21",
        "bidPrice": "9.0",
        "askPrice": "9.849",
        "at": 1654948403000
      },
      {
        "symbol": "icxinr",
        "baseAsset": "icx",
        "quoteAsset": "inr",
        "openPrice": "30.5",
        "lowPrice": "27.6",
        "highPrice": "31.416",
        "lastPrice": "27.6",
        "volume": "9912.31",
        "bidPrice": "27.31",
        "askPrice": "27.6",
        "at": 1654948403000
      },
      {
        "symbol": "omginr",
        "baseAsset": "omg",
        "quoteAsset": "inr",
        "openPrice": "215",
        "lowPrice": "197.0",
        "highPrice": "224.5",
        "lastPrice": "197.0",
        "volume": "1731.74",
        "bidPrice": "196.0",
        "askPrice": "209.998",
        "at": 1654948403000
      },
      {
        "symbol": "polyinr",
        "baseAsset": "poly",
        "quoteAsset": "inr",
        "openPrice": "20.533",
        "lowPrice": "18.13",
        "highPrice": "20.533",
        "lastPrice": "18.762",
        "volume": "33811.0",
        "bidPrice": "18.13",
        "askPrice": "18.836",
        "at": 1654948403000
      },
      {
        "symbol": "iostinr",
        "baseAsset": "iost",
        "quoteAsset": "inr",
        "openPrice": "1.35",
        "lowPrice": "1.25",
        "highPrice": "1.35",
        "lastPrice": "1.25",
        "volume": "1169849.0",
        "bidPrice": "1.25",
        "askPrice": "1.26",
        "at": 1654948403000
      },
      {
        "symbol": "dentinr",
        "baseAsset": "dent",
        "quoteAsset": "inr",
        "openPrice": "0.1",
        "lowPrice": "0.089",
        "highPrice": "0.1",
        "lastPrice": "0.089",
        "volume": "16844344.0",
        "bidPrice": "0.089",
        "askPrice": "0.091",
        "at": 1654948403000
      },
      {
        "symbol": "hotinr",
        "baseAsset": "hot",
        "quoteAsset": "inr",
        "openPrice": "0.194",
        "lowPrice": "0.18",
        "highPrice": "0.197",
        "lastPrice": "0.18",
        "volume": "2560473.0",
        "bidPrice": "0.181",
        "askPrice": "0.184",
        "at": 1654948403000
      }
    ]
""".trimIndent()