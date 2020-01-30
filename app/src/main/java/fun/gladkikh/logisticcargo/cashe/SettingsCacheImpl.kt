package `fun`.gladkikh.logisticcargo.cashe

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.repository.SettingsCache
import javax.inject.Inject

class SettingsCacheImpl @Inject constructor (private val prefsManager: SharedPrefsManager) : SettingsCache {
    override fun getSettings(): Either<Failure, SettingsEntity> {
        return prefsManager.getSettings()
    }

}