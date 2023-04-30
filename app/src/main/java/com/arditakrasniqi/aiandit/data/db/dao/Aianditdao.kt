package com.arditakrasniqi.aiandit.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arditakrasniqi.aiandit.data.model.responses.LoginResponseModel
import com.arditakrasniqi.aiandit.data.model.responses.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface Aianditdao {

    /**Login Data*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(loginResponseModel: LoginResponseModel)

    @Query("SELECT * FROM LoginResponseModel WHERE token = :token")
    fun getUser(token: String): Flow<LoginResponseModel>

    @Query("DELETE FROM LoginResponseModel")
    fun deleteLoginResponse()


    /**Vehicle Data*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehiclesDataToDb(vehicleList: List<Vehicle>)

}