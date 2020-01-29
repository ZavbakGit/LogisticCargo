package `fun`.gladkikh.logisticcargo.presentation.route


import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.common.domain.type.SingleLiveEvent
import `fun`.gladkikh.common.presentation.BaseViewModel
import `fun`.gladkikh.logisticcargo.domain.failure.NoSavedAccountFailure
import `fun`.gladkikh.logisticcargo.domain.failure.NoSavedSettingFailure

import `fun`.gladkikh.logisticcargo.domain.settings.CheckSettings
import `fun`.gladkikh.logisticcargo.ui.core.Command
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

class RouteViewModel @Inject constructor(
    val checkSettings: CheckSettings
) : BaseViewModel() {

    val message = SingleLiveEvent<String>()
    val command = SingleLiveEvent<Command>()

    fun auth(){
        checkSettings(None(),viewModelScope){
            it.either(::handleFailureCheck,::handleCheck)
        }
    }


    private fun handleCheck(none:None) {
        command.value = Command.OpenMain
    }

    private fun handleFailureCheck(failure: Failure) {
        when(failure){
            is NoSavedSettingFailure -> command.value = Command.OpenSettings
            is NoSavedAccountFailure -> command.value = Command.OpenSettings
        }
    }

}