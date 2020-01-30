package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.flatMap
import `fun`.gladkikh.logisticcargo.domain.AccountEntity
import `fun`.gladkikh.logisticcargo.domain.failure.JsonToObjectFailure
import `fun`.gladkikh.remote.remote.RequestRemote
import com.google.gson.Gson

class AccountRepositoryImpl(
    private val settingsCache: SettingsCache,
    private val requestRemote: RequestRemote,
    private val gson: Gson
) : AccountRepository {


    override fun login(password: String): Either<Failure, AccountEntity> {

        return settingsCache.getSettings().flatMap {
            return@flatMap requestRemote.request(
                it.login1C!!,
                it.password1C!!,
                gson.toJson(DataRequestLogin(password))
            )
        }.flatMap {
            try {
                Either.Right(gson.fromJson(it, AccountEntity::class.java))
            } catch (e: Exception) {
                Either.Left(JsonToObjectFailure("Ошибка преобразования ответа login"))
            }
        }
    }

    class DataRequestLogin(val password: String, val command: String = "login")
}