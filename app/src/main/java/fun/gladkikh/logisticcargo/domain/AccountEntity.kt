package `fun`.gladkikh.logisticcargo.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class AccountEntity(
    @SerializedName("user_id")
    var id: String,
    var name: String,
    @SerializedName("user_date")
    var userDate: Date,
    var password: String
)