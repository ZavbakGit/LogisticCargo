package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.domain.AccountEntity
import java.util.*

class AccountRepositoryImpl : AccountRepository {
    override fun login(password: String): Either<Failure, AccountEntity> {
        return Either.Right(AccountEntity(id = "456", name = "Alex",password = password,userDate = Date() ))
    }



}