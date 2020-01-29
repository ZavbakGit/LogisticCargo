package `fun`.gladkikh.common.presentation

import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.SingleLiveEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel() {

    var failureData = SingleLiveEvent<Failure>()
    var progressData: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failureData.postValue(failure)
        updateProgress(false)
    }

    protected fun updateProgress(progress: Boolean) {
        this.progressData.postValue(progress)
    }
}