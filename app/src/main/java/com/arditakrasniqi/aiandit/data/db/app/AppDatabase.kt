package com.arditakrasniqi.aiandit.data.db.app

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arditakrasniqi.aiandit.data.db.dao.Aianditdao
import com.arditakrasniqi.aiandit.data.model.requests.LoginRequestModel
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle


@Database(
    entities = [LoginResponseModel::class, Vehicle::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserModelTypeConverters::class, VehicleTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Aianditdao
}