package `fun`.gladkikh.remote.remote

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.remote.remote.core.AutorithationUtil
import `fun`.gladkikh.remote.remote.core.NetworkHandler
import `fun`.gladkikh.remote.remote.core.Request
import `fun`.gladkikh.remote.remote.entity.RequestEntity
import `fun`.gladkikh.remote.remote.service.ServiceFactory
import android.content.Context
import java.util.*


class RequestRemoteImpl constructor(
    context: Context,
    isDebug: Boolean,
    baseUrl: String,
    date: Date? = null

) : RequestRemote {

    private val request = Request(NetworkHandler(context))
    private val service = ServiceFactory(baseUrl).makeService(isDebug)

    override fun request(
        user: String,
        password: String,
        data: String
    ): Either<Failure, String> {
        val auth = AutorithationUtil.getStringAutorization(user, password)
        return request.make(service.getDataFromServer(auth, RequestEntity(data))) {
            it.data
        }
    }
}