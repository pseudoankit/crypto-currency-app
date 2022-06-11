package lazycoder21.droid.crypto.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lazycoder21.droid.crypto.data.local.LocalDataBase
import lazycoder21.droid.crypto.data.remote.CryptoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .baseUrl(CryptoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideLocalDataBase(app: Application): LocalDataBase {
        return Room.databaseBuilder(
            app,
            LocalDataBase::class.java,
            LocalDataBase.DB_NAME
        ).build()
    }
}