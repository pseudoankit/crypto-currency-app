package lazycoder21.droid.crypto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import lazycoder21.droid.crypto.data.local.LocalDataBase.Companion.DB_VERSION
import lazycoder21.droid.crypto.data.local.dao.CryptoDao
import lazycoder21.droid.crypto.data.local.entity.CryptoDetailLocal

@Database(
    entities = [CryptoDetailLocal::class],
    version = DB_VERSION
)
abstract class LocalDataBase : RoomDatabase() {
    abstract val cryptoDao: CryptoDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "crypto_db"
    }
}