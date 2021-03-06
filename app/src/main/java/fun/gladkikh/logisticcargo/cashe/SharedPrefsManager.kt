package `fun`.gladkikh.logisticcargo.cashe

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.logisticcargo.domain.AccountEntity
import `fun`.gladkikh.logisticcargo.domain.SettingsEntity
import `fun`.gladkikh.logisticcargo.domain.failure.NoSavedAccountFailure
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(
    private val prefs: SharedPreferences,
    private val gson: Gson
) {
    companion object {
        const val HOST = "preference_host"
        const val LOGIN_1C = "preference_login"
        const val PASSWORD_1C = "preference_pass"
        const val LIST_TSD = "list_tsd"

        const val ACCOUNT = "account"

    }

    fun getAccountEntity(): Either<Failure, AccountEntity> {

        val accountStr =
            prefs.getString(ACCOUNT, null) ?: return Either.Left(NoSavedAccountFailure())

        val account = gson.fromJson(accountStr, AccountEntity::class.java)
        return Either.Right(account)

    }

    fun saveAccountEntity(account: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            putSafely(ACCOUNT, gson.toJson(account))
        }.apply()
        return Either.Right(None())
    }

    fun getSettings(): Either<Failure, SettingsEntity> {
        val host = prefs.getString(HOST, null)
        val login1C = prefs.getString(LOGIN_1C, null)
        val password1C = prefs.getString(PASSWORD_1C, null)
        val tsd = prefs.getString(LIST_TSD, 1.toString())?.toIntOrNull()

        return Either.Right(
            SettingsEntity(
                host = host,
                login1C = login1C,
                date = Date(),
                password1C = password1C
            )
        )
    }

}

fun SharedPreferences.Editor.putSafely(key: String, value: Long?) {
    if (value != null && value != 0L) {
        putLong(key, value)
    }
}

fun SharedPreferences.Editor.putSafely(key: String, value: String?) {
    if (value != null && value.isNotEmpty()) {
        putString(key, value)
    }
}