package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.cashe.SharedPrefsManager
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.domain.settings.SettingsRepository

class SettingsRepositoryImpl(private val sharedPrefsManager: SharedPrefsManager) : SettingsRepository{
    override fun getSettings(): Either<Failure, SettingsEntity> {
        return sharedPrefsManager.getSettings()
    }
}