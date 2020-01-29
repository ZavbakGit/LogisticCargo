package `fun`.gladkikh.logisticcargo.di

import `fun`.gladkikh.logisticcargo.MainViewModel
import `fun`.gladkikh.common.presentation.ViewModelFactory
import `fun`.gladkikh.logisticcargo.SecondViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(accountViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    abstract fun bindSecondViewModel(secondViewModel: SecondViewModel): ViewModel

}
