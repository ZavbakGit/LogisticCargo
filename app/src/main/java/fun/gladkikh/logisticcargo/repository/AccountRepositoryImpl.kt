package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.*
import `fun`.gladkikh.logisticcargo.domain.AccountEntity
import `fun`.gladkikh.logisticcargo.domain.failure.JsonToObjectFailure
import `fun`.gladkikh.remote.remote.RequestRemote
import com.google.gson.Gson
import java.util.*

class AccountRepositoryImpl(
    private val settingsCache: SettingsCache,
    private val requestRemote: RequestRemote,
    private val gson: Gson
) : AccountRepository {


    override fun login(password: String): Either<Failure, AccountEntity> {

        val data = object {
            val command = "login"
            val password = password
        }

        return settingsCache.getSettings().flatMap {
            return@flatMap requestRemote.request(it.login1C!!, it.password1C!!, gson.toJson(data))
        }.flatMap {
            try {
                Either.Right(gson.fromJson(it, AccountEntity::class.java))
            } catch (e: Exception) {
                Either.Left(JsonToObjectFailure("Ошибка преобразования ответа login"))
            }
        }
    }


}