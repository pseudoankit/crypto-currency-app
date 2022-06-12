package lazycoder21.droid.crypto.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailPartial
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapPartial

@Dao
interface CryptoDao {

    @Query("""
        Select * from cryptodetaillocal WHERE Lower(symbol) Like '%' || Lower(:symbol) || '%'
    """)
    fun getListings(symbol: String = ""): LiveData<List<CryptoDetailLocal>>

    @Query("""
        Select * from cryptodetaillocal WHERE (Lower(symbol) Like '%' || Lower(:symbol) || '%') and (favourite = 1)
    """)
    fun getFavouriteListings(symbol: String = ""): LiveData<List<CryptoDetailLocal>>

    @Query("""
        Select * from cryptodetaillocal Where Lower(symbol) = Lower(:symbol)
    """)
    fun getDetail(symbol: String): LiveData<CryptoDetailLocal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListings(list: List<CryptoDetailLocal>): LongArray

    @Transaction
    suspend fun insertOrUpdate(objList: List<CryptoDetailLocal>) = insertListings(objList)
        .withIndex()
        .filter { it.value == -1L }
        .forEach {
            updatePartial(objList[it.index].mapPartial)
        }

    @Update(entity = CryptoDetailLocal::class)
    fun updatePartial(item: CryptoDetailPartial)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListing(list: CryptoDetailLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(item: CryptoDetailLocal)

    @Update
    suspend fun update(item: CryptoDetailLocal)

    @Query("DELETE FROM cryptodetaillocal")
    suspend fun clearListings()
}