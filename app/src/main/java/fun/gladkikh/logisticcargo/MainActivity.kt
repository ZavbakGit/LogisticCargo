package `fun`.gladkikh.logisticcargo

import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.map
import `fun`.gladkikh.common.ui.BaseActivity
import `fun`.gladkikh.common.ui.ext.onEvent
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.repository.AccountRepository
import `fun`.gladkikh.logisticcargo.ui.core.RouteActivity
import `fun`.gladkikh.logisticcargo.ui.preferences.SettingsActivity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var accountRepository: AccountRepository

    override val contentId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //App.instance?.initRepositoryComponent()!!.inject(this)
        App.appComponent.inject(this)

        App.appContext()?.let {
            Settings.Secure.getString(it.contentResolver, Settings.Secure.ANDROID_ID)
        }





        accountRepository.login("111").map {
            it.toString()
        }.either(::handleError,::handleMessage)


        mainViewModel =  viewModel{
            onEvent(message, ::handleMessage)
            onEvent(failureData,::handleError)
        }

        btStart.setOnClickListener {
            mainViewModel.starttest()
        }

        btStartSecondActivity.setOnClickListener {
            val i = Intent(baseContext, SettingsActivity::class.java)
            startActivity(i)
        }

    }

    private fun handleMessage(message: String?) {
       Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun handleError(failure: Failure?) {
        Toast.makeText(this,failure.toString(),Toast.LENGTH_SHORT).show()
    }

}
