package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.domain.AccountEntity

interface AccountRepository {
    fun login(password: String): Either<Failure, AccountEntity>
}