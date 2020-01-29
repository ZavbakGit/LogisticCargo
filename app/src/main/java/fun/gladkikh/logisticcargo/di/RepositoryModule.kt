package `fun`.gladkikh.logisticcargo.di


import `fun`.gladkikh.logisticcargo.cashe.SharedPrefsManager
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.domain.settings.SettingsRepository
import `fun`.gladkikh.logisticcargo.repository.AccountRepository
import `fun`.gladkikh.logisticcargo.repository.AccountRepositoryImpl
import `fun`.gladkikh.logisticcargo.repository.SettingsRepositoryImpl
import `fun`.gladkikh.remote.remote.RequestRemote
import `fun`.gladkikh.remote.remote.RequestRemoteImpl
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(sharedPrefsManager: SharedPrefsManager): SettingsRepository
            = SettingsRepositoryImpl(sharedPrefsManager)


    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository = AccountRepositoryImpl()

    @Provides
    @Singleton
    fun provideSettingsEntity(): SettingsEntity = SettingsEntity(
        "Админ",
        "123",
        "http://172.31.255.150//UT/hs/api/",
        Date()
    )

    @Provides
    @Singleton
    fun provideRequestRemote(context:Context,settingsEntity: SettingsEntity): RequestRemote = RequestRemoteImpl(
        context = context,
        isDebug = true,
        baseUrl = settingsEntity.host!!
    )

//    @Provides
//    @ViewModuleScope
//    fun provideTestRequestUseCase(requestRemote: RequestRemote): TestRequestUseCase = TestRequestUseCase(requestRemote)

}