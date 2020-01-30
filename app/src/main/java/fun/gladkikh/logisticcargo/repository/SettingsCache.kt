package `fun`.gladkikh.logisticcargo.repository

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity

interface SettingsCache {
    fun getSettings(): Either<Failure,SettingsEntity>
}