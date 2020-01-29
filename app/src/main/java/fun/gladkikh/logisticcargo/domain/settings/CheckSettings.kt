package `fun`.gladkikh.logisticcargo.domain.settings

import `fun`.gladkikh.common.domain.interactor.UseCase
import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.common.domain.type.flatMap
import `fun`.gladkikh.logisticcargo.domain.failure.NoSavedSettingFailure
import javax.inject.Inject

class CheckSettings @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<None, None>() {


    override suspend fun run(params: None): Either<Failure, None> {
        return settingsRepository.getSettings().flatMap {
            if (it.host.isNullOrEmpty() ||
                it.login1C.isNullOrEmpty() || it.password1C.isNullOrEmpty()
            ) {
                return@flatMap Either.Left(NoSavedSettingFailure())
            } else {
                return@flatMap Either.Right(None())
            }
        }

    }
}
