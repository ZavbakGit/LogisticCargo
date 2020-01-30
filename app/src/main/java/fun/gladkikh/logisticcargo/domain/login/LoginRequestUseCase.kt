package `fun`.gladkikh.logisticcargo.domain.login

import `fun`.gladkikh.common.domain.interactor.UseCase
import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.remote.remote.RequestRemote
import com.google.gson.GsonBuilder


class LoginRequestUseCase constructor(val requestRemote: RequestRemote) : UseCase<String, None>() {

    override suspend fun run(params: None): Either<Failure, String> {
        val builder = GsonBuilder()
        val gson = builder.create()
        val testStr = gson.toJson(Request("test","111"))

//        (0..10).forEach {
//            delay(1000)
//            Log.d("anit","${it} \n ${Thread.currentThread().name} ")
//        }

        return requestRemote.request("Админ","123",testStr)
    }


    data class Request(val command:String,val data:String)

}