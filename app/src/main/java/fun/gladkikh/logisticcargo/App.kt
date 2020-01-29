package `fun`.gladkikh.logisticcargo

import `fun`.gladkikh.logisticcargo.di.ViewModuleScope
import `fun`.gladkikh.logisticcargo.di.AppModule
import `fun`.gladkikh.logisticcargo.di.RepositoryModule
import `fun`.gladkikh.logisticcargo.di.ViewModelModule
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

class App : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var appComponent: AppComponent

        var instance: App? = null
        fun appContext(): Context? = instance?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

//    // ActivityComponent (scoped to just the lifetime of an Activity).
//    var activityComponent: ActivityComponent? = null
//
//    fun createActivityComponent(): ActivityComponent? {
////        activityComponent = appComponent.plus(RepositoryModule())
////        return activityComponent
//    }

    fun initRepositoryComponent():RepositoryComponent{
        val modul = RepositoryModule()
        return appComponent.getRepositoryComponent(repositoryModule = modul)
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}



@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {
    fun getRepositoryComponent(repositoryModule: RepositoryModule):RepositoryComponent
}

@ViewModuleScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(activity: SecondActivity)
}