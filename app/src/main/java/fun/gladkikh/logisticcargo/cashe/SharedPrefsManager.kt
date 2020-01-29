package `fun`.gladkikh.logisticcargo.cashe

import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.common.domain.type.None
import `fun`.gladkikh.logisticcargo.domain.AccountEntity
import `fun`.gladkikh.logisticcargo.domain.failure.NoSavedAccountsError
import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_DATE = "account_date"
        const val ACCOUNT_PASSWORD = "account_password"
    }


    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            putSafely(ACCOUNT_ID, account.id)
            putSafely(ACCOUNT_NAME, account.name)
            putSafely(ACCOUNT_DATE, account.userDate.time)
            putSafely(ACCOUNT_PASSWORD, account.password)
        }.apply()

        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        val id = prefs.getLong(ACCOUNT_ID, 0)

        if (id == 0L) {
            return Either.Left(NoSavedAccountsError())
        }

        val account = AccountEntity(
            prefs.getString(ACCOUNT_ID, "") ?: "",
            prefs.getString(ACCOUNT_NAME, "") ?: "",
            Date(prefs.getLong(ACCOUNT_DATE, 0)),
            prefs.getString(ACCOUNT_PASSWORD, "") ?: ""
        )

        return Either.Right(account)
    }

    fun removeAccount(): Either<Failure, None> {
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_NAME)
            remove(ACCOUNT_DATE)
            remove(ACCOUNT_PASSWORD)
        }.apply()

        return Either.Right(None())
    }

    fun containsAnyAccount(): Either<Failure, Boolean> {
        val id = prefs.getLong(ACCOUNT_ID, 0)
        return Either.Right(id != 0L)
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