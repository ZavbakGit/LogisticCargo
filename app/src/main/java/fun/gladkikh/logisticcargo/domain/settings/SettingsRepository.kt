package `fun`.gladkikh.logisticcargo.domain.settings

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity

interface SettingsRepository {
    fun getSettings():Either<Failure,SettingsEntity>
}