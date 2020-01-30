package `fun`.gladkikh.logisticcargo.domain.login

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.logisticcargo.domain.AccountEntity

interface LoginRepository {
    fun getAccount():Either<Failure,AccountEntity>
    fun saveAccount(account:AccountEntity):Either<Failure, None>
    fun login(password:String):Either<AccountEntity, None>
}