package lazycoder21.droid.crypto.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailPartial
import lazycoder21.droid.crypto.data.mapper.CryptoListingMapper.mapPartial
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOptions
import lazycoder21.droid.crypto.utils.CryptoSortingUtils.SortOrder

@Dao
interface CryptoDao {

    @Query(
        value = """
        SELECT * FROM cryptodetaillocal WHERE Lower(symbol) LIKE '%' || Lower(:symbol) || '%' 
        ORDER BY 
            CASE WHEN :sortOption = 1 and :sortOrder = 1 THEN symbol END DESC,
            CASE WHEN :sortOption = 1 and :sortOrder != 1 THEN symbol END ASC,
            CASE WHEN :sortOption = 2 and :sortOrder = 1 THEN volume END DESC,
            CASE WHEN :sortOption = 2 and :sortOrder != 1 THEN volume END ASC,
            CASE WHEN :sortOption = 3 and :sortOrder = 1 THEN priceChange END DESC,
            CASE WHEN :sortOption = 3 and :sortOrder != 1 THEN priceChange END ASC
    """
    )
    fun getListings(
        symbol: String = "",
        sortOrder: Int = SortOrder.ASCENDING,
        sortOption: Int = SortOptions.ALPHABETIC
    ): LiveData<List<CryptoDetailLocal>>

    @Query(
        value = """
        Select * from cryptodetaillocal WHERE (Lower(symbol) Like '%' || Lower(:symbol) || '%') and (favourite = 1)
        ORDER BY 
            CASE WHEN :sortOption = 1 and :sortOrder = 1 THEN symbol END DESC,
            CASE WHEN :sortOption = 1 and :sortOrder != 1 THEN symbol END ASC,
            CASE WHEN :sortOption = 2 and :sortOrder = 1 THEN volume END DESC,
            CASE WHEN :sortOption = 2 and :sortOrder != 1 THEN volume END ASC,
            CASE WHEN :sortOption = 3 and :sortOrder = 1 THEN priceChange END DESC,
            CASE WHEN :sortOption = 3 and :sortOrder != 1 THEN priceChange END ASC
    """
    )
    fun getFavouriteListings(
        symbol: String = "",
        sortOrder: Int = SortOrder.ASCENDING,
        sortOption: Int = SortOptions.ALPHABETIC
    ): LiveData<List<CryptoDetailLocal>>


    @Query(
        """
        Select * from cryptodetaillocal Where Lower(symbol) = Lower(:symbol)
    """
    )
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

    @Query(
        """
        UPDATE cryptodetaillocal  SET favourite = :favourite WHERE symbol == :symbol 
    """
    )
    suspend fun update(symbol: String, favourite: Int)

    @Query("DELETE FROM cryptodetaillocal")
    suspend fun clearListings()
}