package `fun`.gladkikh.logisticcargo.di

import `fun`.gladkikh.common.presentation.ViewModelFactory
import `fun`.gladkikh.logisticcargo.TestRequestUseCase
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.repository.AccountRepository
import `fun`.gladkikh.logisticcargo.repository.AccountRepositoryImpl
import `fun`.gladkikh.remote.remote.RequestRemote
import `fun`.gladkikh.remote.remote.RequestRemoteImpl
import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class RepositoryModule {

    @Provides
    @ViewModuleScope
    fun provideAccountRepository(): AccountRepository = AccountRepositoryImpl()

    @Provides
    @ViewModuleScope
    fun provideSettingsEntity(): SettingsEntity = SettingsEntity(
        "Админ",
        "123",
        "http://172.31.255.150//UT/hs/api/",
        Date()
    )

    @Provides
    @ViewModuleScope
    fun provideRequestRemote(context:Context,settingsEntity: SettingsEntity): RequestRemote = RequestRemoteImpl(
        context = context,
        isDebug = true,
        baseUrl = settingsEntity.BASE_URL
    )

//    @Provides
//    @ViewModuleScope
//    fun provideTestRequestUseCase(requestRemote: RequestRemote): TestRequestUseCase = TestRequestUseCase(requestRemote)

}