package lazycoder21.droid.crypto.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal

@Dao
interface CryptoDao {

    @Query("""
        Select * from cryptodetaillocal WHERE Lower(symbol) Like '%' || Lower(:symbol) || '%'
    """)
    fun getListings(symbol: String = ""): LiveData<List<CryptoDetailLocal>>

    @Query("""
        Select * from cryptodetaillocal Where Lower(symbol) = Lower(:symbol)
    """)
    fun getDetail(symbol: String): LiveData<CryptoDetailLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListings(list: List<CryptoDetailLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(item: CryptoDetailLocal)

    @Update
    suspend fun update(item: CryptoDetailLocal)

    @Query("DELETE FROM cryptodetaillocal")
    suspend fun clearListings()
}