package `fun`.gladkikh.logisticcargo.domain.login

import `fun`.gladkikh.common.domain.interactor.UseCase
import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import java.util.*
import javax.inject.Inject

class CheckAuth @Inject constructor(
    //private val accountRepository: AccountRepository
) : UseCase<Date, None>() {

    val date = Date()

    override suspend fun run(params: None): Either<Failure, Date>{
        return Either.Right(date)
        //accountRepository.checkAuth()
    }
}

//ToDO Сделать репозиторий