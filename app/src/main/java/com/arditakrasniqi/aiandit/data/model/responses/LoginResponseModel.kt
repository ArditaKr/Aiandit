package com.arditakrasniqi.aiandit.data.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginResponseModel(
    @PrimaryKey
    val token: String,
    val user: UserModel
)
