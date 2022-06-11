package lazycoder21.droid.crypto.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lazycoder21.droid.crypto.data.repository.CryptoRepositoryImpl
import lazycoder21.droid.crypto.domain.repository.CryptoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindCryptoRepo(
        cryptoRepositoryImpl: CryptoRepositoryImpl
    ) : CryptoRepository
}