package com.arditakrasniqi.aiandit.data.db.app

import androidx.room.TypeConverter
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import com.google.gson.Gson

class VehicleTypeConverters {

    @TypeConverter
    fun fromJson(json: String): Vehicle {
        return Gson().fromJson(json, Vehicle::class.java)
    }

    @TypeConverter
    fun toJson(vehicle: Vehicle): String {
        return Gson().toJson(vehicle)
    }

    @TypeConverter
    fun fromJson2(json: String): Vehicle.ImageCount {
        return Gson().fromJson(json, Vehicle.ImageCount::class.java)
    }

    @TypeConverter
    fun toJson2(imageCount: Vehicle.ImageCount): String {
        return Gson().toJson(imageCount)
    }

    @TypeConverter
    fun fromJson3(json: String): Vehicle.VehicleInfo {
        return Gson().fromJson(json, Vehicle.VehicleInfo::class.java)
    }

    @TypeConverter
    fun toJson3(vehicleInfo: Vehicle.VehicleInfo): String {
        return Gson().toJson(vehicleInfo)
    }
}