package `fun`.gladkikh.logisticcargo

import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.common.domain.type.SingleLiveEvent
import `fun`.gladkikh.common.presentation.BaseViewModel
import `fun`.gladkikh.remote.remote.RequestRemoteImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import javax.inject.Inject

class MainViewModel @Inject constructor() :BaseViewModel(){

    val message = SingleLiveEvent<String>()
    fun getMessage():LiveData<String> = message

    var job: Job? = null

    val requestRemote = RequestRemoteImpl(
        context = App.appContext()!!,
        baseUrl = "http://172.31.255.150//UT/hs/api/",
        isDebug = true
    )

    val testRequestUseCase = TestRequestUseCase(requestRemote)

    fun starttest(){
        job =  testRequestUseCase(None(),viewModelScope){
            it.either(::handleFailure,::handleRequest)
        }
    }

    fun stop(){
        job?.cancel()
    }

    private fun handleRequest(str: String) {
        this.message.value = str
    }

}