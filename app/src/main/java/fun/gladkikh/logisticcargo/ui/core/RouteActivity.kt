package `fun`.gladkikh.logisticcargo.ui.core

import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.ui.BaseActivity
import `fun`.gladkikh.common.ui.ext.onEvent
import `fun`.gladkikh.logisticcargo.App
import `fun`.gladkikh.logisticcargo.R
import `fun`.gladkikh.logisticcargo.presentation.route.RouteViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation


class RouteActivity : BaseActivity(){

    override val contentId = R.layout.route_activity
    lateinit var viewModel: RouteViewModel

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //App.instance?.initRepositoryComponent()!!.inject(this)
        App.appComponent.inject(this)


        navController =  Navigation.findNavController(this, R.id.nav_host_fragment)

        viewModel =  viewModel{
            onEvent(message, ::handleMessage)
            onEvent(failureData,::handleError)
            onEvent(command,::handleCommand)
        }

        viewModel.auth()



    }

    private fun handleCommand(command: Command?) {
        when(command){
            is Command.OpenSettings ->{
                navController.navigate(R.id.settingsActivity)
            }
            else -> navController.navigate(R.id.secondActivity)
        }
    }

    private fun handleMessage(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(failure: Failure?) {
        Toast.makeText(this,failure.toString(), Toast.LENGTH_SHORT).show()
    }
}