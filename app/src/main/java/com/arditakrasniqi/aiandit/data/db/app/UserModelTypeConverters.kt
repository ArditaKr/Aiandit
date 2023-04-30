package com.arditakrasniqi.aiandit.data.db.app

import androidx.room.TypeConverter
import com.arditakrasniqi.aiandit.data.model.responses.UserModel
import com.google.gson.Gson

class UserModelTypeConverters {

    @TypeConverter
    fun fromJson(json: String): UserModel {
        return Gson().fromJson(json, UserModel::class.java)
    }

    @TypeConverter
    fun toJson(userModel: UserModel): String {
        return Gson().toJson(userModel)
    }
}